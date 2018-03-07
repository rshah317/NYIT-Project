package org.Nyit.Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.Nyit.Utilis.StoreConstant;

public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public HomeController() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		if(request.getParameter(StoreConstant.ACTION)!=null && request.getParameter(StoreConstant.ACTION).equalsIgnoreCase(StoreConstant.ACTION_VIEW)){
			RequestDispatcher dispatcher = request.getRequestDispatcher("/setup/adminHome.jsp");
			dispatcher.forward(request,response);
		}
		else if(request.getParameter(StoreConstant.ACTION)!=null && request.getParameter(StoreConstant.ACTION).equalsIgnoreCase(StoreConstant.ACTION_LOGOUT)){
			response.sendRedirect("/login.jsp");
		}
	}
}
