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
@Table(name="CAB")
public class CabDO {
	
	@Id
	@Column(name="ID")
	private String id;
	
	@Column(name="REGISTRATION_NUMBER")
	private String registrationNumber;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="BUS_TYPE")
	private String busType;
	
	@Column(name="AC_TYPE")
	private String acType;
	
	@Column(name="SEATING_CAPACITY")
	private int seatingCapacity;
	
	@Column(name="AVAILABLE_SEATING_CAPACITY")
	private int availableSeatingCapacity;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSeatingCapacity() {
		return seatingCapacity;
	}

	public void setSeatingCapacity(int seatingCapacity) {
		this.seatingCapacity = seatingCapacity;
	}

	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	public String getAcType() {
		return acType;
	}

	public void setAcType(String acType) {
		this.acType = acType;
	}

	public int getAvailableSeatingCapacity() {
		return availableSeatingCapacity;
	}

	public void setAvailableSeatingCapacity(int availableSeatingCapacity) {
		this.availableSeatingCapacity = availableSeatingCapacity;
	}
	
	
	
}
