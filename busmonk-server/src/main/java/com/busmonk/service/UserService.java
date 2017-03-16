/**
 * 
 */
package com.busmonk.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.busmonk.config.Config;
import com.busmonk.dal.data.UserRouteDO;
import com.busmonk.security.JWTUtil;
import com.busmonk.service.bo.*;
import com.busmonk.util.Util;
import org.springframework.beans.factory.annotation.Autowired;

import com.busmonk.dal.data.BusTimingDO;
import com.busmonk.dal.data.StopDO;
import com.busmonk.dal.service.DatabaseService;
import com.busmonk.util.LatitudeLongitude;
import com.busmonk.util.ObjectConverter;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.TravelMode;

/**
 * @author Siddharth Patnaik
 *
 */
public class UserService {
	
	@Autowired
	private DatabaseService databaseService;
	@Autowired
	private AdminService adminService;
	
	GeoApiContext context;
	
	public UserService()
	{
		context = new GeoApiContext();
        context.setApiKey(Config.GOOGLE_API_KEY);
        context.setQueryRateLimit(100, 0);
	}

	public UserRoute createUserRoute(UserRoute ur)
	{
		databaseService.insertUserRoute(ObjectConverter.convert(ur));
		return ur;
	}

	public void updateUserRoute(UserRoute ur)
	{
		databaseService.updateUserRoute(ObjectConverter.convert(ur));
	}

	public List<UserRoute> getUserRoutes(String userId)
	{
		List<UserRoute> result = new ArrayList<>();

		List<UserRouteDO>  userRouteDOList =  databaseService.getUserRoutesForUser(userId);

		for(UserRouteDO urdo : userRouteDOList)
		{
			UserRoute ur = new UserRoute();
			ur.setId(urdo.getId());
			ur.setName(urdo.getName());
			ur.setUserId(urdo.getUserId());
			Bus bus = ObjectConverter.convert(databaseService.getBus(urdo.getBusId()));
			Cab cab = ObjectConverter.convert(databaseService.getCab(bus.getCab()));
			ur.setBusId(bus.getId());
			ur.setCab(cab);
			String boardingPoint = ObjectConverter.convert(databaseService.getStop(urdo.getBoardingPoint())).getId();
			String dropPoint = ObjectConverter.convert(databaseService.getStop(urdo.getDropPoint())).getId();
			ur.setBoardingPoint(boardingPoint);
			ur.setDropPoint(dropPoint);
			List<BusTiming> busTimingList = ObjectConverter.convertBusTimings(databaseService.getBusTimingsForBus(urdo.getBusId()));
			ur.setBoardingTime(getStopTime(boardingPoint, busTimingList));
			ur.setDropTime(getStopTime(dropPoint, busTimingList));
			result.add(ur);
		}

		return result;
	}

	public List<UserRouteDetail> getUserRouteDetails(String userId)
	{
		List<UserRouteDetail> result = new ArrayList<>();

		List<UserRouteDO>  userRouteDOList =  databaseService.getUserRoutesForUser(userId);

		for(UserRouteDO urdo : userRouteDOList)
		{
			UserRouteDetail urd = new UserRouteDetail();
			urd.setId(urdo.getId());
			urd.setName(urdo.getName());
			urd.setUserId(urdo.getUserId());
			Bus bus = ObjectConverter.convert(databaseService.getBus(urdo.getBusId()));
			Cab cab = ObjectConverter.convert(databaseService.getCab(bus.getCab()));
			urd.setBus(bus);
			urd.setCab(cab);
			urd.setBoardingPoint(ObjectConverter.convert(databaseService.getStop(urdo.getBoardingPoint())));
			urd.setDropPoint(ObjectConverter.convert(databaseService.getStop(urdo.getDropPoint())));
			urd.setBusTimingList(ObjectConverter.convertBusTimings(databaseService.getBusTimingsForBus(urdo.getBusId())));

			result.add(urd);
		}

		return result;
	}


	public List<UserRouteDetail> searchBuses(LatitudeLongitude src, LatitudeLongitude dest)
	{

		List<UserRouteDetail> result = new ArrayList<UserRouteDetail>();

		Map<Stop, Double> boardingStopsMap = this.searchNearestStops(src, 4);
		Map<Stop, Double> dropStopsMap   = this.searchNearestStops(dest, 4);

		if(boardingStopsMap != null && boardingStopsMap.size() > 0  && dropStopsMap != null && dropStopsMap.size() > 1)
		{
			//Take only 1 nearest destination stop
			Map<Stop, Double> dropStopMap = new LinkedHashMap<Stop, Double>();
			Stop dropStop = null;
			for (Map.Entry<Stop, Double> entry : dropStopsMap.entrySet())
			{
				dropStop = entry.getKey();
				if (dropStop != null) {
					break;
				}

			}

			long walkingTimeFromDropStopToDestination = getWalkingTimeBetweenPoints(new LatitudeLongitude(dropStop.getLattitude(), dropStop.getLongitude()), dest);

			for (Map.Entry<Stop, Double> srcEntry : boardingStopsMap.entrySet()) {
				Stop boardingStop = srcEntry.getKey();


				long walkingTimeFromSourceToPickupStop = getWalkingTimeBetweenPoints(src, new LatitudeLongitude(boardingStop.getLattitude(), boardingStop.getLongitude()));

				List<String> busIds = searchBuses(boardingStop.getId(), dropStop.getId());
				for (String busId : busIds)
				{
					Bus b = adminService.getBus(busId);
					Cab c = adminService.getCab(b.getCab());
					BusTimingDO btDO_src = databaseService.getBusTiming(busId, boardingStop.getId());
					BusTiming bt_src = ObjectConverter.convert(btDO_src);
					BusTimingDO btDO_dest = databaseService.getBusTiming(busId, dropStop.getId());
					BusTiming bt_dest = ObjectConverter.convert(btDO_dest);
					List<BusTiming> busTimingList = ObjectConverter.convertBusTimings(databaseService.getBusTimingsForBus(b.getId()));


					UserRouteDetail urd = new UserRouteDetail();
					urd.setId("");
					urd.setName("");
					urd.setUserId("");
					urd.setWalkingTimeFromSourceToPickupStop(walkingTimeFromSourceToPickupStop);
					urd.setWalkingTimeFromDropStopToDestination(walkingTimeFromDropStopToDestination);
					urd.setCab(c);
					urd.setBusTimingList(busTimingList);
					urd.setBus(b);
					urd.setBoardingPoint(boardingStop);
					urd.setDropPoint(dropStop);

					result.add(urd);
				}

			}

			sortOnWalkingTimeFromAlightStopToDestinationThenOnWalkingTimeFromSourceToPickupStop(result);
		}
		return result;
	}

	public List<String> searchBuses(String source, String dest)
	{
		return databaseService.searchBus(source, dest);
	}
	private Map<Stop, Double> searchNearestStops(LatitudeLongitude p, float distance)
	{
		Map<StopDO, Double> dbResult = databaseService.searchNearestStops(p.getLat(), p.getLongt(), distance);
		Map<Stop, Double> result = new LinkedHashMap<Stop, Double>();
		for(Map.Entry<StopDO, Double> e : dbResult.entrySet())
		{
			result.put(ObjectConverter.convert(e.getKey()), e.getValue());
		}
		return result;
	}
	
	private void sortOnWalkingTimeFromAlightStopToDestinationThenOnWalkingTimeFromSourceToPickupStop(List<UserRouteDetail> result)
	{
		result.sort(Comparator.comparing(UserRouteDetail::getWalkingTimeFromDropStopToDestination).thenComparing(UserRouteDetail::getWalkingTimeFromSourceToPickupStop));
	}
	
	private long getWalkingTimeBetweenPoints(LatitudeLongitude s, LatitudeLongitude d)
	{
		long result = -1;
		
		try 
		{
			String[] sc = {s.getLat() + "," + s.getLongt()};
			String[] dc = {d.getLat() + "," + d.getLongt()};
			//DirectionsResult result = DirectionsApi.getDirections(context, sc, dc).await();
			DistanceMatrixApiRequest req = DistanceMatrixApi.getDistanceMatrix(context, sc, dc);
			req.mode(TravelMode.WALKING);
			DistanceMatrix matrix = req.await();
			long timeInSeconds = matrix.rows[0].elements[0].duration.inSeconds;
			result = timeInSeconds/60;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return result;	
	}

	public List<Stop> getStopsForRoute(String routeId)
	{
		List<Stop> result = new ArrayList<>();

		List<StopDO> stopDOList = databaseService.getStopsForRoute(routeId);
		for(StopDO sdo : stopDOList)
		{
			result.add(ObjectConverter.convert(sdo));
		}

		return result;

	}

	public User getUserById(String userId)
	{
		User u = ObjectConverter.convert(databaseService.getUserById(userId));
		if(u != null)
		{
			u.setPassword(null);
		}
		return u;
	}

	public User getUserByToken(String token)
	{
		User retVal = null;
		User u = JWTUtil.parseJWT(token);
		String userId = u.getId();
		u = this.getUserById(userId);
		if(u != null)
		{
			u.setPassword(null);
			retVal = u;
		}
		return retVal;
	}

	private String getStopTime(String stopId, List<BusTiming> busTimingList)
	{
		String result = null;
		for(BusTiming bt : busTimingList)
		{
			if(bt.getStop().equals(stopId))
			{
				result = bt.getTime();
				break;
			}
		}
		return result;
	}

	/*public User getUserByEmail(String email)
	{
		return ObjectConverter.convert(databaseService.getUserByEmail(email));
	}*/
}
