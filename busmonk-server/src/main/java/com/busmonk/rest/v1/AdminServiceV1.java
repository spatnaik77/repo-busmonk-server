/**
 * 
 */
package com.busmonk.rest.v1;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.busmonk.service.AdminService;
import com.busmonk.service.bo.Bus;
import com.busmonk.service.bo.BusTiming;
import com.busmonk.service.bo.Cab;
import com.busmonk.service.bo.Company;
import com.busmonk.service.bo.Driver;
import com.busmonk.service.bo.User;

/**
 * @author Siddharth Patnaik
 *
 */
@RestController    
@RequestMapping("/api/v1/admin")
public class AdminServiceV1 
{
	private static final Logger logger = LoggerFactory.getLogger(AdminServiceV1.class);

	@Autowired
	private AdminService adminService;
	
	@RequestMapping(method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE, value = "/")
	public String hello()
	{
		logger.info("hello()");
		return "Hello busmonk"; 
	}

	@RequestMapping(method=RequestMethod.POST, value="/companies", consumes=MediaType.APPLICATION_JSON_VALUE)
	public void createCompany(@RequestBody Company c)
	{
		logger.info("createCompany()");
		adminService.createCompany(c);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/companies", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Company> getCompanies()
	{
		logger.info("getCompanies()");
		return adminService.getCompanies();
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/companies/{id}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public void updateCompany(@PathVariable("id") String id, @RequestBody Company c)
	{
		logger.info("updateCompany(), companyId:"+ id);
		c.setId(id);
		adminService.updateCompany(c);
	}

	@RequestMapping(method=RequestMethod.GET, value="/companies/{id}", headers="Accept=*/*", produces=MediaType.APPLICATION_JSON_VALUE)
	public Company getCompany(@PathVariable("id") String id)
	{
		logger.info("getCompany(), companyId:" + id);
		return adminService.getCompany(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/users", consumes=MediaType.APPLICATION_JSON_VALUE)
	public void createUser(@RequestBody User u)
	{
		logger.info("createUser()");
		adminService.createUser(u);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/users/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public User getuser(@PathVariable("id") String id)
	{
		logger.info("getuser(), userId:" + id);
		return adminService.getUserById(id);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/users/{id}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public void updateUser(@PathVariable("id") String id, @RequestBody User u)
	{
		logger.info("updateUser(), userId:" + id);
		u.setId(id);
		adminService.updateUser(u);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/buses", consumes=MediaType.APPLICATION_JSON_VALUE)
	public void createBus(@RequestBody Bus r)
	{
		logger.info("createBus()");
		adminService.createBus(r);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/buses", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Bus> getBuses()
	{
		logger.info("getBuses()");
		return adminService.getBuses();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/buses/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Bus getBus(@PathVariable("id") String id)
	{
		logger.info("getBus(), busId:"+ id);
		return adminService.getBus(id);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/buses/{id}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public void updateBus(@PathVariable("id") String id, @RequestBody Bus r)
	{
		logger.info("updateBus(), busId:" + id);
		adminService.updateBus(r);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/buses/{id}/timings", consumes=MediaType.APPLICATION_JSON_VALUE)
	public void createBusTiming(@PathVariable("id") String id, @RequestBody BusTiming routeSchedule)
	{
		logger.info("createBusTiming(), busId:" + id);
		routeSchedule.setBusId(id);
		adminService.createBusTiming(routeSchedule);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/cabs", consumes=MediaType.APPLICATION_JSON_VALUE)
	public void createCab(@RequestBody Cab c)
	{
		logger.info("createCab()");
		adminService.createCab(c);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/cabs", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Cab> getCabs()
	{
		logger.info("getCabs()");
		return adminService.getCabs();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/cabs/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Cab getCab(@PathVariable("id") String id)
	{
		logger.info("getCab(), cabId:" + id);
		return adminService.getCab(id);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/cabs/{id}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public void updateCab(@PathVariable("id") String id, @RequestBody Cab c)
	{
		logger.info("updateCab(), cabId:" + id);
		c.setId(id);
		adminService.updateCab(c);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/cabdrivers", consumes=MediaType.APPLICATION_JSON_VALUE)
	public void createCabDriver(@RequestBody Driver cabDriver)
	{
		logger.info("createCabDriver()");
		adminService.createCabDriver(cabDriver);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/cabdrivers", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Driver> getCabDrivers()
	{
		logger.info("getCabDrivers()");
		return adminService.getCabDrivers();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/cabdrivers/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Driver getCabDriver(@PathVariable("id") String id)
	{
		logger.info("getCabDriver(), id:" + id);
		return adminService.getCabDriver(id);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/cabdrivers/{id}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public void updateCabDriver(@PathVariable("id") String id, @RequestBody Driver cabDriver)
	{
		logger.info("updateCabDriver(), cabId:" + id);
		cabDriver.setId(id);
		adminService.updateCabDriver(cabDriver);
	}
	
}
