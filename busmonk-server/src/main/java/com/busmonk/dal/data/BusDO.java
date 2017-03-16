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
@Table(name="BUS")
public class BusDO {
	@Id
	@Column(name="ID")
	private String id;

	@Column(name="ROUTE_ID")
	private String routeId;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="CAB")
	private String cab;
	
	@Column(name="driver")
	private String driver;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCab() {
		return cab;
	}

	public void setCab(String cab) {
		this.cab = cab;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}
	
}
