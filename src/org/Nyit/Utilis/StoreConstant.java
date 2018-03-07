package org.Nyit.Utilis;

public class StoreConstant {
	
	public static final String MODULE="module";
	public static final String ACTION="action";
	public static final String STATUS_ACTIVE="ACTIVE";
	public static final String STATUS_INACTIVE="INACTIVE";
	public static final String STATUS_APPROVED="approved";
	public static final String STATUS_PENDING="pending";
	public static final String STATUS_DECLINE="decline";
	public static final String SUBJECT="DO NOT REPLY-Approval For Registration";
	
	//Module
	public static final String MOD_CAMPUS="campus";
	public static final String MOD_USER="user";
	public static final String MOD_HOME="home";
	public static final String MOD_MAJOR="major";
	public static final String MOD_CAMPUS_SUBJECT_MAPPING="campussubjectmapping";
	public static final String MOD_SUBJECT="subject";
	public static final String MOD_STUDENTS="student";
	public static final String MOD_SECTION="section";
	public static final String MOD_SECTION_SUBJECT_MAPPING="sectionsubjectmapping";
	public static final String MOD_CAMPUS_SCHEDULE="campusschedule";
	public static final String MOD_CAMPUS_SECTION_SEAT_EVALUATION="campussectionseatevaluation";
	public static final String MOD_STUDENT_REQUEST="studentrequest";
	// Servlet
	public static final String SERVLET_BASE="base";
	public static final String SERVLET_CAMPUS="campus";
	public static final String SERVLET_LOGIN="login";
	public static final String SERVLET_HOME="home";
	public static final String SERVLET_MAJOR="major";
	public static final String SERVLET_CAMPUS_SUBJECT_MAPPING="campussubjectmapping";
	public static final String SERVLET_SUBJECT="subject";
	public static final String SERVLET_STUDENT="student";
	public static final String SERVLET_SECTION="section";
	public static final String SERVLET_SECTION_SUBJECT_MAPPING="sectionsubjectmapping";
	public static final String SERVLET_CAMPUS_SCHEDULE="campusschedule";
	public static final String SERVLET_CAMPUS_SECTION_SEAT_EVALUATION="campussectionseatevaluation";
	public static final String SERVLET_STUDENT_REQUEST="studentrequest";

	
	// Action
	public static final String ACTION_NEW="new";
	public static final String ACTION_REGISTRATION="register";
	public static final String ACTION_LOGIN="login";
	public static final String ACTION_LOGOUT="logout";
	public static final String ACTION_VIEW="view";
	public static final String ACTION_UPDATE="update";
	public static final String ACTION_DELETE="delete";
	public final static String ACTION_GET_ROW_DATA="getRowData";
	public final static String ACTION_GET_PAGE_DATA="pageData";
	public final static String ACTION_GET_COMBO_LIST="combolist"; 
	public final static String ACTION_GET_SUBJECT_MAJOR_DETAILS="subjectmajorDetails";
	public final static String ACTION_GET_SUBJECT_DAY_TIME_DETAILS="subjectScheduleDetails"; 
	public final static String ACTION_SAVE_STUDENT_INFO="studentInfoNew";
	public final static String ACTION_SAVE_APPROVED_STUDENT="saveApprovedStudents";
	public final static String ACTION_UPDATE_STATUS="updateStatus";
	public final static String ACTION_SEND_DECLINE_MAIL="declineStudent";
}
