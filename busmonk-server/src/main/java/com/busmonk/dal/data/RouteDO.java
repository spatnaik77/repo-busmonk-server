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
@Table(name="ROUTE")
public class RouteDO {
	
	@Id
	@Column(name="ID")
	private String id;

	@Column(name="STOPS")
	private String stops;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStops() {
		return stops;
	}

	public void setStops(String stops) {
		this.stops = stops;
	}
	
	
}
