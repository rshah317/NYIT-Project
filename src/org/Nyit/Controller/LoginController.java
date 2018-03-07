package org.Nyit.Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.Nyit.DAO.UserDAO;
import org.Nyit.Utilis.StoreConstant;
import org.Nyit.VO.UserVO;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO dao;

	public LoginController() {
		super();
		dao = new UserDAO();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JSONObject jsonObject = new JSONObject();
		if (request.getParameter(StoreConstant.ACTION) != null && request.getParameter(StoreConstant.ACTION).equalsIgnoreCase(StoreConstant.ACTION_REGISTRATION)) {
			try {
				if (request.getParameter("firstName") != null && request.getParameter("lastName") != null && request.getParameter("email_Id") != null && request.getParameter("password") != null) {
					response.setContentType("text/html");
					UserVO userVO = new UserVO();
					userVO.setFirstName(request.getParameter("firstName"));
					userVO.setLastName(request.getParameter("lastName"));
					userVO.setEmailId(request.getParameter("email_Id"));
					userVO.setPassword(request.getParameter("password"));
					dao.registerUser(userVO);
					 jsonObject.put("message","Registered Successfully");
					 jsonObject.put("isSuccess",true);
					 response.getWriter().write(jsonObject.toString());
					// response.sendRedirect("login.jsp");
				} else {
					 jsonObject.put("message","Registered not done Successfully");
					 jsonObject.put("isSuccess",false);
					 response.getWriter().write(jsonObject.toString());
				}
			}
			catch (Throwable e) {
				e.printStackTrace();
			}
		} 
		else if (request.getParameter(StoreConstant.ACTION)!=null && request.getParameter(StoreConstant.ACTION).equalsIgnoreCase(StoreConstant.ACTION_LOGIN)){
			try {
				if (request.getParameter("email_Id") != null && request.getParameter("password") != null) {
						UserVO userVO = dao.Authenticate(request.getParameter("email_Id"), request.getParameter("password"));
						HttpSession session = request.getSession(true);
						String email_Id = userVO.getEmailId();
						session.setAttribute("currentUser",email_Id);
						jsonObject.put("message","Login Successfully");
						jsonObject.put("isSuccess",true);
						response.getWriter().write(jsonObject.toString());

					}
				}
				catch (Throwable e) {
					try {
						jsonObject.put("message","Invalid Email Id or Password" + e);
						jsonObject.put("isSuccess",false);
						response.getWriter().write(jsonObject.toString());
					} catch (JSONException e1) {
						e1.printStackTrace();
				}
			}
		}
		else if (request.getParameter(StoreConstant.ACTION)!=null && request.getParameter(StoreConstant.ACTION).equalsIgnoreCase(StoreConstant.ACTION_LOGOUT)){
			HttpSession session = request.getSession();
			if (session != null) {
				session.removeAttribute("currentUser");
				session.invalidate();
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request,response);
		}
	}
}
