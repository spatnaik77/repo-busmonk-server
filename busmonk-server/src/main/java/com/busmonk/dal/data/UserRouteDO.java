/**
 * 
 */
package com.busmonk.dal.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Siddharth Patnaik
 *
 */
@Entity
@Table(name="USER_ROUTE")
public class UserRouteDO {
	
	@Id
	@Column(name="ID")
	private String id;

	@Column(name="NAME")
	private String name;

	@Column(name="USER_ID")
	private String userId;
	
	@Column(name="BUS_ID")
	private String busId;
	
	@Column(name="BOARDING_POINT")
	private String boardingPoint;
	
	@Column(name="DROP_POINT")
	private String dropPoint;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBoardingPoint() {
		return boardingPoint;
	}
	public void setBoardingPoint(String boardingPoint) {
		this.boardingPoint = boardingPoint;
	}
	public String getDropPoint() {
		return dropPoint;
	}
	public void setDropPoint(String dropPoint) {
		this.dropPoint = dropPoint;
	}

	public String getBusId() {
		return busId;
	}

	public void setBusId(String busId) {
		this.busId = busId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
