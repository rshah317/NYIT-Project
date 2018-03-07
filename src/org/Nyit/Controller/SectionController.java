package org.Nyit.Controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.Nyit.DAO.SectionDAO;
import org.Nyit.Utilis.StoreConstant;
import org.Nyit.VO.SectionVO;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class SectionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SectionDAO dao;
    
    public SectionController() {
        super();
        dao = new SectionDAO();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject jsonObject = new JSONObject();
		 if(request.getParameter(StoreConstant.ACTION)!=null && request.getParameter(StoreConstant.ACTION).equalsIgnoreCase(StoreConstant.ACTION_NEW)){
			try{
				if((request.getParameter("section_Name")!=null)){
					response.setContentType("text/html");
					Date date = new Date(System.currentTimeMillis());
					SectionVO sectionVO = new SectionVO();
					sectionVO.setSectionName(request.getParameter("section_Name"));
					sectionVO.setCreatedBy(request.getSession().getAttribute("currentUser").toString());
					sectionVO.setCreatedDate(date);
					sectionVO.setStatus(StoreConstant.STATUS_ACTIVE);
					dao.addSection(sectionVO);
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
			    		response.getWriter().write(jsonObject.toString());
			    	} catch (JSONException e1) {
					e1.printStackTrace();
				}
			}
		}
		
		else if (request.getParameter(StoreConstant.ACTION)!=null && request.getParameter(StoreConstant.ACTION).equalsIgnoreCase(StoreConstant.ACTION_UPDATE)){
	    	try{
	    		if(request.getParameter("section_Name")!=null && request.getParameter("section_Id")!=null){
	    			SectionVO sectionVO = new SectionVO();
	    			sectionVO.setSectionId(Integer.parseInt(request.getParameter("section_Id")));
	    			sectionVO.setSectionName(request.getParameter("section_Name"));
	    			if("1".equals(request.getParameter("rbtn"))){
						sectionVO.setStatus(StoreConstant.STATUS_ACTIVE);
					}else{
						sectionVO.setStatus(StoreConstant.STATUS_INACTIVE);
					}
	    			dao.updateSection(sectionVO);
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
	    		 if(request.getParameter("section_Id")!=null){
	    			 SectionVO sectionVO = new SectionVO();
	    			 sectionVO.setSectionId(Integer.parseInt(request.getParameter("section_Id")));
	    			 dao.deleteSection(Integer.parseInt(request.getParameter("section_Id")));
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
	    	int section_Id = Integer.parseInt(request.getParameter("section_Id"));
	    	SectionVO sectionVO = dao.findSectionById(section_Id);
	    	if(sectionVO!=null){
				try {
					response.getWriter().print(sectionVO.getJSONObject().toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
	    	}
	    }
			
		else if(request.getParameter(StoreConstant.ACTION)!=null && request.getParameter(StoreConstant.ACTION).equalsIgnoreCase(StoreConstant.ACTION_GET_PAGE_DATA)){
	    	int pageNo = Integer.parseInt(request.getParameter("pageNo"));
	    	int pageSize = Integer.parseInt(request.getParameter("pageSize"));
	    	List<SectionVO> list = dao.getSectionDetails(pageNo, pageSize);
	    	JSONArray array = new JSONArray();
			for (SectionVO sectionVO : list) {
				try {
					array.put(sectionVO.getJSONObject());
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
			List<SectionVO> sectionDetails = dao.getSectionDetails();
			JSONArray array = new JSONArray();
			for (SectionVO sectionVO : sectionDetails) {
				try {
					array.put(sectionVO.getJSONObject());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			response.getWriter().write(array.toString());
		}
	}
}
