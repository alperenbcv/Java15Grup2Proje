package org.example.java15grup2proje.constant;

public class RestApi {
	public static final String VERSION = "/v1";
	public static final String DEVELOPER = "/dev";
	public static final String ROOT = VERSION + DEVELOPER;
	
	public static final String AUTH_EMPLOYEE = ROOT + "/auth/employee";
	public static final String AUTH_MANAGER = ROOT + "/auth/manager";
	public static final String AUTH_ADMIN = ROOT + "/auth/admin";
	public static final String AUTH_COMPANY = ROOT + "/auth/company";
	
	public static final String REGISTER = "/register";
	public static final String LOGIN = "/login";
	public static final String REFRESH_TOKEN = "/refresh-token";
	public static final String GET_ALL_COMPANY_NAMES = "company-list";
	
	public static final String GET_PROFILE = "/get-profile";
	public static final String EDIT_PROFILE = "/edit-profile";
	public static final String EDIT_PHOTO = "/edit-photo";
	public static final String GET_MY_PERSONNEL = "/get-my-personnel";
	public static final String EDIT_MY_PERSONNEL = "/edit-my-personnel";
}