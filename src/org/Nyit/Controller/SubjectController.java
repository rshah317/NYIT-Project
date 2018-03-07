package org.Nyit.Controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.Nyit.DAO.SubjectDAO;
import org.Nyit.Utilis.StoreConstant;
import org.Nyit.VO.SubjectVO;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SubjectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private SubjectDAO dao;
	public SubjectController() {
		super();
		dao = new SubjectDAO();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JSONObject jsonObject = new JSONObject();
		if(request.getParameter(StoreConstant.ACTION)!=null && request.getParameter(StoreConstant.ACTION).equalsIgnoreCase(StoreConstant.ACTION_VIEW)){
			RequestDispatcher dispatcher = request.getRequestDispatcher("/setup/setupSubject.jsp");
			dispatcher.forward(request,response);
		}
		else if (request.getParameter(StoreConstant.ACTION)!=null && request.getParameter(StoreConstant.ACTION).equalsIgnoreCase(StoreConstant.ACTION_NEW)){
			try{
				if(request.getParameter("subject_Name")!=null && request.getParameter("subject_Code")!=null){
					  response.setContentType("text/html");
					  Date date = new Date(System.currentTimeMillis());
					  SubjectVO subjectVO = new SubjectVO();
					  subjectVO.setSubjectName(request.getParameter("subject_Name"));	
					  subjectVO.setSubjectCode(request.getParameter("subject_Code"));
					  subjectVO.setCreatedDate(date);
					  subjectVO.setCreatedBy(request.getSession().getAttribute("currentUser").toString());
					  subjectVO.setStatus(StoreConstant.STATUS_ACTIVE);
					  dao.addSubject(subjectVO);
					  jsonObject.put("message","Successfully Added");
					  jsonObject.put("isSuccess", true);
					  response.getWriter().write(jsonObject.toString());
				} else {
					jsonObject.put("message", "Failure parameter missing");
					jsonObject.put("isSuccess", false);
					response.getWriter().write(jsonObject.toString());
				}
			} catch (Throwable e) {
				try {
					jsonObject.put("message", "Failure");
					jsonObject.put("isSuccess", false);
					e.printStackTrace();
					response.getWriter().write(jsonObject.toString());
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
			}
		}
		else if (request.getParameter(StoreConstant.ACTION)!=null && request.getParameter(StoreConstant.ACTION).equalsIgnoreCase(StoreConstant.ACTION_UPDATE)){
			try{
				 if(request.getParameter("subject_Name")!=null && request.getParameter("subject_Code")!=null && request.getParameter("subject_Id")!=null){
					 SubjectVO subjectVO = new SubjectVO();
					 subjectVO.setSubjectId(Integer.parseInt(request.getParameter("subject_Id")));
					 subjectVO.setSubjectName(request.getParameter("subject_Name"));
					 subjectVO.setSubjectCode(request.getParameter("subject_Code"));
					 if("1".equals(request.getParameter("rbtn"))){
						 subjectVO.setStatus(StoreConstant.STATUS_ACTIVE);
					 }else{
						 subjectVO.setStatus(StoreConstant.STATUS_INACTIVE);
					 }
					 dao.updateSubject(subjectVO);
					 jsonObject.put("message", "Updated Successfully");
						jsonObject.put("isSuccess",true);
						response.getWriter().write(jsonObject.toString());
		    		}else{
		    			jsonObject.put("message","Failure Parameter Missing");
						jsonObject.put("isSuccess", false);
						response.getWriter().write(jsonObject.toString());
		    		}
		    	}catch(Throwable e){
		    		try {
						jsonObject.put("message","Oops Something Went Wrong");
						jsonObject.put("isSuccess", false);
						response.getWriter().write(jsonObject.toString());
					} catch (JSONException e1) {
						e1.printStackTrace();
					}
		    	}
		    }
		else if (request.getParameter(StoreConstant.ACTION)!=null && request.getParameter(StoreConstant.ACTION).equalsIgnoreCase(StoreConstant.ACTION_DELETE)){
			try{
	    		 if(request.getParameter("subject_Id")!=null){
	    			 SubjectVO subjectVO = new SubjectVO();
	    			 subjectVO.setSubjectId(Integer.parseInt(request.getParameter("subject_Id")));
	    			 dao.deleteSubject(Integer.parseInt(request.getParameter("subject_Id")));
	    				jsonObject.put("message", "Deleted Successfully");
						jsonObject.put("isSuccess",true);
						response.getWriter().write(jsonObject.toString());
		    		}else{
		    			jsonObject.put("message","Failure Parameter Missing");
						jsonObject.put("isSuccess", false);
						response.getWriter().write(jsonObject.toString());
		    		}
		    	}catch(Throwable e){
		    		try {
						jsonObject.put("message","Oops Something Went Wrong");
						jsonObject.put("isSuccess", false);
						response.getWriter().write(jsonObject.toString());
					} catch (JSONException e1) {
						e1.printStackTrace();
				}
		    }
		}
		else if (request.getParameter(StoreConstant.ACTION)!=null && request.getParameter(StoreConstant.ACTION).equalsIgnoreCase(StoreConstant.ACTION_GET_ROW_DATA)){
			int subject_Id = Integer.parseInt(request.getParameter("subject_Id"));
	    	SubjectVO subjectVO = dao.findSubjectById(subject_Id);
	    	if(subjectVO!=null){
				try {
					response.getWriter().print(subjectVO.getJSONObject().toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
	    	}
		}
		else if (request.getParameter(StoreConstant.ACTION)!=null && request.getParameter(StoreConstant.ACTION).equalsIgnoreCase(StoreConstant.ACTION_GET_PAGE_DATA)){
			int pageNo = Integer.parseInt(request.getParameter("pageNo"));
	    	int pageSize = Integer.parseInt(request.getParameter("pageSize"));
	    	List<SubjectVO> list = dao.getSubjectDetails(pageNo, pageSize);
	    	JSONArray array = new JSONArray();
			for (SubjectVO subjectVO : list) {
				try {
					array.put(subjectVO.getJSONObject());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			try {
				jsonObject.put("pageNo", pageNo);
				jsonObject.put("pageSize", pageSize);
				jsonObject.put("pageData", array);
				jsonObject.put("Total", dao.count());
				response.getWriter().print(jsonObject.toString());
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		else if (request.getParameter(StoreConstant.ACTION)!=null && request.getParameter(StoreConstant.ACTION).equalsIgnoreCase(StoreConstant.ACTION_GET_COMBO_LIST)){
			List<SubjectVO> subject = dao.getSubjectDetails();
			JSONArray array = new JSONArray();
			for (SubjectVO subjectVO : subject) {
				try {
					array.put(subjectVO.getJSONObject());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			response.getWriter().write(array.toString());
		}
	}
}
