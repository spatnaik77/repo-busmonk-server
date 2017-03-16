/**
 * 
 */
package com.busmonk.util;

/**
 * @author Siddharth Patnaik
 *
 */
public class LatitudeLongitude {
	
	private double lat;
	private double longt;
	public LatitudeLongitude(double lat, double longt) {
		super();
		this.lat = lat;
		this.longt = longt;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLongt() {
		return longt;
	}
	public void setLongt(double longt) {
		this.longt = longt;
	}

}
