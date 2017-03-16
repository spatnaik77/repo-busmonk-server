/**
 * 
 */
package com.busmonk.service;

import java.util.List;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import com.busmonk.dal.data.CompanyDO;
import com.busmonk.dal.data.RouteDO;
import com.busmonk.dal.data.BusDO;
import com.busmonk.dal.data.BusTimingDO;
import com.busmonk.dal.data.UserDO;
import com.busmonk.dal.service.DatabaseService;
import com.busmonk.service.bo.Cab;
import com.busmonk.service.bo.Company;
import com.busmonk.service.bo.Driver;
import com.busmonk.service.bo.Route;
import com.busmonk.service.bo.Bus;
import com.busmonk.service.bo.BusTiming;
import com.busmonk.service.bo.Stop;
import com.busmonk.service.bo.User;
import com.busmonk.util.ObjectConverter;

/**
 * @author Siddharth Patnaik
 *
 */
public class AdminService {

	//final static Logger logger = LoggerFactory.getLogger(AdminService.class);


	@Autowired
	DatabaseService databaseService;



	public String hello()
	{
		//logger.info("Loggingggggggggggggggggg");
		System.out.println(databaseService.getCompanies());
		return "Hello world";
	}

	public void createCompany(@RequestBody Company c)
	{
		databaseService.insertCompany(ObjectConverter.convert(c));
	}
	
	public void updateCompany(Company c)
	{
		CompanyDO cdo = ObjectConverter.convert(c);
		databaseService.updateCompany(cdo);
	}
	
	public List<Company> getCompanies()
	{
		List<CompanyDO> cdoList = databaseService.getCompanies();
		return ObjectConverter.convertCompanies(cdoList);
	}
	
	public Company getCompany(String id)
	{
		CompanyDO cdo = databaseService.getCompanyById(id);
		return ObjectConverter.convert(cdo);
	}
	
	public void createUser(User u)
	{
		UserDO udo = ObjectConverter.convert(u);
		databaseService.insertUser(udo);
	}
	
	public void updateUser(User u)
	{
		UserDO udo = ObjectConverter.convert(u);
		databaseService.updateUser(udo);
	}
	
	public User  getUserById(String id)
	{
		UserDO udo = databaseService.getUserById(id);
		return ObjectConverter.convert(udo);
	}
	
	/*public User  getUserByEmail(String email)
	{
		UserDO udo = databaseService.getUserByEmail(email);
		return ObjectConverter.convert(udo);
	}*/
	
	public void createRoute(Route r)
	{
		databaseService.insertRoute(ObjectConverter.convert(r));
	}
	
	public List<Route> getRoutes()
	{
		List<RouteDO> dbRes = databaseService.getRoutes();
		return ObjectConverter.convertRoutes(dbRes);
	}
	
	public void createBus(Bus r)
	{
		databaseService.insertBus(ObjectConverter.convert(r));
	}
	public void updateBus(Bus r)
	{
		databaseService.updateBus(ObjectConverter.convert(r));
	}
	public List<Bus> getBuses()
	{
		List<BusDO> dbRes = databaseService.getBuses();
		return ObjectConverter.convertBuses(dbRes);
	}
	public Bus getBus(String id)
	{
		return ObjectConverter.convert(databaseService.getBus(id));
	}
	
	public void createBusTiming(BusTiming rs)
	{
		databaseService.insertBusTiming(ObjectConverter.convert(rs));
	}
	public void updateBusTiming(BusTiming rs)
	{
		databaseService.updateBusTiming(ObjectConverter.convert(rs));
	}
	public List<BusTiming> getStops(String routeId)
	{
		List<BusTimingDO> dbres = databaseService.getBusTimingsForBus(routeId);
		return ObjectConverter.convertBusTimings(dbres);
	}
	
	public void createStop(Stop s)
	{
		databaseService.insertStop(ObjectConverter.convert(s));
	}
	public List<Stop> getStops()
	{
		return ObjectConverter.convertStops(databaseService.getStops());
	}
	public Stop getStop(String id)
	{
		return ObjectConverter.convert(databaseService.getStop(id));
	}
	public void updateStop(Stop s)
	{
		databaseService.updateStop(ObjectConverter.convert(s));
	}
	
	public void createCab(Cab c)
	{
		databaseService.insertCab(ObjectConverter.convert(c));
	}
	public void updateCab(Cab c)
	{
		databaseService.updateCab(ObjectConverter.convert(c));
	}
	public List<Cab> getCabs()
	{
		return ObjectConverter.convertCabs(databaseService.getCabs());
	}
	public Cab getCab(String cabId)
	{
		return ObjectConverter.convert(databaseService.getCab(cabId));
	}
	public void createCabDriver(Driver cabDriver)
	{
		databaseService.insertDriver(ObjectConverter.convert(cabDriver));
	}
	public void updateCabDriver(Driver cabDriver)
	{
		databaseService.updateDriver(ObjectConverter.convert(cabDriver));
	}
	public List<Driver> getCabDrivers()
	{
		return ObjectConverter.convertCabDrivers(databaseService.getDrivers());
	}
	public Driver getCabDriver(String id)
	{
		return ObjectConverter.convert(databaseService.getDriver((id)));
	}
	
}
