package com.busmonk.rest.v1;

import com.busmonk.service.RegistrationService;
import com.busmonk.service.bo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sr250345 on 11/17/16.
 */
@RestController
@RequestMapping("/signup")
public class RegistrationServiceV1 {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationServiceV1.class);

    @Autowired
    RegistrationService registrationService;

    @RequestMapping(method= RequestMethod.POST, value="/users", consumes= MediaType.APPLICATION_JSON_VALUE)
    public User createUser(@RequestBody User u)
    {
        logger.info("createUser()");
        return registrationService.createUser(u);
    }

    @RequestMapping(method= RequestMethod.POST, value="/otp", consumes= MediaType.APPLICATION_JSON_VALUE)
    public User sendOTP(@RequestBody User u, HttpServletRequest request, HttpServletResponse response)
    {
        logger.info("sendOTP()");
        User retVal = null;
        retVal  = registrationService.sendOTP(u);
        if(retVal == null)
        {
        	response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return retVal;
     }

    /**
     * 
     * @param u
     * @param request
     * @param response
     * @return  404 when either user not found with mobile or wrong OTP
     */
    @RequestMapping(method= RequestMethod.POST, value="/otp/varify", consumes= MediaType.APPLICATION_JSON_VALUE)
    public User varifyOTP(@RequestBody User u, HttpServletRequest request, HttpServletResponse response)
    {
    	User retVal = null;
    	logger.info("varifyOTP()");
        retVal = registrationService.varifyOTP(u); 
        if(retVal == null)
        {
        	response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return retVal;
    }

    /**
     * 
     * @param u
     * @param request
     * @param response
     * @return 404 when user not found for given mobile 
     */
    @RequestMapping(method= RequestMethod.PUT, value="/users", consumes= MediaType.APPLICATION_JSON_VALUE)
    public User updateUser(@RequestBody User u, HttpServletRequest request, HttpServletResponse response)
    {
        logger.info("updateUser()");
        User retVal = null;
        retVal =  registrationService.updateUser(u);
        if(retVal == null)
        {
        	response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return retVal;
    }
}
