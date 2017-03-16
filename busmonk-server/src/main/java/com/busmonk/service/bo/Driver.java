/**
 * 
 */
package com.busmonk.service.bo;

/**
 * @author Siddharth Patnaik
 *
 */
public class Driver {
	
	private String id;
	private String name;
	private String email;
	private String password;
	private String address;
	private String drivingLicense;
	private String gender;
	private String mobile;
	
	public Driver()
	{
		
	}
	
	public Driver(String id, String name, String email, String password, String address) 
	{
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.address = address;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDrivingLicense() {
		return drivingLicense;
	}
	public void setDrivingLicense(String drivingLicense) {
		this.drivingLicense = drivingLicense;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "Driver [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", address="
				+ address + ", drivingLicense=" + drivingLicense + ", gender=" + gender + ", mobile=" + mobile + "]";
	}
	
	

}
