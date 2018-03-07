package org.Nyit.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.Nyit.DAO.StudentDAO;
import org.Nyit.Utilis.StoreConstant;
import org.Nyit.VO.StudentVO;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDAO dao;
	public StudentController() {
		super();
		dao=new StudentDAO();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JSONObject jsonObject = new JSONObject();
		if(request.getParameter(StoreConstant.ACTION)!=null && request.getParameter(StoreConstant.ACTION).equalsIgnoreCase(StoreConstant.ACTION_VIEW)){
			RequestDispatcher dispatcher = request.getRequestDispatcher("/setup/setupStudent.jsp");
			dispatcher.forward(request, response);
		}
		else if(request.getParameter(StoreConstant.ACTION)!=null && request.getParameter(StoreConstant.ACTION).equalsIgnoreCase(StoreConstant.ACTION_NEW)){
			try {
				if (request.getParameter("campusSubjectId")!=null && request.getParameter("term")!=null && request.getParameter("student_Id")!=null) {
					String[] strs = request.getParameter("campusSubjectId").trim().split(",");
					String[] strsTerm = request.getParameter("term").trim().split(",");
					StudentVO studentVO = new StudentVO();
					studentVO.setStudentId(Integer.parseInt(request.getParameter("student_Id")));
					studentVO.setSubject(strs);
					studentVO.setTerm(strsTerm);
					dao.addStudentSubjectInfo(studentVO);
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
		else if(request.getParameter(StoreConstant.ACTION)!=null && request.getParameter(StoreConstant.ACTION).equalsIgnoreCase(StoreConstant.ACTION_SAVE_STUDENT_INFO)){
			try {
				if (request.getParameter("nyit_Id")!=null && request.getParameter("student_Email")!=null && request.getParameter("student_Lname")!=null && request.getParameter("student_Fname")!=null && request.getParameter("address")!=null && request.getParameter("city")!=null && request.getParameter("state")!=null && request.getParameter("zipcode")!=null && request.getParameter("cellPhone")!=null && request.getParameter("academic")!=null && request.getParameter("campus")!=null) {
					String[] checkBox = request.getParameter("campus").trim().split(",");
					StudentVO studentVO = new StudentVO();
					studentVO.setStudentNyitId(Integer.parseInt(request.getParameter("nyit_Id")));
					studentVO.setEmailId(request.getParameter("student_Email"));
					studentVO.setLastName(request.getParameter("student_Lname"));
					studentVO.setFirstName(request.getParameter("student_Fname"));
					studentVO.setAddress(request.getParameter("address"));
					studentVO.setCity(request.getParameter("city"));
					studentVO.setState(request.getParameter("state"));
					studentVO.setZipcode(Integer.parseInt(request.getParameter("zipcode")));
					if(request.getParameter("homePhone")!=null && request.getParameter("homePhone")!=""){
					studentVO.setHomePhone(Long.parseLong(request.getParameter("homePhone")));
					}else{
						studentVO.setHomePhone(0);
					}
					if(request.getParameter("workPhone")!=null  && request.getParameter("workPhone")!=""){
						studentVO.setWorkPhone(Long.parseLong(request.getParameter("workPhone")));
					}else{
						studentVO.setWorkPhone(0);
					}
					studentVO.setCellPhone(Long.parseLong(request.getParameter("cellPhone")));
					studentVO.setAcademicProgram(request.getParameter("academic"));
					studentVO.setCampus(checkBox);
					dao.addStudent(studentVO);
					jsonObject.put("message", "Successfully Submitted");
					jsonObject.put("isSuccess", true);
					response.getWriter().write(jsonObject.toString());
				} else {
					jsonObject.put("message", "Error In Submitting");
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
		else if (request.getParameter(StoreConstant.ACTION)!=null && request.getParameter(StoreConstant.ACTION).equalsIgnoreCase(StoreConstant.ACTION_GET_ROW_DATA)){
			int nyit_Id = Integer.parseInt(request.getParameter("search"));
			StudentVO  studentVO = dao.findStudentById(nyit_Id);
	    	if(studentVO!=null){
				try {
					response.getWriter().print(studentVO.getJSONObject().toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
	    	}
		}
		else if (request.getParameter(StoreConstant.ACTION)!=null && request.getParameter(StoreConstant.ACTION).equalsIgnoreCase(StoreConstant.ACTION_GET_PAGE_DATA)){
	    	List<StudentVO> list = dao.getStudentDetails();
	    	JSONArray array = new JSONArray();
			for (StudentVO studentVO : list) {
				try {
					array.put(studentVO.getJSONObject());
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
	}
}
