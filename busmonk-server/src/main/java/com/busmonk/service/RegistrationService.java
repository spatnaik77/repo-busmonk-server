package com.busmonk.service;

import com.busmonk.dal.service.DatabaseService;
import com.busmonk.security.JWTUtil;
import com.busmonk.service.bo.RegistrationStatus;
import com.busmonk.service.bo.User;
import com.busmonk.util.ObjectConverter;
import com.busmonk.util.Util;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by sr250345 on 11/17/16.
 */
public class RegistrationService {

    @Autowired
    private DatabaseService databaseService;

    public User createUser(User u)
    {
		/*
			The user object just contains the mobile number
			check if a user exists with the phone number
			if exist, return the user
			else
				insert the user with registrationStatus as MFA_PENDING
		*/
        long mobile = u.getMobile();
        User user  = ObjectConverter.convert(databaseService.getUserByMobile(mobile));
        if(user == null)
        {
            user = new User();
            user.setId(""+ System.currentTimeMillis());
            user.setMobile(mobile);
            user.setRegistrationStatus(RegistrationStatus.MFA_PENDING);
            databaseService.insertUser(ObjectConverter.convert(user));

            return ObjectConverter.convert(databaseService.getUserByMobile(mobile));
        }
        else
        {
            return user;
        }

    }
    public User sendOTP(User u)
    {
		long mobile = u.getMobile();
        User user  = ObjectConverter.convert(databaseService.getUserByMobile(mobile));
        if(user == null)
        {
            //throw exception
        }
        else
        {
            //generate OTP
            user.setOtp(123456);
            databaseService.updateUser(ObjectConverter.convert(user));
        }
        return user;

    }
    public User varifyOTP(User u)
    {
		/*
			get user by id. If not found throw exception
			varify OTP.
				If correct, update the registrationStatus with MFA_COMPLETE
				else throw otp mismatch exception
		 */
        User result = null;

        long mobile = u.getMobile();
        int otp = u.getOtp();

        User user = ObjectConverter.convert(databaseService.getUserByMobile(mobile));
        if(user == null)
        {
            //throw exception
        }
        else
        {
            if(otp == user.getOtp())
            {
                user.setRegistrationStatus(RegistrationStatus.MFA_COMPLETE);
                databaseService.updateUser(ObjectConverter.convert(user));
                result = user;
            }
        }
        return result;
    }

    /**
     * Used to update the user with additional details like work email, password etc
     * This can be invoked only if registrationStatus = MFA_COMPLETE
     * @param u
     * @return  token
     */
    public User updateUser(User u)
    {
        User result = null;

        long mobile = u.getMobile();
        String email = u.getEmail();
        String password = Util.getHashedPassword(u.getPassword());

        User user = ObjectConverter.convert(databaseService.getUserByMobile(mobile));
        if(user == null)
        {
            //throw exception
        }
        else
        {
            //varify reg status
            if(user.getRegistrationStatus() == RegistrationStatus.MFA_COMPLETE)
            {
                user.setEmail(email);
                user.setPassword(password);
                user.setRegistrationStatus(RegistrationStatus.REGISTRATION_COMPLETE);
                databaseService.updateUser(ObjectConverter.convert(user));

                //generate jason web token and return
                //resultToken = JWTUtil.createJWT(user.getId(), -1);
                result = user;
            }
            else
            {
                //throw exception
            }
        }
        return user;

    }



}
