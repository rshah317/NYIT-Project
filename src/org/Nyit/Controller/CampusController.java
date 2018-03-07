package org.Nyit.Controller;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.Nyit.DAO.CampusDAO;
import org.Nyit.Utilis.StoreConstant;
import org.Nyit.VO.CampusVO;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CampusController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CampusDAO dao;

	public CampusController() {
		super();
		dao = new CampusDAO();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JSONObject jsonObject = new JSONObject();
		if(request.getParameter(StoreConstant.ACTION)!=null && request.getParameter(StoreConstant.ACTION).equalsIgnoreCase(StoreConstant.ACTION_VIEW)){
			RequestDispatcher dispatcher = request.getRequestDispatcher("/setup/setupCampus.jsp");
			dispatcher.forward(request, response);
		}
	    else if(request.getParameter(StoreConstant.ACTION)!=null && request.getParameter(StoreConstant.ACTION).equalsIgnoreCase(StoreConstant.ACTION_NEW)){
			try{
				if((request.getParameter("campus_Name")!=null)){
					response.setContentType("text/html");
					Date date = new Date(System.currentTimeMillis());
					CampusVO campusVO = new CampusVO();
					campusVO.setCampusName(request.getParameter("campus_Name"));
					campusVO.setCreatedBy(request.getSession().getAttribute("currentUser").toString());
					campusVO.setCreatedDate(date);
					campusVO.setStatus(StoreConstant.STATUS_ACTIVE);
					dao.addCampus(campusVO);
					jsonObject.put("message","Successfully Added");
					jsonObject.put("isSuccess", true);
					response.getWriter().write(jsonObject.toString());
				}else{
					jsonObject.put("message", "Failure parameter missing");
					jsonObject.put("isSuccess", false);
					response.getWriter().write(jsonObject.toString());
				}
			}catch (Throwable e) {
				try {
					jsonObject.put("message", "Failure");
					jsonObject.put("isSuccess", false);
					response.getWriter().write(jsonObject.toString());
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
			}
		}
	    else if (request.getParameter(StoreConstant.ACTION)!=null && request.getParameter(StoreConstant.ACTION).equalsIgnoreCase(StoreConstant.ACTION_UPDATE)){
	    	try{
	    		if(request.getParameter("campus_Name")!=null && request.getParameter("campus_Id")!=null){
	    			CampusVO campusVO = new CampusVO();
	    			campusVO.setCampusId(Integer.parseInt(request.getParameter("campus_Id")));
	    			campusVO.setCampusName(request.getParameter("campus_Name"));
	    			if("1".equals(request.getParameter("rbtn"))){
						campusVO.setStatus(StoreConstant.STATUS_ACTIVE);
					}else{
						campusVO.setStatus(StoreConstant.STATUS_INACTIVE);
					}
	    			dao.updateCampus(campusVO);
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
	    else if(request.getParameter(StoreConstant.ACTION)!=null && request.getParameter(StoreConstant.ACTION).equalsIgnoreCase(StoreConstant.ACTION_DELETE)){
	    	try{
	    		 if(request.getParameter("campus_Id")!=null){
	    			 CampusVO campusVO = new CampusVO();
	    			 campusVO.setCampusId(Integer.parseInt(request.getParameter("campus_Id")));
	    			 dao.deleteCampus(Integer.parseInt(request.getParameter("campus_Id")));
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
	    else if(request.getParameter(StoreConstant.ACTION)!=null && request.getParameter(StoreConstant.ACTION).equalsIgnoreCase(StoreConstant.ACTION_GET_ROW_DATA)){
	    	int campus_Id = Integer.parseInt(request.getParameter("campus_Id"));
	    	CampusVO campusVO = dao.findCampusById(campus_Id);
	    	if(campusVO!=null){
				try {
					response.getWriter().print(campusVO.getJSONObject().toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
	    	}
	    }
	    else if(request.getParameter(StoreConstant.ACTION)!=null && request.getParameter(StoreConstant.ACTION).equalsIgnoreCase(StoreConstant.ACTION_GET_PAGE_DATA)){
	    	int pageNo = Integer.parseInt(request.getParameter("pageNo"));
	    	int pageSize = Integer.parseInt(request.getParameter("pageSize"));
	    	List<CampusVO> list = dao.getCampusDetails(pageNo, pageSize);
	    	JSONArray array = new JSONArray();
			for (CampusVO campusVO : list) {
				try {
					array.put(campusVO.getJSONObject());
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
			List<CampusVO> campus = dao.getCampusDetails();
			JSONArray array = new JSONArray();
			for (CampusVO campusVO : campus) {
				try {
					array.put(campusVO.getJSONObject());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			response.getWriter().write(array.toString());
		}
	}
}
