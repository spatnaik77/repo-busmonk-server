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
@Table(name="USER")
public class UserDO {
	
	@Id
	@Column(name="ID")
	private String id;

	@Column(name="MOBILE")
	private long mobile;

	@Column(name="OTP")
	private int otp;

	@Column(name="REGISTRATION_STATUS")
	private int registrationStatus;
	
	@Column(name="COMPANY_ID")	
	private String companyId;
	
	@Column(name="NAME")	
	private String name;
	
	@Column(name="EMAIL")	
	private String email;
	
	@Column(name="PASSWORD")	
	private String password;
	
	@Column(name="GENDER")	
	private String gender;
	
	@Column(name="ROLES")
	private String roles;
	
	@Column(name="HOME_ADDRESS")
	private String homeAddress;
	
	@Column(name="HOME_ADDRESS_COORDINATES")
	private String homeAddressCoordinates;
	
	@Column(name="OFFICE_ADDRESS")
	private String officeAddress;
	
	@Column(name="OFFICE_ADDRESS_COORDINATES")
	private String officeAddressCoordinates;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public long getMobile() {
		return mobile;
	}

	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getHomeAddressCoordinates() {
		return homeAddressCoordinates;
	}

	public void setHomeAddressCoordinates(String homeAddressCoordinates) {
		this.homeAddressCoordinates = homeAddressCoordinates;
	}

	public String getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}

	public String getOfficeAddressCoordinates() {
		return officeAddressCoordinates;
	}

	public void setOfficeAddressCoordinates(String officeAddressCoordinates) {
		this.officeAddressCoordinates = officeAddressCoordinates;
	}

	public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}

	public int getRegistrationStatus() {
		return registrationStatus;
	}

	public void setRegistrationStatus(int registrationStatus) {
		this.registrationStatus = registrationStatus;
	}
}
