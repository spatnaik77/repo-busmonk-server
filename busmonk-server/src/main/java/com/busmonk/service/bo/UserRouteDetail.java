package com.busmonk.service.bo;

import java.util.*;

/**
 * Created by sr250345 on 11/10/16.
 */
public class UserRouteDetail {

    private String id;
    private String name;
    private String userId;
    private Bus bus;
    private Cab cab;
    private Stop boardingPoint;
    private Stop dropPoint;
    private List<BusTiming> busTimingList;

    private long walkingTimeFromSourceToPickupStop;
    private long walkingTimeFromDropStopToDestination;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public Stop getBoardingPoint() {
        return boardingPoint;
    }

    public void setBoardingPoint(Stop boardingPoint) {
        this.boardingPoint = boardingPoint;
    }

    public Stop getDropPoint() {
        return dropPoint;
    }

    public void setDropPoint(Stop dropPoint) {
        this.dropPoint = dropPoint;
    }

    public List<BusTiming> getBusTimingList() {
        return busTimingList;
    }

    public void setBusTimingList(List<BusTiming> busTimingList) {
        this.busTimingList = busTimingList;
    }

    public Cab getCab() {
        return cab;
    }

    public void setCab(Cab cab) {
        this.cab = cab;
    }

    public long getWalkingTimeFromSourceToPickupStop() {
        return walkingTimeFromSourceToPickupStop;
    }

    public void setWalkingTimeFromSourceToPickupStop(long walkingTimeFromSourceToPickupStop) {
        this.walkingTimeFromSourceToPickupStop = walkingTimeFromSourceToPickupStop;
    }

    public long getWalkingTimeFromDropStopToDestination() {
        return walkingTimeFromDropStopToDestination;
    }

    public void setWalkingTimeFromDropStopToDestination(long walkingTimeFromDropStopToDestination) {
        this.walkingTimeFromDropStopToDestination = walkingTimeFromDropStopToDestination;
    }

	@Override
	public String toString() {
		return "UserRouteDetail [id=" + id + ", name=" + name + ", userId=" + userId + ", bus=" + bus + ", cab=" + cab
				+ ", boardingPoint=" + boardingPoint + ", dropPoint=" + dropPoint + ", busTimingList=" + busTimingList
				+ ", walkingTimeFromSourceToPickupStop=" + walkingTimeFromSourceToPickupStop
				+ ", walkingTimeFromDropStopToDestination=" + walkingTimeFromDropStopToDestination + "]";
	}
    
    
}
