package demo;

import com.busmonk.service.AdminService;
import com.busmonk.service.UserService;
import com.busmonk.service.bo.*;
import com.busmonk.util.LatitudeLongitude;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.List;

/**
 * Created by sr250345 on 11/10/16.
 */


public class DemoDataPopulator {

    AdminService as;
    UserService us;

    public static void main(String[] args)
    {
        DemoDataPopulator demo = new DemoDataPopulator();
        demo.init();

        demo.initData();
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
        //Create company
        Company c = new Company();
        as.createCompany(new Company("c1", "DigitalInsight"));

        //Create user
        as.createUser(new User("u1", "Tom cruise", "tom@cruise.com", "password", "M", 9999999999l, "c1"));


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

        String route3 = "ECOSPACE-DOMLUR";
        String route4 = "ECOSPACE-MAHADEVAPURA";


        as.createRoute(new Route(route1, "domlur;hmg;marathalli;jpm;ecospace"));
        as.createRoute(new Route(route2, "mahadevapura;emc;marathalli;jpm;ecospace"));
        as.createRoute(new Route(route3, "ecospace;jpm;marathalli;hmg;domlur"));
        as.createRoute(new Route(route4, "ecospace;jpm;marathalli;emc;mahadevapura"));


        String bus1 = "bus1";
        as.createBus(new Bus(bus1, route1, "ACTIVE", cabId1, driverId1));
        String bus2 = "bus2";
        as.createBus(new Bus(bus2, route2, "ACTIVE", cabId2, driverId2));
        String bus3 = "bus3";
        as.createBus(new Bus(bus3, route3, "ACTIVE", cabId1, driverId1));
        String bus4 = "bus4";
        as.createBus(new Bus(bus4, route4, "ACTIVE", cabId2, driverId2));

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

        as.createBusTiming(new BusTiming("11", bus3, "ecospace", "06:00PM"));
        as.createBusTiming(new BusTiming("12", bus3, "jpm", "06:15PM"));
        as.createBusTiming(new BusTiming("13", bus3, "marathalli", "06:30PM"));
        as.createBusTiming(new BusTiming("14", bus3, "hmg", "06:45PM"));
        as.createBusTiming(new BusTiming("15", bus3, "domlur", "07:00PM"));


        as.createBusTiming(new BusTiming("16", bus4, "ecospace", "05:00PM"));
        as.createBusTiming(new BusTiming("17", bus4, "jpm", "05:15PM"));
        as.createBusTiming(new BusTiming("18", bus4, "marathalli", "05:30PM"));
        as.createBusTiming(new BusTiming("19", bus4, "emc", "05:45PM"));
        as.createBusTiming(new BusTiming("20", bus4, "mahadevapura", "06:00PM"));

        //User routes   String id, String name, String userId, String busId, String boardingPoint, String dropPoint
        us.createUserRoute(new UserRoute("1", "home-office", "u001", "bus2", "marathalli", "ecospace"));
        us.createUserRoute(new UserRoute("2", "office-home", "u001", "bus4", "ecospace", "marathalli"));



    }


}
