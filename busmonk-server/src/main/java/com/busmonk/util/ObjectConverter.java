/**
 * 
 */
package com.busmonk.util;

import java.util.ArrayList;
import java.util.List;

import com.busmonk.dal.data.BusDO;
import com.busmonk.dal.data.BusTimingDO;
import com.busmonk.dal.data.CabDO;
import com.busmonk.dal.data.CompanyDO;
import com.busmonk.dal.data.DriverDO;
import com.busmonk.dal.data.RouteDO;
import com.busmonk.dal.data.StopDO;
import com.busmonk.dal.data.UserDO;
import com.busmonk.dal.data.UserRouteDO;
import com.busmonk.service.bo.*;

/**
 * @author Siddharth Patnaik

 */
public class ObjectConverter {
	
	public static CompanyDO convert(Company c)
	{
		if(c == null)
		{
			return null;
		}
		CompanyDO cdo = new CompanyDO();
		cdo.setId(c.getId());
		cdo.setName(c.getName());
		cdo.setAddress1(c.getAddress1());
		cdo.setAddress2(c.getAddress2());
		cdo.setAddress3(c.getAddress3());
		cdo.setAddress4(c.getAddress4());
		cdo.setAddress5(c.getAddress5());
		return cdo;
	}
	
	public static Company convert(CompanyDO cdo)
	{
		if(cdo == null)
		{
			return null;
		}
		
		Company c = new Company();
		c.setId(cdo.getId());
		c.setName(cdo.getName());
		c.setAddress1(cdo.getAddress1());
		c.setAddress2(cdo.getAddress2());
		c.setAddress3(cdo.getAddress3());
		c.setAddress4(cdo.getAddress4());
		c.setAddress5(cdo.getAddress5());
		return c;
	}
	public static List<Company> convertCompanies(List<CompanyDO> cdoList)
	{
		if(cdoList == null)
		{
			return null;
		}
		
		List<Company> res = new ArrayList<Company>();
		for(CompanyDO cdo : cdoList)
		{
			res.add(convert(cdo));
		}
		return res;
	}
	
	public static UserDO convert(User u)
	{
		if(u == null)
		{
			return null;
		}
		
		UserDO udo = new UserDO();
		udo.setId(u.getId());
		udo.setName(u.getName());
		udo.setEmail(u.getEmail());
		udo.setPassword(u.getPassword());
		udo.setGender(u.getGender());
		udo.setMobile(u.getMobile());
		udo.setCompanyId(u.getCompanyId());
		udo.setRoles(u.getRoles());
		udo.setHomeAddress(u.getHomeAddress());
		udo.setHomeAddressCoordinates(u.getHomeAddressCoordinates());
		udo.setOfficeAddress(u.getOfficeAddress());
		udo.setOfficeAddressCoordinates(u.getOfficeAddressCoordinates());
		udo.setOtp(u.getOtp());
		udo.setRegistrationStatus(getRegistrationStatusAsIntVal(u.getRegistrationStatus()));
		
		return udo;
	}
	
	public static User convert(UserDO udo)
	{
		if(udo == null)
		{
			return null;
		}
		
		User u = new User();
		u.setId(udo.getId());
		u.setName(udo.getName());
		u.setEmail(udo.getEmail());
		u.setPassword(udo.getPassword());
		u.setGender(udo.getGender());
		u.setMobile(udo.getMobile());
		u.setCompanyId(udo.getCompanyId());
		u.setRoles(udo.getRoles());
		u.setHomeAddress(udo.getHomeAddress());
		u.setHomeAddressCoordinates(udo.getHomeAddressCoordinates());
		u.setOfficeAddress(udo.getOfficeAddress());
		u.setOfficeAddressCoordinates(udo.getOfficeAddressCoordinates());
		u.setOtp(udo.getOtp());
		u.setRegistrationStatus(getRegistrationStatusFromIntVal(udo.getRegistrationStatus()));
		
		return u;
	}
	
	public static UserRouteDO convert(UserRoute ur)
	{
		if(ur == null)
		{
			return null;
		}
		
		UserRouteDO urd = new UserRouteDO();
		urd.setId(ur.getId());
		urd.setName(ur.getName());
		urd.setUserId(ur.getUserId());
		urd.setBusId(ur.getBusId());
		urd.setBoardingPoint(ur.getBoardingPoint());
		urd.setDropPoint(ur.getDropPoint());
		return urd;
	}
	
	/*public static  UserRoute convert(UserRouteDO urd)
	{
		if(urd == null)
		{
			return null;
		}
		
		UserRoute ur = new UserRoute();
		ur.setId(urd.getId());
		ur.setUserId(urd.getUserId());
		ur.setBusId(urd.getBusId());
		ur.setBoardingPoint(urd.getBoardingPoint());
		ur.setDropPoint(urd.getDropPoint());
		return ur;
	}*/

	/*public static  List<UserRoute> convert(List<UserRouteDO> urd)
	{
		List<UserRoute> result = new ArrayList<UserRoute>();
		for(UserRouteDO u : urd)
		{
			result.add(convert(u));
		}
		return result;
	}*/

	public static  BusDO convert(Bus b)
	{
		if(b == null)
		{
			return null;
		}
		
		BusDO brd = new BusDO();
		brd.setId(b.getId());
		brd.setRouteId(b.getRouteId());
		brd.setStatus(b.getStatus());
		brd.setCab(b.getCab());
		brd.setDriver(b.getDriver());
		return brd;
	}
	
	public static  Bus convert(BusDO brd )
	{
		if(brd == null)
		{
			return null;
		}
		Bus b = new Bus();
		b.setId(brd.getId());
		b.setRouteId(brd.getRouteId());
		b.setStatus(brd.getStatus());
		b.setCab(brd.getCab());
		b.setDriver(brd.getDriver());
		
		return b;
	}
	
	public static List<Route> convertRoutes(List<RouteDO> rdList)
	{
		if(rdList == null)
		{
			return null;
		}
		List<Route> res = new ArrayList<Route>();
		for(RouteDO rdo : rdList)
		{
			res.add(convert(rdo));
		}
		return res;
	}
	
	public static  CabDO convert(Cab c)
	{
		if(c == null)
		{
			return null;
		}
		CabDO cd = new CabDO();
		cd.setId(c.getId());
		cd.setRegistrationNumber(c.getRegistrationNumber());
		cd.setDescription(c.getDescription());
		cd.setBusType(c.getBusType());
		cd.setAcType(c.getAcType());
		cd.setSeatingCapacity(c.getSeatingCapacity());
		cd.setAvailableSeatingCapacity(c.getAvailableSeatingCapacity());
		
		return cd;
	}
	
	public static  Cab convert(CabDO cd)
	{
		if(cd == null)
		{
			return null;
		}
		Cab c = new Cab();
		c.setId(cd.getId());
		c.setRegistrationNumber(cd.getRegistrationNumber());
		c.setDescription(cd.getDescription());
		c.setBusType(cd.getBusType());
		c.setAcType(cd.getAcType());
		c.setSeatingCapacity(cd.getSeatingCapacity());
		c.setAvailableSeatingCapacity(cd.getAvailableSeatingCapacity());
		
		return c;
	}
	
	public static List<Cab> convertCabs(List<CabDO> cdList)
	{
		if(cdList == null)
		{
			return null;
		}
		List<Cab> res = new ArrayList<Cab>();
		for(CabDO cd : cdList)
		{
			res.add(ObjectConverter.convert(cd));
		}
		return res;
	}
	
	public static  DriverDO convert(Driver cd)
	{
		if(cd == null)
		{
			return null;
		}
		DriverDO cdd = new DriverDO();
		cdd.setId(cd.getId());
		cdd.setName(cd.getName());
		cdd.setEmail(cd.getEmail());
		cdd.setPassword(cd.getPassword());
		cdd.setAddress(cd.getAddress());
		cdd.setDrivingLicense(cd.getDrivingLicense());
		cdd.setGender(cd.getGender());
		cdd.setMobile(cd.getMobile());
		return cdd;
	}
	public static  Driver convert(DriverDO cdd)
	{
		if(cdd == null)
		{
			return null;
		}
		Driver cd = new Driver();
		cd.setId(cdd.getId());
		cd.setName(cdd.getName());
		cd.setEmail(cdd.getEmail());
		cd.setPassword(cdd.getPassword());
		cd.setAddress(cdd.getAddress());
		cd.setDrivingLicense(cdd.getDrivingLicense());
		cd.setGender(cdd.getGender());
		cd.setMobile(cdd.getMobile());
		return cd;
	}
	
	public static List<Driver> convertCabDrivers(List<DriverDO> cddList)
	{
		if(cddList == null)
		{
			return null;
		}
		List<Driver> res = new ArrayList<Driver>();
		for(DriverDO cd : cddList)
		{
			res.add(ObjectConverter.convert(cd));
		}
		return res;
	}
	public static  Stop convert(StopDO std)
	{
		if(std == null)
		{
			return null;
		}
		
		Stop s = new Stop();
		s.setId(std.getId());
		s.setLattitude(std.getLattitude());
		s.setLongitude(std.getLongitude());
		s.setName(std.getName());
		
		return s;
	}
	public static  StopDO convert(Stop st)
	{
		if(st == null)
		{
			return null;
		}
		
		StopDO std = new StopDO();
		std.setId(st.getId());
		std.setLattitude(st.getLattitude());
		std.setLongitude(st.getLongitude());
		std.setName(st.getName());
		
		return std;
	}
	public static List<Stop> convertStops(List<StopDO> stopList)
	{
		if(stopList == null)
		{
			return null;
		}
		List<Stop> res = new ArrayList<Stop>();
		for(StopDO s : stopList)
		{
			res.add(ObjectConverter.convert(s));
		}
		return res;
	}
	
	public static RouteDO convert(Route r)
	{
		RouteDO rdo = new RouteDO();
		rdo.setId(r.getId());
		rdo.setStops(r.getStops());
		
		return rdo;
	}
	public static Route convert(RouteDO rdo)
	{
		Route r = new Route(rdo.getId(), rdo.getStops());
		
		return r;
	}
	
	public static List<Bus> convertBuses(List<BusDO> bdoList)
	{
		List<Bus> result = new ArrayList<Bus>();
		for(BusDO bdo : bdoList)
		{
			result.add(convert(bdo));
		}
		
		return result;
	}
	
	
	public static  BusTiming convert(BusTimingDO btd)
	{
		if(btd == null)
		{
			return null;
		}
		
		BusTiming rs = new BusTiming();
		rs.setId(btd.getId());
		rs.setBusId(btd.getBusId());
		rs.setStop(btd.getStop());
		rs.setTime(btd.getTime());
		
		return rs;
	}
	public static List<BusTiming> convertBusTimings(List<BusTimingDO> btdList)
	{
		if(btdList == null)
		{
			return null;
		}
		List<BusTiming> res = new ArrayList<BusTiming>();
		for(BusTimingDO btd : btdList)
		{
			res.add(ObjectConverter.convert(btd));
		}
		return res;
	}
	public static  BusTimingDO convert(BusTiming bt)
	{
		if(bt == null)
		{
			return null;
		}
		
		BusTimingDO btd = new BusTimingDO();
		btd.setId(bt.getId());
		btd.setBusId(bt.getBusId());
		btd.setStop(bt.getStop());
		btd.setTime(bt.getTime());
		
		return btd;
	}

	private static int getRegistrationStatusAsIntVal(RegistrationStatus registrationStatus)
	{
		int result = -1;

		if(registrationStatus == RegistrationStatus.MFA_PENDING)
		{
			result = 1;
		}
		else if(registrationStatus == RegistrationStatus.MFA_COMPLETE)
		{
			result = 2;
		}
		else if(registrationStatus == RegistrationStatus.REGISTRATION_COMPLETE)
		{
			result = 3;
		}
		return result;
	}
	private static RegistrationStatus getRegistrationStatusFromIntVal(int val)
	{
		RegistrationStatus result = null;

		if(1 == val)
		{
			result = RegistrationStatus.MFA_PENDING;
		}
		else if(2 == val)
		{
			result = RegistrationStatus.MFA_COMPLETE;
		}
		else if(3 == val)
		{
			result = RegistrationStatus.REGISTRATION_COMPLETE;
		}
		return result;
	}
	
}
