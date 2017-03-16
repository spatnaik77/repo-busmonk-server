package com.busmonk.service;

import com.busmonk.dal.service.DatabaseService;
import com.busmonk.security.JWTUtil;
import com.busmonk.service.bo.Jwt;
import com.busmonk.service.bo.RegistrationStatus;
import com.busmonk.service.bo.User;
import com.busmonk.util.ObjectConverter;
import com.busmonk.util.Util;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by sr250345 on 11/17/16.
 */
public class AuthenticationService {

    @Autowired
    private DatabaseService databaseService;

    public Jwt login(User u)
    {
        Jwt retVal = null;

        long mobile = u.getMobile();
        String password = u.getPassword();
        String hashedPassword = Util.getHashedPassword(password);
        User user =  ObjectConverter.convert(databaseService.authenticateUser(mobile, hashedPassword));

        if(user == null)
        {
            //authentication failed
            //throw exception
        }
        else
        {
            if(user.getRegistrationStatus() == RegistrationStatus.REGISTRATION_COMPLETE)
            {
                String token = JWTUtil.createJWT(user);
                retVal = new Jwt(token);
            }
            else
            {
                //throw exception - login cannot be performed
            }
        }
        return retVal;
    }
}
