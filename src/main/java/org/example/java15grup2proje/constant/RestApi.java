package org.example.java15grup2proje.constant;

public class RestApi {
	public static final String VERSION = "/v1";
	public static final String DEVELOPER = "/dev";
	public static final String ROOT = VERSION + DEVELOPER;
	
	public static final String USER = ROOT + "/user";
	public static final String AUTH = ROOT + "/auth";
	
	public static final String AUTH_EMPLOYEE = ROOT + "/auth/employee";
	public static final String AUTH_MANAGER = ROOT + "/auth/manager";
	public static final String AUTH_ADMIN = ROOT + "/auth/admin";
	public static final String AUTH_COMPANY = ROOT + "/auth/company";
	public static final String LEAVE = ROOT + "/leave";
	public static final String POSSESSION = ROOT + "/possession";
	public static final String EXPENSE = ROOT + "/expense";
	public static final String COMMENT = ROOT + "/comment";
	public static final String MEDIA_FILE = ROOT + "/media-file";
	
	public static final String REGISTER = "/register";
	public static final String LOGIN = "/login";
	public static final String REFRESH_TOKEN = "/refresh-token";
	
	public static final String GET_PROFILE = "/get-profile";
	public static final String EDIT_PROFILE = "/edit-profile";
	public static final String EDIT_PHOTO = "/edit-photo";
	public static final String GET_MY_PERSONNEL = "/get-my-personnel";
	public static final String EDIT_MY_PERSONNEL = "/edit-my-personnel";
	
	public static final String ADD_LEAVE = "/add-leave";
	public static final String GET_PENDING_LEAVES = "/get-pending-leaves";
	public static final String MANAGE_LEAVE = "/manage-leave";
	public static final String GET_LEAVES_BY_MANAGER = "/get-leaves-by-manager";
	
	public static final String GET_ALL_COMPANY_NAMES = "/company-list";
	public static final String GET_ALL_COMPANIES = "/get-all-companies";
	public static final String MANAGE_COMPANY_REGISTER_STATE = "/manage-company-register-state";
	
	public static final String MANAGE_POSSESSION = "/manage-possession";
	public static final String GET_MY_POSSESSIONS = "/get-my-possessions";
	
	public static final String MANAGE_EXPENSE = "/manage-expense";
	public static final String GET_MY_EXPENSES = "/get-my-expenses";
	public static final String GET_MY_EMPLOYEES_EXPENSES = "/get-my-employees-expenses";
	public static final String ADD_EXPENSE = "/add-expense";
	
	public static final String ADD_COMMENT = "/add-comment";
	public static final String DELETE_COMMENT = "/delete-comment";
	public static final String EDIT_COMMENT =  "/edit-comment";
	public static final String GET_COMMENT =  "/get-comment";
	public static final String GET_ALL_COMMENTS =  "/get-all-comments";
	
	public static final String UPLOAD_MEDIA = "/upload-media";
}