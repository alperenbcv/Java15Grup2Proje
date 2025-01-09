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
	public static final String AUTH_SHIFT = ROOT + "/auth/shift";
	public static final String LEAVE = ROOT + "/leave";
	public static final String POSSESSION = ROOT + "/possession";
	public static final String EXPENSE = ROOT + "/expense";
	public static final String COMMENT = ROOT + "/comment";
	public static final String MEDIA_FILE = ROOT + "/media-file";
	public static final String PERSONNEL_FILE = "/personnel-file";
	
	public static final String REGISTER = "/register";
	public static final String LOGIN = "/login";
	public static final String REFRESH_TOKEN = "/refresh-token";
	
	public static final String GET_PROFILE = "/get-profile";
	public static final String EDIT_PROFILE = "/edit-profile";
	public static final String EDIT_PHOTO = "/edit-photo";
	public static final String GET_MY_PERSONNEL = "/get-my-personnel";
	public static final String EDIT_MY_PERSONNEL = "/edit-my-personnel";
	public static final String GET_MANAGER_BY_COMMENT = "/get-manager-by-comment";
	
	public static final String ADD_LEAVE = "/add-leave";
	public static final String GET_PENDING_LEAVES = "/get-pending-leaves";
	public static final String MANAGE_LEAVE = "/manage-leave";
	public static final String GET_LEAVES_BY_MANAGER = "/get-leaves-by-manager";
	
	public static final String GET_ALL_COMPANY_NAMES = "/company-list";
	public static final String GET_ALL_COMPANIES = "/get-all-companies";
	public static final String MANAGE_COMPANY_REGISTER_STATE = "/manage-company-register-state";
	public static final String GET_COMPANY_BY_COMMENT = "/get-company-by-comment";
	
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
	public static final String UPLOAD_PROFILE_PICTURE = "/upload-profile-picture";
	public static final String GET_MY_EMPLOYEES = "/get-my-employees";
	public static final String ADD_EMPLOYEE = "/add-employee";
	
	public static final String GET_FILES_OF_MY_PERSONNEL = "/get-files";
	public static final String UPLOAD_PERSONNEL_FILE = "/upload-file";
	public static final String DEACTIVATE_EMPLOYEE = "/deactivate-employee";
	
	
	public static final String GET_MANAGER_CARD = "/get-manager-card";
	public static final String SEND_RECOVERY_MAIL = "/send-recovery-mail";
	public static final String PASSWORD_RECOVERY = "/password-recovery";
	public static final String CREATE_SHIFT = "/create-shift";
	public static final String GET_SHIFT_FOR_MANAGER = "/get-shift-manager";
	public static final String DELETE_SHIFT ="/delete-shift";
	public static final String UPDATE_SHIFT = "/update-shift";
	public static final String GET_SHIFT_FOR_EMPLOYEE = "/get-shift-employee";
	public static final String APP_MAIL_ADDRESS = "corehr@java15.iam.gserviceaccount.com";
	public static final String ACTIVATE_EMPLOYEE_ACC = "/activate-employee";
	public static final String ACCOUNT_ACTIVATION = "/activate";
	public static final String ADD_NEW_PERSONNEL_FILE = "/add-new-personnel-file";
	public static final String ALTER_ACCOUNT_ACTIVATION= "/alter-account-activation";
	public static final String ADD_POSSESSION = "/add-possession";
	public static final String GET_MY_EMPLOYEES_POSSESSIONS = "/get-my-employees-possessions";
}