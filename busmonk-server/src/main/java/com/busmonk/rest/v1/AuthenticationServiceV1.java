package com.busmonk.rest.v1;

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

import com.busmonk.service.AuthenticationService;
import com.busmonk.service.bo.Jwt;
import com.busmonk.service.bo.User;

/**
 * Created by sr250345 on 10/31/16.
 */

@RestController
@RequestMapping("/auth")
public class AuthenticationServiceV1 {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationServiceV1.class);

    @Autowired
    private AuthenticationService authenticationService;

    /**
     * 
     * @param u
     * @param request
     * @param response
     * @return  403 when login fails
     */
    @RequestMapping(method= RequestMethod.POST, value="/login", consumes= MediaType.APPLICATION_JSON_VALUE)
    public Jwt login(@RequestBody User u, HttpServletRequest request, HttpServletResponse response)
    {
        logger.info("login()");
        Jwt retVal = null;
        retVal = authenticationService.login(u);
        if(retVal == null)
        {
            logger.info("Incorrect mobile number or password" + " Mobile: " + u.getMobile() + " Password:" + u.getPassword());
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);//403
        }
        else
        {
        	logger.info("jwt token:" + retVal);
        }
        return retVal;
    }






}
