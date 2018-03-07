package org.Nyit.Controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.Nyit.DAO.CampusSubjectSeatEvaluationDAO;
import org.Nyit.Utilis.StoreConstant;
import org.Nyit.VO.CampusSubjectSeatEvaluationVO;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CampusSectionSeatEvaluationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CampusSubjectSeatEvaluationDAO dao;    
    
    public CampusSectionSeatEvaluationController() {
        super();
        dao = new CampusSubjectSeatEvaluationDAO();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject jsonObject = new JSONObject();
		if(request.getParameter(StoreConstant.ACTION)!=null && request.getParameter(StoreConstant.ACTION).equalsIgnoreCase(StoreConstant.ACTION_NEW)){
			try {
				if (request.getParameter("sectionsubjectSelect")!=null && request.getParameter("total_seats")!=null && request.getParameter("start_date")!=null && request.getParameter("end_date")!=null) {
					Date date = new Date(System.currentTimeMillis());
					CampusSubjectSeatEvaluationVO campusSubjectSeatEvaluationVO = new CampusSubjectSeatEvaluationVO();
					campusSubjectSeatEvaluationVO.setSectionSubjectScheduleId(Integer.parseInt(request.getParameter("sectionsubjectSelect")));
					campusSubjectSeatEvaluationVO.setTotalSeats(Integer.parseInt(request.getParameter("total_seats")));
					campusSubjectSeatEvaluationVO.setSeatsAvailable(Integer.parseInt(request.getParameter("total_seats")));
					campusSubjectSeatEvaluationVO.setStartDate(request.getParameter("start_date"));
					campusSubjectSeatEvaluationVO.setEndDate(request.getParameter("end_date"));
					campusSubjectSeatEvaluationVO.setCreatedDate(date);
					campusSubjectSeatEvaluationVO.setCreatedBy(request.getSession().getAttribute("currentUser").toString());
					campusSubjectSeatEvaluationVO.setStatus(StoreConstant.STATUS_ACTIVE);
					dao.addCampusSubjectSeatEvaluation(campusSubjectSeatEvaluationVO);
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
	    		if(request.getParameter("sectionSubjectSeatEvaluation_Id")!=null && request.getParameter("sectionsubjectSelect")!=null && request.getParameter("total_seats")!=null && request.getParameter("start_date")!=null && request.getParameter("end_date")!=null){
	    			CampusSubjectSeatEvaluationVO campusSubjectSeatEvaluationVO = new CampusSubjectSeatEvaluationVO();
	    			campusSubjectSeatEvaluationVO.setCampusSubjectSeatEvaluationId(Integer.parseInt(request.getParameter("sectionSubjectSeatEvaluation_Id")));
	    			campusSubjectSeatEvaluationVO.setSectionSubjectScheduleId(Integer.parseInt(request.getParameter("sectionsubjectSelect")));
	    			campusSubjectSeatEvaluationVO.setTotalSeats(Integer.parseInt(request.getParameter("total_seats")));
	    			campusSubjectSeatEvaluationVO.setSeatsAvailable(Integer.parseInt(request.getParameter("total_seats")));
	    			campusSubjectSeatEvaluationVO.setStartDate(request.getParameter("start_date"));
	    			campusSubjectSeatEvaluationVO.setEndDate(request.getParameter("end_date"));
	    			if("1".equals(request.getParameter("sectionseat_rbtn"))){
	    				campusSubjectSeatEvaluationVO.setStatus(StoreConstant.STATUS_ACTIVE);
					}else{
						campusSubjectSeatEvaluationVO.setStatus(StoreConstant.STATUS_INACTIVE);
					}
	    			dao.updateSubjectSeatEvaluation(campusSubjectSeatEvaluationVO);
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
	    		 if(request.getParameter("sectionSubjectSeatEvaluation_Id")!=null){
	    			 CampusSubjectSeatEvaluationVO campusSubjectSeatEvaluationVO = new CampusSubjectSeatEvaluationVO();
	    			 campusSubjectSeatEvaluationVO.setCampusSubjectSeatEvaluationId(Integer.parseInt(request.getParameter("sectionSubjectSeatEvaluation_Id")));
	    			 dao.deleteCampusSubjectSeatEvaluation(Integer.parseInt(request.getParameter("sectionSubjectSeatEvaluation_Id")));
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
			int sectionSubjectSeatEvaluation_Id = Integer.parseInt(request.getParameter("sectionSubjectSeatEvaluation_Id"));
			CampusSubjectSeatEvaluationVO campusSubjectSeatEvaluationVO =dao.findCampusSubjectSeatEvaluationById(sectionSubjectSeatEvaluation_Id);
	    	if(campusSubjectSeatEvaluationVO!=null){
				try {
					response.getWriter().print(campusSubjectSeatEvaluationVO.getJSONObject().toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
	    	}
		}
		else if (request.getParameter(StoreConstant.ACTION)!=null && request.getParameter(StoreConstant.ACTION).equalsIgnoreCase(StoreConstant.ACTION_GET_PAGE_DATA)){
			int pageNo = Integer.parseInt(request.getParameter("pageNo"));
	    	int pageSize = Integer.parseInt(request.getParameter("pageSize"));
	    	List<CampusSubjectSeatEvaluationVO> list = dao.getCampusSubjectSeatEvaluationDetails(pageNo, pageSize);
	    	JSONArray array = new JSONArray();
			for (CampusSubjectSeatEvaluationVO campusSubjectSeatEvaluationVO : list) {
				try {
					array.put(campusSubjectSeatEvaluationVO.getJSONObject());
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
			List<CampusSubjectSeatEvaluationVO> campusSeatSubject = dao.getCampusSubjectSeatEvaluationDetails();
			JSONArray array = new JSONArray();
			for (CampusSubjectSeatEvaluationVO campusSubjectSeatEvaluationVO : campusSeatSubject) {
				try {
					array.put(campusSubjectSeatEvaluationVO.getJSONObject());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			response.getWriter().write(array.toString());
		}
		
	}
}
