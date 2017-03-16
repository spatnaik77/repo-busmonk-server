/**
 * 
 */
package com.busmonk.service.bo;

/**
 * @author Siddharth Patnaik
 *
 */
public class Route {
	
	private String id;
	private String stops;
	
	public Route(String id, String stops) {
		super();
		this.id = id;
		this.stops = stops;
	}
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
	@Override
	public String toString() {
		return "Route [id=" + id + ", stops=" + stops + "]";
	}
	
	

}
