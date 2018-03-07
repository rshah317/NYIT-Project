package org.Nyit.Controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.Nyit.DAO.MajorDAO;
import org.Nyit.Utilis.StoreConstant;
import org.Nyit.VO.MajorVO;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MajorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MajorDAO dao;
   
    public MajorController() {
        super();
        dao = new MajorDAO();
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    JSONObject jsonObject = new JSONObject();
		if(request.getParameter(StoreConstant.ACTION)!=null && request.getParameter(StoreConstant.ACTION).equalsIgnoreCase(StoreConstant.ACTION_NEW)){
		  try{
			  if(request.getParameter("major_Name")!=null && request.getParameter("major_Code")!=null){
				  response.setContentType("text/html");
				  Date date = new Date(System.currentTimeMillis());
				  MajorVO majorVO = new MajorVO();
				  majorVO.setMajorName(request.getParameter("major_Name"));
				  majorVO.setMajorCode(request.getParameter("major_Code"));
				  majorVO.setCreatedBy(request.getSession().getAttribute("currentUser").toString());
				  majorVO.setCreatedDate(date);
				  majorVO.setStatus(StoreConstant.STATUS_ACTIVE);
				  dao.addMajor(majorVO);
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
		else if (request.getParameter(StoreConstant.ACTION)!=null && request.getParameter(StoreConstant.ACTION).equalsIgnoreCase(StoreConstant.ACTION_DELETE)){
			try{
				if(request.getParameter("major_Id")!=null){
					MajorVO majorVO = new MajorVO();
					majorVO.setMajorId(Integer.parseInt(request.getParameter("major_Id")));
					dao.deleteMajor(Integer.parseInt(request.getParameter("major_Id")));
					jsonObject.put("message", "Deleted Successfully");
					jsonObject.put("isSuccess",true);
					response.getWriter().write(jsonObject.toString());
				}else{
	    			jsonObject.put("message","Failure Parameter Missing");
					jsonObject.put("isSuccess", false);
					response.getWriter().write(jsonObject.toString());
	    		}
			}catch(Throwable e){
				try{
					jsonObject.put("message", "Failure");
					jsonObject.put("isSuccess", false);
					response.getWriter().write(jsonObject.toString());
				}catch (JSONException e1) {
					e1.printStackTrace();
				}
			}
		}
		else if (request.getParameter(StoreConstant.ACTION)!=null && request.getParameter(StoreConstant.ACTION).equalsIgnoreCase(StoreConstant.ACTION_UPDATE)){
			try{
				if(request.getParameter("major_Name")!=null && request.getParameter("major_Code")!=null && request.getParameter("major_Id")!=null){
					MajorVO majorVO = new MajorVO();
					majorVO.setMajorId(Integer.parseInt(request.getParameter("major_Id")));
					majorVO.setMajorName(request.getParameter("major_Name"));
					majorVO.setMajorCode(request.getParameter("major_Code"));
					if("1".equals(request.getParameter("rbtn"))){
						majorVO.setStatus(StoreConstant.STATUS_ACTIVE);
					}else{
						majorVO.setStatus(StoreConstant.STATUS_INACTIVE);
					}
					dao.updateMajor(majorVO);
					jsonObject.put("message", "Updated Successfully");
					jsonObject.put("isSuccess",true);
					response.getWriter().write(jsonObject.toString());
	    		}else{
	    			jsonObject.put("message","Failure Parameter Missing");
					jsonObject.put("isSuccess", false);
					response.getWriter().write(jsonObject.toString());
				}
			}catch (Throwable e) {
				try{
					jsonObject.put("message", "Failure");
					jsonObject.put("isSuccess", false);
					response.getWriter().write(jsonObject.toString());
				}catch (JSONException e1) {
					e1.printStackTrace();
				}
			}
		}
		else if (request.getParameter(StoreConstant.ACTION)!=null && request.getParameter(StoreConstant.ACTION).equalsIgnoreCase(StoreConstant.ACTION_GET_ROW_DATA)){
				int major_Id = Integer.parseInt(request.getParameter("major_Id"));
				MajorVO majorVO = dao.findMajorById(major_Id);
				if(majorVO!=null){
					try{
						response.getWriter().print(majorVO.getJSONObject().toString());
					}catch (Exception e) {
						e.printStackTrace();
				}
			}
		}
		else if (request.getParameter(StoreConstant.ACTION)!=null && request.getParameter(StoreConstant.ACTION).equalsIgnoreCase(StoreConstant.ACTION_GET_PAGE_DATA)){
			int pageNo = Integer.parseInt(request.getParameter("pageNo"));
	    	int pageSize = Integer.parseInt(request.getParameter("pageSize"));
	    	List<MajorVO> list = dao.getMajorDetails(pageNo, pageSize);
	    	JSONArray array = new JSONArray();
			for (MajorVO majorVO : list) {
				try {
					array.put(majorVO.getJSONObject());
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
			List<MajorVO> major = dao.getMajorDetails();
			JSONArray array = new JSONArray();
			for (MajorVO majorVO : major) {
				try {
					array.put(majorVO.getJSONObject());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			response.getWriter().write(array.toString());
		}
	}
}
