package org.example.java15grup2proje.constant;

public class RestApi {
	public static final String VERSION = "/v1";
	public static final String DEVELOPER = "/dev";
	public static final String ROOT = VERSION + DEVELOPER;
	
	public static final String AUTH_EMPLOYEE = ROOT + "/auth/employee";
	public static final String AUTH_MANAGER = ROOT + "/auth/manager";
	public static final String AUTH_ADMIN = ROOT + "/auth/admin";
	public static final String AUTH_COMPANY = ROOT + "/auth/company";
	public static final String AUTH_SHIFT = ROOT + "/auth/shift";
	
	public static final String REGISTER = "/register";
	public static final String LOGIN = "/login";
	public static final String ACCOUNT_ACTIVATION = "/activate";
	public static final String GET_ALL_COMPANY_NAMES = "/company-list";
	public static final String GET_ALL_EMPLOYEE_LIST = "/get-employee-list";
	public static final String ACTIVATE_EMPLOYEE_ACC = "/activate-employee";
	public static final String DELETE_EMPLOYEE = "/delete-employee";
	public static final String GET_MANAGER_CARD = "/get-manager-card";
	public static final String SEND_RECOVERY_MAIL = "/send-recovery-mail";
	public static final String PASSWORD_RECOVERY = "/password-recovery";
	public static final String CREATE_SHIFT = "/create-shift";
	public static final String GET_SHIFT_FOR_MANAGER = "/get-shift-manager";
	public static final String DELETE_SHIFT ="/delete-shift";
	public static final String UPDATE_SHIFT = "/update-shift";
	public static final String GET_SHIFT_FOR_EMPLOYEE = "/get-shift-employee";
	public static final String APP_MAIL_ADDRESS = "corehr@java15.iam.gserviceaccount.com";
}