package org.Nyit.Controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.Nyit.DAO.SectionSubjectMappingDAO;
import org.Nyit.Utilis.StoreConstant;
import org.Nyit.VO.SectionSubjectMappingVO;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SectionSubjectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private SectionSubjectMappingDAO dao;   
    public SectionSubjectController() {
        super();
        dao = new SectionSubjectMappingDAO();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			JSONObject jsonObject = new JSONObject();
		if(request.getParameter(StoreConstant.ACTION)!=null && request.getParameter(StoreConstant.ACTION).equalsIgnoreCase(StoreConstant.ACTION_VIEW)){
			RequestDispatcher dispatcher = request.getRequestDispatcher("/setup/setupSectionSubject.jsp");
			dispatcher.forward(request, response);
		}
		else if(request.getParameter(StoreConstant.ACTION)!=null && request.getParameter(StoreConstant.ACTION).equalsIgnoreCase(StoreConstant.ACTION_NEW)){
			try {
				if (request.getParameter("campusSubjectSelect")!=null && request.getParameter("checkBoxValue")!=null) {
					String[] strs = request.getParameter("checkBoxValue").trim().split(",");
					int [] value = new  int [strs.length];
					for (int i = 0; i < strs.length; i++) {
						value[i] = Integer.parseInt(strs[i]);
					}
					Date date = new Date(System.currentTimeMillis());
					SectionSubjectMappingVO sectionSubjectMappingVO = new SectionSubjectMappingVO();
					sectionSubjectMappingVO.setCampusSubjectId(Integer.parseInt(request.getParameter("campusSubjectSelect")));
					sectionSubjectMappingVO.setSectionId(value);
					sectionSubjectMappingVO.setCreatedDate(date);
					sectionSubjectMappingVO.setCreatedBy(request.getSession().getAttribute("currentUser").toString());
					sectionSubjectMappingVO.setStatus(StoreConstant.STATUS_ACTIVE);
					dao.addSectionSubjectSchedule(sectionSubjectMappingVO);
					jsonObject.put("message", "Successfully Added");
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
	    		if(request.getParameter("sectionSubjectSchedule_Id")!=null && request.getParameter("campusSubjectSelect")!=null && request.getParameter("checkBoxValue")!=null){
	    			SectionSubjectMappingVO sectionSubjectMappingVO = new SectionSubjectMappingVO();
	    			
	    			String[] strs = request.getParameter("checkBoxValue").trim().split(",");
					int [] value = new  int [strs.length];
					for (int i = 0; i < strs.length; i++) {
						value[i] = Integer.parseInt(strs[i]);
					}
					sectionSubjectMappingVO.setSectionSubjectScheduleId(Integer.parseInt(request.getParameter("sectionSubjectSchedule_Id")));
					sectionSubjectMappingVO.setCampusSubjectId(Integer.parseInt(request.getParameter("campusSubjectSelect")));
					sectionSubjectMappingVO.setSectionId(value);
	    			if("1".equals(request.getParameter("sectionsubject_rbtn"))){
	    				sectionSubjectMappingVO.setStatus(StoreConstant.STATUS_ACTIVE);
					}else{
						sectionSubjectMappingVO.setStatus(StoreConstant.STATUS_INACTIVE);
					}
	    			dao.updateSectionSubjectSchedule(sectionSubjectMappingVO);
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
	    		 if(request.getParameter("sectionSubjectSchedule_Id")!=null){
	    			 SectionSubjectMappingVO sectionSubjectMappingVO = new SectionSubjectMappingVO();
	    			 sectionSubjectMappingVO.setSectionSubjectScheduleId(Integer.parseInt(request.getParameter("sectionSubjectSchedule_Id")));
	    			 dao.deleteSectionSubjectSchedule(Integer.parseInt(request.getParameter("sectionSubjectSchedule_Id")));
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
			int sectionSubjectSchedule_Id = Integer.parseInt(request.getParameter("sectionSubjectSchedule_Id"));
			SectionSubjectMappingVO sectionSubjectMappingVO = dao.findSectionSubjectScheduleById(sectionSubjectSchedule_Id);
	    	if(sectionSubjectMappingVO!=null){
				try {
					response.getWriter().print(sectionSubjectMappingVO.getJSONObject().toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
	    	}
		}
		else if (request.getParameter(StoreConstant.ACTION)!=null && request.getParameter(StoreConstant.ACTION).equalsIgnoreCase(StoreConstant.ACTION_GET_PAGE_DATA)){
			int pageNo = Integer.parseInt(request.getParameter("pageNo"));
	    	int pageSize = Integer.parseInt(request.getParameter("pageSize"));
	    	List<SectionSubjectMappingVO> list = dao.getSectionSubjectScheduleDetails(pageNo, pageSize);  	
	    	JSONArray array = new JSONArray();
			for (SectionSubjectMappingVO sectionSubjectMappingVO : list) {
				try {
					array.put(sectionSubjectMappingVO.getJSONObject());
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
			List<SectionSubjectMappingVO> sectionSubject = dao.getSectionSubjectScheduleDetails();
			JSONArray array = new JSONArray();
			for (SectionSubjectMappingVO sectionSubjectMappingVO : sectionSubject) {
				try {
					array.put(sectionSubjectMappingVO.getJSONObject());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			response.getWriter().write(array.toString());
		}
	}
}
