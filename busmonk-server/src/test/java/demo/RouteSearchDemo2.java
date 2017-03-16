/**
 * 
 */
package demo;

import java.util.List;

import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.busmonk.dal.service.DatabaseService;
import com.busmonk.service.AdminService;
import com.busmonk.service.UserService;
import com.busmonk.service.bo.Cab;
import com.busmonk.service.bo.Driver;
import com.busmonk.service.bo.Route;
import com.busmonk.service.bo.Bus;
import com.busmonk.service.bo.BusTiming;
import com.busmonk.service.bo.Stop;

/**
 * @author Siddharth Patnaik
 *
 */
public class RouteSearchDemo2 {

	AdminService as;
	UserService us;
	
	static boolean INIT_DATA = false;
	
	public static void main(String[] args) 
	{
		RouteSearchDemo2 demo = new RouteSearchDemo2();
		demo.init();
		
		if(INIT_DATA)
		{
			demo.initData();
		}
		String src = "Kadugodi Railway Station" ; String dest = "Adarsh palm Meadows";
		System.out.println(src + " To " + dest + "\t Routes : " + demo.searchRoutes(src, dest));
		
		
		
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
		String[] stops = {"Kadugodi Railway Station", "Hope farm Junction", "Whitefield ( Mayura Bakery )", 
							"Forum Value Mall", "Adarsh palm Meadows", "Kundanhalli Gate", "Spice Garden", 
							"Marathalli Bridge (Brand factory)", "Marathalli Market (Canara Bank)", "Bagmane Tech Park (BTP)"};
		
		//Create Stops
		as.createStop(new Stop(stops[0], stops[0], 12.997451, 77.761381));
		as.createStop(new Stop(stops[1], stops[1], 12.984122, 77.752396));
		as.createStop(new Stop(stops[2], stops[2], 12.970186, 77.750047));
		as.createStop(new Stop(stops[3], stops[3], 12.959746, 77.747961));
		as.createStop(new Stop(stops[4], stops[4], 12.957303, 77.740306));
		as.createStop(new Stop(stops[5], stops[5], 12.956066, 77.713950));
		as.createStop(new Stop(stops[6], stops[6], 12.956435, 77.708864));
		as.createStop(new Stop(stops[7], stops[7], 12.957021, 77.699782));
		as.createStop(new Stop(stops[8], stops[8], 12.956324, 77.695499));
		as.createStop(new Stop(stops[9], stops[9], 12.982797, 77.693070));
		
		String driverId = "driver1@gmail.com";
		String cabId = "cab1";
		
		String route1 = "KADUGODI-BTP";
		String stopsStr  = stops[0] + ";" + stops[1] + ";" + stops[2] + ";" +stops[3] +";"+stops[4]  + ";" 
						+ stops[5] + ";" + stops[6] + ";" + stops[7] + ";" + stops[8] + ";" + stops[9] ;
		as.createRoute(new Route(route1, stopsStr));
		
		
		as.createCab(new Cab(cabId, "KA 52 MC 8004", "A/C", 12));
		as.createCabDriver(new Driver(driverId, "Srinibas", "srinibas@gmail.com", "password", "Bangalore"));
		
		String busId = "BUS1";
		as.createBus(new Bus(busId, route1, "ACTIVE", cabId, driverId));
		
		//Create RouteSchedule
		as.createBusTiming(new BusTiming("1", busId, stops[0], "07:55AM"));
		as.createBusTiming(new BusTiming("2", busId, stops[1], "08:05AM"));
		as.createBusTiming(new BusTiming("3", busId, stops[2], "08:10AM"));
		as.createBusTiming(new BusTiming("4", busId, stops[3], "08:12AM"));
		as.createBusTiming(new BusTiming("5", busId, stops[4], "08:15AM"));
		as.createBusTiming(new BusTiming("6", busId, stops[5], "08:23AM"));
		as.createBusTiming(new BusTiming("7", busId, stops[6], "08:25AM"));
		as.createBusTiming(new BusTiming("8", busId, stops[7], "08:27AM"));
		as.createBusTiming(new BusTiming("9", busId, stops[8], "08:29AM"));
		as.createBusTiming(new BusTiming("10", busId, stops[9], "09:15AM"));
	}
	
	private List<String> searchRoutes(String source, String dest)
	{
		return us.searchBuses(source, dest);
	}
	
	

}
