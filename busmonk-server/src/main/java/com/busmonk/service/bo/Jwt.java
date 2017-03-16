package com.busmonk.service.bo;

/**
 * Created by sr250345 on 11/22/16.
 */
public class Jwt {

    private String token;

    public Jwt(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

	@Override
	public String toString() {
		return "Jwt [token=" + token + "]";
	}
    
    
}
