/**
 * 
 */
package demo;

import java.util.List;

import com.busmonk.service.bo.*;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.busmonk.service.AdminService;
import com.busmonk.service.UserService;
import com.busmonk.util.LatitudeLongitude;

/**
 * @author Siddharth Patnaik
 *
 */
public class RouteSearchDemo1 {

	AdminService as;
	UserService us;
	
	static boolean INIT_DATA = true;
	
	public static void main(String[] args) 
	{
		RouteSearchDemo1 demo = new RouteSearchDemo1();
		demo.init();
		
		if(INIT_DATA)
		{
			demo.initData();
		}

		System.out.println(demo.searchRoutes(12.958757, 77.705691, 12.927810, 77.680985));

	}
	
	private void init()
	{
		FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext
				("//Users/SR250345/Documents/siddharth/personal/mybitbucketrepo/busmonk-repository/busmonk-server/src/main/webapp/WEB-INF/spring-context.xml");     //spring4.xml   spring-context"   
		

		context.getBean("adminService");
		
		as = context.getBean(AdminService.class);	
		us = context.getBean(UserService.class);	
	}
	private void initData()
	{
		//Create Stops
		as.createStop(new Stop("domlur", "Domlur bridge", 12.960637, 77.643643));
		as.createStop(new Stop("hmg", "HAL Main Gate", 12.958643, 77.665269));
		as.createStop(new Stop("marathalli", "Marathalli bridge", 12.952535, 77.700074));
		as.createStop(new Stop("jpm", "JP Morgan", 12.942317, 77.697139));
		as.createStop(new Stop("ecospace", "Ecospace", 12.927810,77.680985));
		
		as.createStop(new Stop("mahadevapura", "Mahadevapura", 12.991997, 77.687639));
		as.createStop(new Stop("emc", "Emc", 12.982350, 77.693121));
		
		String driverId1 = "driver1@gmail.com";
		String cabId1 = "cab1";
		String driverId2 = "driver2@gmail.com";
		String cabId2 = "cab2";
		
		
		as.createCab(new Cab(cabId1, "KA 52 MC 8004", "A/C", 12));
		as.createCabDriver(new Driver(driverId1, "Srinibas", "srinibas@gmail.com", "password", "Bangalore"));
		
		as.createCab(new Cab(cabId2, "KA 52 MC 8005", "A/C", 12));
		as.createCabDriver(new Driver(driverId2, "Manoj", "Manoj@gmail.com", "password", "Bangalore"));
		
		String route1 = "DOMLUR-ECOSPACE";
		String route2 = "MAHADEVAPURA-ECOSPACE";
		as.createRoute(new Route(route1, "domlur;hmg;marathalli;jpm;ecospace"));
		
		as.createRoute(new Route(route2, "mahadevapura;emc;marathalli;jpm;ecospace"));
		
		String bus1 = "bus1";
		as.createBus(new Bus(bus1, route1, "ACTIVE", cabId1, driverId1));
		String bus2 = "bus2";
		as.createBus(new Bus(bus2, route2, "ACTIVE", cabId2, driverId2));
		
		//Create RouteSchedule
		as.createBusTiming(new BusTiming("1", bus1, "domlur", "07:30AM"));
		as.createBusTiming(new BusTiming("2", bus1, "hmg", "07:45AM"));
		as.createBusTiming(new BusTiming("3", bus1, "marathalli", "08:00AM"));
		as.createBusTiming(new BusTiming("4", bus1, "jpm", "08:15AM"));
		as.createBusTiming(new BusTiming("5", bus1, "ecospace", "08:30AM"));
		
		as.createBusTiming(new BusTiming("6", bus2, "mahadevapura", "08:30AM"));
		as.createBusTiming(new BusTiming("7", bus2, "emc", "08:45AM"));
		as.createBusTiming(new BusTiming("8", bus2, "marathalli", "09:00AM"));
		as.createBusTiming(new BusTiming("9", bus2, "jpm", "09:15AM"));
		as.createBusTiming(new BusTiming("10", bus2, "ecospace", "09:30AM"));
		
	}
	
	public List<UserRouteDetail> searchRoutes(double sourceLat, double sourcelongd, double destLat, double destlongd)
	{
		LatitudeLongitude src = new LatitudeLongitude(sourceLat, sourcelongd);
		LatitudeLongitude dest = new LatitudeLongitude(destLat, destlongd);
		List<UserRouteDetail> result = us.searchBuses(src, dest);
		return result;
	}
	/*private List<String> searchRoutes(String source, String dest)
	{
		return us.searchRoutes(source, dest);
	}
	public Map<String, Double> searchNearestStops(double lat, double longd, int distance)
	{
		return us.searchNearestStops(lat, longd, distance);
	}*/
}
