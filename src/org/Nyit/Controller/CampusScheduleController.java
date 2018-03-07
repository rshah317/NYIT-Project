package org.Nyit.Controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.Nyit.DAO.ScheduleDAO;
import org.Nyit.Utilis.StoreConstant;
import org.Nyit.VO.ScheduleVO;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class CampusScheduleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ScheduleDAO dao;
    public CampusScheduleController() {
        super();
        dao = new ScheduleDAO();
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject jsonObject = new JSONObject();
		if(request.getParameter(StoreConstant.ACTION)!=null && request.getParameter(StoreConstant.ACTION).equalsIgnoreCase(StoreConstant.ACTION_VIEW)){
			RequestDispatcher dispatcher = request.getRequestDispatcher("/setup/setupSchedule.jsp");
			dispatcher.forward(request, response);
		}
		else if(request.getParameter(StoreConstant.ACTION)!=null && request.getParameter(StoreConstant.ACTION).equalsIgnoreCase(StoreConstant.ACTION_NEW)){
			try {
				if (request.getParameter("subjectscheduleSelect")!=null && request.getParameter("day")!=null && request.getParameter("time")!=null) {
					Date date = new Date(System.currentTimeMillis());
					ScheduleVO scheduleVO = new ScheduleVO();
					scheduleVO.setCampus_subject_seat_evaluation_Id(Integer.parseInt(request.getParameter("subjectscheduleSelect")));
					scheduleVO.setDay(request.getParameter("day"));
					scheduleVO.setTime(request.getParameter("time"));
					scheduleVO.setCreatedDate(date);
					scheduleVO.setCreatedBy(request.getSession().getAttribute("currentUser").toString());
					scheduleVO.setStatus(StoreConstant.STATUS_ACTIVE);
					dao.addSchedule(scheduleVO);
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
	    		if(request.getParameter("schedule_Id")!=null && request.getParameter("subjectscheduleSelect")!=null && request.getParameter("day")!=null && request.getParameter("time")!=null){
	    			ScheduleVO scheduleVO = new ScheduleVO();
	    			scheduleVO.setScheduleId(Integer.parseInt(request.getParameter("schedule_Id")));
	    			scheduleVO.setCampus_subject_seat_evaluation_Id(Integer.parseInt(request.getParameter("subjectscheduleSelect")));
	    			scheduleVO.setDay(request.getParameter("day"));
	    			scheduleVO.setTime(request.getParameter("time"));
	    			if("1".equals(request.getParameter("rbtn"))){
	    				scheduleVO.setStatus(StoreConstant.STATUS_ACTIVE);
					}else{
						scheduleVO.setStatus(StoreConstant.STATUS_INACTIVE);
					}
	    			dao.updateSchedule(scheduleVO);
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
	    		 if(request.getParameter("schedule_Id")!=null){
	    			 ScheduleVO scheduleVO= new ScheduleVO();
	    			 scheduleVO.setScheduleId(Integer.parseInt(request.getParameter("schedule_Id")));
	    			 dao.deleteSchedule(Integer.parseInt(request.getParameter("schedule_Id")));
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
			int schedule_Id = Integer.parseInt(request.getParameter("schedule_Id"));
			ScheduleVO scheduleVO = dao.findScheduleById(schedule_Id);
	    	if(scheduleVO!=null){
				try {
					response.getWriter().print(scheduleVO.getJSONObject().toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
	    	}
		}
		else if (request.getParameter(StoreConstant.ACTION)!=null && request.getParameter(StoreConstant.ACTION).equalsIgnoreCase(StoreConstant.ACTION_GET_PAGE_DATA)){
			int pageNo = Integer.parseInt(request.getParameter("pageNo"));
	    	int pageSize = Integer.parseInt(request.getParameter("pageSize"));
	    	List<ScheduleVO> list = dao.getScheduleDetails(pageNo, pageSize);
	    	
	    	JSONArray array = new JSONArray();
			for (ScheduleVO scheduleVO : list) {
				try {
					array.put(scheduleVO.getJSONObject());
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
		else if (request.getParameter(StoreConstant.ACTION)!=null && request.getParameter(StoreConstant.ACTION).equalsIgnoreCase(StoreConstant.ACTION_GET_SUBJECT_MAJOR_DETAILS)){
			int major_Id = Integer.parseInt(request.getParameter("major_Id"));
			List<ScheduleVO> subjectSchedule = dao.getMajorSubjectDetails(major_Id);
			JSONArray array = new JSONArray();
			for (ScheduleVO scheduleVO : subjectSchedule) {
				try {
					array.put(scheduleVO.getJSONObject());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			response.getWriter().write(array.toString());
		}
		else if (request.getParameter(StoreConstant.ACTION)!=null && request.getParameter(StoreConstant.ACTION).equalsIgnoreCase(StoreConstant.ACTION_GET_COMBO_LIST)){
			List<ScheduleVO> list = dao.getScheduleDetails();
			JSONArray array = new JSONArray();
			for (ScheduleVO scheduleVO : list) {
				try {
					array.put(scheduleVO.getJSONObject());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			response.getWriter().write(array.toString());
		}
		else if (request.getParameter(StoreConstant.ACTION)!=null && request.getParameter(StoreConstant.ACTION).equalsIgnoreCase(StoreConstant.ACTION_GET_SUBJECT_DAY_TIME_DETAILS)){
			int campusSubjectId = Integer.parseInt(request.getParameter("campusSubjectId"));
			List<ScheduleVO> subjectSchedule = dao.getSubjectDayTime(campusSubjectId);
			JSONArray array = new JSONArray();
			for (ScheduleVO scheduleVO : subjectSchedule) {
				try {
					array.put(scheduleVO.getJSONObject());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			response.getWriter().write(array.toString());
		}
	}
}
