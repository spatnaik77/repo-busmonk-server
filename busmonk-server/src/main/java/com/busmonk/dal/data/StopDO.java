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
@Table(name="STOP")
public class StopDO {

	@Id
	@Column(name="ID")
	private String id;

	@Column(name="NAME")
	private String name;

	@Column(name="LAT")
	private double lattitude;

	@Column(name="LONGT")
	private double longitude;

	public StopDO()
	{
		
	}
	
	public StopDO(String id, String name, double lattitude, double longitude) {
		super();
		this.id = id;
		this.name = name;
		this.lattitude = lattitude;
		this.longitude = longitude;
	}



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

	public double getLattitude() {
		return lattitude;
	}

	public void setLattitude(double lattitude) {
		this.lattitude = lattitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	
}
