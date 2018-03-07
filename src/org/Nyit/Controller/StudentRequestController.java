package org.Nyit.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.Nyit.DAO.StudentRequestDAO;
import org.Nyit.Utilis.EmailUtilis;
import org.Nyit.Utilis.StoreConstant;
import org.Nyit.VO.StudentRegistrationRequestVO;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class StudentRequestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentRequestDAO dao;
	private String host;
	private String port;
	private String user;
	private String password;
	
	public StudentRequestController() {
		super();
		dao = new StudentRequestDAO();
	}
	
	public void init() {
		// reads SMTP server setting from web.xml file
		ServletContext context = getServletContext();
		host = context.getInitParameter("host");
		port = context.getInitParameter("port");
		user = context.getInitParameter("user");
		password = context.getInitParameter("password");
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JSONObject jsonObject = new JSONObject();
		if(request.getParameter(StoreConstant.ACTION)!=null && request.getParameter(StoreConstant.ACTION).equalsIgnoreCase(StoreConstant.ACTION_VIEW)){
			RequestDispatcher dispatcher = request.getRequestDispatcher("/setup/setupStudentRequest.jsp");
			dispatcher.forward(request, response);
		}
		else if(request.getParameter(StoreConstant.ACTION)!=null && request.getParameter(StoreConstant.ACTION).equalsIgnoreCase(StoreConstant.ACTION_NEW)){
			try {
				  StudentRegistrationRequestVO registrationRequestVO = new StudentRegistrationRequestVO();
				if (request.getParameter("dayTime")!=null && request.getParameter("student_Id")!=null) {
					String [] srt = request.getParameter("dayTime").trim().split(",");
					int [] value = new  int [srt.length];
					  for (int i = 0; i < srt.length; i++) {
					  value[i] = Integer.parseInt(srt[i]);
					  }
					registrationRequestVO.setStudent_Id(Integer.parseInt(request.getParameter("student_Id")));
					registrationRequestVO.setSchedule_Id(value);
					registrationRequestVO.setStatus(StoreConstant.STATUS_PENDING);
					dao.addStudentRequest(registrationRequestVO);
					jsonObject.put("message", "Successfully Saved Your Information");
					jsonObject.put("isSuccess", true);
					response.getWriter().write(jsonObject.toString());
				} else {
					jsonObject.put("message", "Your Information Not saved successfully");
					jsonObject.put("isSuccess", false);
					response.getWriter().write(jsonObject.toString());
				}
			} catch (Throwable e) {
				try {
					jsonObject.put("message", "Error");
					jsonObject.put("isSuccess", false);
					e.printStackTrace();
					response.getWriter().write(jsonObject.toString());
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
			}
		}
		else if (request.getParameter(StoreConstant.ACTION)!=null && request.getParameter(StoreConstant.ACTION).equalsIgnoreCase(StoreConstant.ACTION_GET_PAGE_DATA)){
	    	List<StudentRegistrationRequestVO> list = dao.getStudentRequestDetails();
	    	JSONArray array = new JSONArray();
			for (StudentRegistrationRequestVO registrationRequestVO : list) {
				try {
					array.put(registrationRequestVO.getJSONObject());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			try {
				jsonObject.put("pageData", array);
				response.getWriter().print(jsonObject.toString());
			} catch (JSONException e) {
				e.printStackTrace();
			}
				
		}
		else if (request.getParameter(StoreConstant.ACTION)!=null && request.getParameter(StoreConstant.ACTION).equalsIgnoreCase(StoreConstant.ACTION_SAVE_APPROVED_STUDENT)){
			try {
				   int studentId = Integer.parseInt(request.getParameter("student_Id"));
				   StudentRegistrationRequestVO registrationRequestVO = dao.findStudentsById(studentId);
				   if(registrationRequestVO!=null){
					   registrationRequestVO.setStatus(StoreConstant.STATUS_APPROVED);
					   registrationRequestVO.setStudent_Id(studentId);
					   dao.updateStatus(studentId);
					   dao.saveApprovedSubject(registrationRequestVO); 
					  String EMAIL_MESSAGE="Hi"+" "+registrationRequestVO.getFirstName()+"\n"+"Your registration hold has been removed, please only register for the classes you have selected in the registration form.\n DO NOT REPLY on this email. If you have any doubt about registration process please Email us on soecsgrad@nyit.edu"+"\n\n\n\n"+"Thank you,"+"\n"+"Graduate Advisor" ;	 
						EmailUtilis.sendEmail(host, port, user, password,registrationRequestVO.getEmail_Id(),StoreConstant.SUBJECT,
									EMAIL_MESSAGE);
     				jsonObject.put("message", "Successfully Saved Your Information");
					jsonObject.put("isSuccess", true);
					response.getWriter().write(jsonObject.toString());
				   } else {
					jsonObject.put("message", "Your Information Not saved successfully");
					jsonObject.put("isSuccess", false);
					response.getWriter().write(jsonObject.toString());
				   }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		else if (request.getParameter(StoreConstant.ACTION)!=null && request.getParameter(StoreConstant.ACTION).equalsIgnoreCase(StoreConstant.ACTION_UPDATE_STATUS)){
			try{
				StudentRegistrationRequestVO registrationRequestVO = new StudentRegistrationRequestVO();
				int studentId = Integer.parseInt(request.getParameter("student_Id"));
				dao.updateDeclineStatus(studentId);
				jsonObject.put("message", "Successfully Saved Your Information");
				jsonObject.put("isSuccess", true);
				response.getWriter().write(jsonObject.toString());
			}catch (Exception e) {
				try {
					jsonObject.put("message", "Error");
					jsonObject.put("isSuccess", false);
					e.printStackTrace();
					response.getWriter().write(jsonObject.toString());
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
			}
		}
		else if (request.getParameter(StoreConstant.ACTION)!=null && request.getParameter(StoreConstant.ACTION).equalsIgnoreCase(StoreConstant.ACTION_SEND_DECLINE_MAIL)){
			try{
				int studentId = Integer.parseInt(request.getParameter("student_Id"));
				String to = request.getParameter("to");
				String message = request.getParameter("message");
				
			}catch (Exception e) {
				try {
					jsonObject.put("message", "Error");
					jsonObject.put("isSuccess", false);
					e.printStackTrace();
					response.getWriter().write(jsonObject.toString());
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

}
