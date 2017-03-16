import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.busmonk.service.RegistrationService;
import com.busmonk.service.UserService;
import com.busmonk.service.bo.RegistrationStatus;
import com.busmonk.service.bo.User;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class UserServiceTest {

	static RegistrationService registrationService;
	static UserService userService;
	
	String companyId   = "DigitalInsight"; 
	String companyName = "Digital Insight India Pvt Ltd";
	
	long mobile = 9902354300l;
	String password = "password";
	String email = "spatnaik77@gmail.com";
	
	String routeId = "ROUTE-1";
	String busId = "BUS-1";
	//String routeName = "BTM-Ecospace";
	
	String driverId = "Srinibas";
	String cabId = "cab1";

	@BeforeClass
	public static void setup()
	{
		FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext
				("//Users/SR250345/Documents/siddharth/personal/mybitbucketrepo/busmonk-repository/busmonk-server/src/main/webapp/WEB-INF/spring-context.xml");     //spring4.xml   spring-context"   
		

		context.getBean("adminService");

		userService = context.getBean(UserService.class);
		registrationService = context.getBean(RegistrationService.class);
	}
	
	@Test
	public void test() 
	{
		//testCompany();
		
		//testUserRegistration();
		
		//testCabAndDriver();
		
		//testRoute();
		
		//search();
	}
	private void testUserRegistration()
	{
		User u = new User();
		u.setId("1");
		u.setMobile(mobile);
		u = registrationService.createUser(u);
		assertEquals(u.getRegistrationStatus(), RegistrationStatus.MFA_PENDING);

		registrationService.sendOTP(u);

		u.setOtp(123456);
		u = registrationService.varifyOTP(u);
		assertEquals(u.getRegistrationStatus(), RegistrationStatus.MFA_COMPLETE);

		u.setPassword(password);
		u.setEmail(email);
		u = registrationService.updateUser(u);
	}
	/*private void testCompany()
	{
		
		CompanyDO c = new CompanyDO();
		
		c.setId(companyId);
		c.setName(companyName + " some junk");
		c.setAddress1("Eco space, Bellandur, Bangalore - 560103");
		
		dbService.insertCompany(c);	
		
		c = dbService.getCompanyById(companyId);
		assertEquals(companyId, c.getId());
		
		List<CompanyDO> companies = dbService.getCompanies();
		System.out.println("----Companies---------" + companies);
		
		c.setName(companyName);
		dbService.updateCompany(c);
		
		c = dbService.getCompanyById(companyId);
		assertEquals(companyName, c.getName());
	}
	

	
	private void testCabAndDriver()
	{
		//create cab driver
		DriverDO cd = new DriverDO();
		cd.setId(driverId);
		cd.setName("Srinibas");
		cd.updateUser("password");
		cd.setEmail("srinibas@gmail.com");
		//cd.setMobile("7777777777");
		cd.setAddress("Bangalore");//TODO make it optional
		dbService.insertDriver(cd);
		
		//create cab
		CabDO cab = new CabDO();
		cab.setId(cabId);
		cab.setDescription("Tempo traveller, Wifi");
		cab.setSeatingCapacity(12);
		dbService.insertCab(cab);
		
	}

	
	private void testRoute()
	{
		
		StopDO stop1 = new StopDO();
		String stop1Id = "stop-1"; 
		stop1.setId(stop1Id);
		stop1.setName("Marathalli");
		stop1.setLattitude(123);
		stop1.setLongitude(345);
		dbService.insertStop(stop1);
		
		StopDO stop2 = new StopDO();
		stop2.setId("stop-2");
		stop2.setName("Bellandur");
		stop2.setLattitude(123);
		stop2.setLongitude(345);
		dbService.insertStop(stop2);
		
		List<StopDO> res = dbService.getStops();
		assertEquals(2, res.size());
		
		
		RouteDO rdo = new RouteDO();
		rdo.setId(routeId);
		rdo.setStops("stop-1;stop-2");
		dbService.insertRoute(rdo);
		
		List<RouteDO> routes = dbService.getRoutes();
		assertEquals(1, routes.size());
		
		BusDO b = new BusDO();
		b.setId(busId);
		b.setRouteId(routeId);
		b.setStatus("ACTIVE");
		b.setCab(cabId);
		b.setDriver(driverId);
		
		dbService.insertBus(b);
		
		b = dbService.getBus(busId);
		assertEquals(cabId, b.getCab());
		
		
		BusTimingDO btd = new BusTimingDO();
		btd.setId(System.currentTimeMillis()+ "");
		btd.setBusId(busId);
		btd.setStop(stop1Id);
		btd.setTime("07:00 AM");
		
		dbService.insertBusTiming(btd);
		
		List<BusTimingDO> result = dbService.getBusTimingsForBus(busId);
		assertEquals(1, result.size());
		
		//Assign the user route home-office
		UserRouteDO urd = new UserRouteDO();
		urd.setId("" + System.currentTimeMillis());
		urd.setName("home-office");
		urd.setBoardingPoint("SilkBoard");
		urd.setDropPoint("Ecospace");
		urd.setUserId(userId);
		dbService.insertUserRoute(urd);


		
		//Get user's route
		List<UserRouteDO> userRouteDOList = dbService.getUserRoutesForUser(userId);
		assertEquals("SilkBoard", urd.getBoardingPoint());
		assertEquals("Ecospace", urd.getDropPoint());
		
	}
	private void search()
	{
		List<String> result = dbService.searchBus("stop1", "stop2");

	}*/
		

}
