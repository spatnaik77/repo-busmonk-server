/**
 * 
 */
package com.busmonk.rest.v1;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.busmonk.service.bo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.busmonk.service.UserService;
import com.busmonk.util.LatitudeLongitude;


/**
 * @author Siddharth Patnaik
 *
 */
@RestController
@RequestMapping("/api/v1")
public class UserServiceV1 {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceV1.class);

	@Autowired
	private UserService userService;

	/**
	 * 
	 * @param id
	 * @param idType
	 * @param request
	 * @param response
	 * @return 404 : user with the given id or token not found
	 */
	@RequestMapping(method=RequestMethod.GET, value="/users/{id:.+}", produces=MediaType.APPLICATION_JSON_VALUE)
	public User getUser(@PathVariable("id") String id, @RequestParam("type") String idType, 
							HttpServletRequest request, HttpServletResponse response)
	{
		logger.info("getUser(), idType:" + idType + " ,id:" + id);

		User retVal = null;
		if("id".equalsIgnoreCase(idType))
		{
			retVal =  userService.getUserById(id);
		}
		else if("token".equalsIgnoreCase(idType))
		{
			retVal =  userService.getUserByToken(id);
		}
		if(retVal == null)
        {
            logger.info("user not found: idType:" + idType + ", id:" + id);
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
		return retVal;

	}

	@RequestMapping(method=RequestMethod.POST, value="/users/{id}/userroutes", consumes=MediaType.APPLICATION_JSON_VALUE)
	public UserRoute createUserRoute(@RequestBody UserRoute ur)
	{
		logger.info("createUserRoute()");

		UserRoute result = userService.createUserRoute(ur);
		return result;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/users/{id}/userroutes", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<UserRoute> getUserRoutes(@PathVariable("id") String userId)
	{
		logger.info("getUserRoutes(), userId:" + userId);
		return userService.getUserRoutes(userId);
	}
	
	/*@RequestMapping(method=RequestMethod.PUT, value="/users/{id}/userroutes/{routeId}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public void updateUserRoute(@RequestBody UserRoute ur)
	{
		logger.info("updateUserRoute()");
		userService.updateUserRoute(ur);
	}*/

	@RequestMapping(method=RequestMethod.GET, value="/searchbus", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<UserRouteDetail> searchBuses(@RequestParam("srcLat") double srcLat, @RequestParam("srcLongt") double srcLongt,
												@RequestParam("destLat") double destLat, @RequestParam("destLongt") double destLongt)
	{
		logger.info("searchBuses(), srcLat srcLongt destLat destLongt-" + srcLat + " " + srcLongt + " " + destLat + " " + destLongt);
		return userService.searchBuses(new LatitudeLongitude(srcLat, srcLongt), new LatitudeLongitude(destLat, destLongt));
	}

	@RequestMapping(method=RequestMethod.GET, value="/routes/{id}/stops", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Stop> getStopsForRoute(@PathVariable("id") String routeId)
	{
		logger.info("getStopsForRoute(), routeId:" + routeId);
		return userService.getStopsForRoute(routeId);
	}
}
