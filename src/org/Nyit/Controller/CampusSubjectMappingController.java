package org.Nyit.Controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.Nyit.DAO.CampusSubjectMappingDAO;
import org.Nyit.Utilis.StoreConstant;
import org.Nyit.VO.CampusSubjectMappingVO;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CampusSubjectMappingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CampusSubjectMappingDAO dao;    
    public CampusSubjectMappingController() {
        super();
        dao = new CampusSubjectMappingDAO();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          JSONObject jsonObject = new JSONObject();
		if(request.getParameter(StoreConstant.ACTION)!=null && request.getParameter(StoreConstant.ACTION).equalsIgnoreCase(StoreConstant.ACTION_VIEW)){
			RequestDispatcher dispatcher = request.getRequestDispatcher("/setup/setupCampusSubjectMapping.jsp");
		    dispatcher.forward(request, response);
		}
		else if (request.getParameter(StoreConstant.ACTION)!=null && request.getParameter(StoreConstant.ACTION).equalsIgnoreCase(StoreConstant.ACTION_NEW)){
			try {
				if (request.getParameter("campusSelect")!=null && request.getParameter("subjectSelect")!=null && request.getParameter("majorSelect")!=null) {
					Date date = new Date(System.currentTimeMillis());
					CampusSubjectMappingVO campusSubjectMapppingVO = new CampusSubjectMappingVO();
					campusSubjectMapppingVO.setCampusId(Integer.parseInt(request.getParameter("campusSelect")));
					campusSubjectMapppingVO.setSubjectId(Integer.parseInt(request.getParameter("subjectSelect")));
					campusSubjectMapppingVO.setMajorId(Integer.parseInt(request.getParameter("majorSelect")));
					campusSubjectMapppingVO.setCreatedDate(date);
					campusSubjectMapppingVO.setCreatedBy(request.getSession().getAttribute("currentUser").toString());
					campusSubjectMapppingVO.setStatus(StoreConstant.STATUS_ACTIVE);
					dao.addCampusSubject(campusSubjectMapppingVO);
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
	    		if(request.getParameter("campusSubject_Id")!=null && request.getParameter("campusSelect")!=null && request.getParameter("subjectSelect")!=null && request.getParameter("majorSelect")!=null){
	    			CampusSubjectMappingVO campusSubjectMapppingVO = new CampusSubjectMappingVO();
					campusSubjectMapppingVO.setCampusSubjectId(Integer.parseInt(request.getParameter("campusSubject_Id")));
					campusSubjectMapppingVO.setCampusId(Integer.parseInt(request.getParameter("campusSelect")));
					campusSubjectMapppingVO.setSubjectId(Integer.parseInt(request.getParameter("subjectSelect")));
					campusSubjectMapppingVO.setMajorId(Integer.parseInt(request.getParameter("majorSelect")));
	    			if("1".equals(request.getParameter("campussubject_rbtn"))){
	    				campusSubjectMapppingVO.setStatus(StoreConstant.STATUS_ACTIVE);
					}else{
						campusSubjectMapppingVO.setStatus(StoreConstant.STATUS_INACTIVE);
					}
	    			dao.updateCampusSubject(campusSubjectMapppingVO);
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
	    		 if(request.getParameter("campusSubject_Id")!=null){
	    			 CampusSubjectMappingVO campusSubjectMapppingVO = new CampusSubjectMappingVO();
	    			 campusSubjectMapppingVO.setCampusSubjectId(Integer.parseInt(request.getParameter("campusSubject_Id")));
	    			 dao.deleteCampusSubject(Integer.parseInt(request.getParameter("campusSubject_Id")));
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
			int campusSubject_Id = Integer.parseInt(request.getParameter("campusSubject_Id"));
			CampusSubjectMappingVO campusSubjectMapppingVO = dao.findCampusSubjectById(campusSubject_Id);
	    	if(campusSubjectMapppingVO!=null){
				try {
					response.getWriter().print(campusSubjectMapppingVO.getJSONObject().toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
	    	}
		}
		else if (request.getParameter(StoreConstant.ACTION)!=null && request.getParameter(StoreConstant.ACTION).equalsIgnoreCase(StoreConstant.ACTION_GET_PAGE_DATA)){
			int pageNo = Integer.parseInt(request.getParameter("pageNo"));
	    	int pageSize = Integer.parseInt(request.getParameter("pageSize"));
	    	List<CampusSubjectMappingVO> list = dao.getCampusSubjectDetails(pageNo, pageSize);
	    	
	    	JSONArray array = new JSONArray();
			for (CampusSubjectMappingVO campusSubjectMapppingVO : list) {
				try {
					array.put(campusSubjectMapppingVO.getJSONObject());
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
			List<CampusSubjectMappingVO> campusSubject = dao.getCampusSubjectDetails();
			JSONArray array = new JSONArray();
			for (CampusSubjectMappingVO campusSubjectMapppingVO : campusSubject) {
				try {
					array.put(campusSubjectMapppingVO.getCampusSubjectJSONObject());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			response.getWriter().write(array.toString());
		}
	}
}
