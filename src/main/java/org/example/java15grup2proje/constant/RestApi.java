package org.example.java15grup2proje.constant;

public class RestApi {
	public static final String VERSION = "/v1";
	public static final String DEVELOPER = "/dev";
	public static final String ROOT = VERSION + DEVELOPER;
	
	public static final String USER = ROOT + "/user";
	
	public static final String REGISTER = ROOT + "/register";
	public static final String LOGIN = ROOT + "/login";
}