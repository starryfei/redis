package com.starryfei.service;

public interface UserImpl {
	
	public boolean checkLogin(String name, String pwd);
	
	public boolean register(String name, String pwd);
	
	
}
