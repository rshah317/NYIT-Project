package org.Nyit.Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.Nyit.Utilis.StoreConstant;

public class BaseController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BaseController() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		if(request.getParameter(StoreConstant.MODULE).equalsIgnoreCase(StoreConstant.MOD_CAMPUS)){
			dispatcher = request.getRequestDispatcher(StoreConstant.SERVLET_CAMPUS);
			dispatcher.forward(request, response);
		}
		else if(request.getParameter(StoreConstant.MODULE).equalsIgnoreCase(StoreConstant.MOD_USER)){
			dispatcher = request.getRequestDispatcher(StoreConstant.SERVLET_LOGIN);
			dispatcher.forward(request, response);
		}
		else if(request.getParameter(StoreConstant.MODULE).equalsIgnoreCase(StoreConstant.MOD_HOME)){
			dispatcher = request.getRequestDispatcher(StoreConstant.SERVLET_HOME);
			dispatcher.forward(request, response);
		}
		else if(request.getParameter(StoreConstant.MODULE).equalsIgnoreCase(StoreConstant.MOD_CAMPUS_SUBJECT_MAPPING)){
			dispatcher = request.getRequestDispatcher(StoreConstant.SERVLET_CAMPUS_SUBJECT_MAPPING);
			dispatcher.forward(request, response);
		}
		else if(request.getParameter(StoreConstant.MODULE).equalsIgnoreCase(StoreConstant.MOD_SUBJECT)){
			dispatcher = request.getRequestDispatcher(StoreConstant.SERVLET_SUBJECT);
			dispatcher.forward(request, response);
		}
		else if(request.getParameter(StoreConstant.MODULE).equalsIgnoreCase(StoreConstant.MOD_MAJOR)){
			dispatcher = request.getRequestDispatcher(StoreConstant.SERVLET_MAJOR);
			dispatcher.forward(request, response);
		}
		else if(request.getParameter(StoreConstant.MODULE).equalsIgnoreCase(StoreConstant.MOD_STUDENTS)){
			dispatcher = request.getRequestDispatcher(StoreConstant.SERVLET_STUDENT);
			dispatcher.forward(request, response);
		}
		else if(request.getParameter(StoreConstant.MODULE).equalsIgnoreCase(StoreConstant.MOD_SECTION)){
			dispatcher = request.getRequestDispatcher(StoreConstant.SERVLET_SECTION);
			dispatcher.forward(request, response);
		}
		else if(request.getParameter(StoreConstant.MODULE).equalsIgnoreCase(StoreConstant.MOD_SECTION_SUBJECT_MAPPING)){
			dispatcher = request.getRequestDispatcher(StoreConstant.SERVLET_SECTION_SUBJECT_MAPPING);
			dispatcher.forward(request, response);
		}
		else if(request.getParameter(StoreConstant.MODULE).equalsIgnoreCase(StoreConstant.MOD_CAMPUS_SCHEDULE)){
			dispatcher = request.getRequestDispatcher(StoreConstant.SERVLET_CAMPUS_SCHEDULE);
			dispatcher.forward(request, response);
		}
		else if(request.getParameter(StoreConstant.MODULE).equalsIgnoreCase(StoreConstant.MOD_CAMPUS_SECTION_SEAT_EVALUATION)){
			dispatcher = request.getRequestDispatcher(StoreConstant.SERVLET_CAMPUS_SECTION_SEAT_EVALUATION);
			dispatcher.forward(request, response);
		}
		else if(request.getParameter(StoreConstant.MODULE).equalsIgnoreCase(StoreConstant.MOD_STUDENTS)){
			dispatcher = request.getRequestDispatcher(StoreConstant.SERVLET_STUDENT);
			dispatcher.forward(request, response);
		}
		else if(request.getParameter(StoreConstant.MODULE).equalsIgnoreCase(StoreConstant.MOD_STUDENT_REQUEST)){
			dispatcher = request.getRequestDispatcher(StoreConstant.SERVLET_STUDENT_REQUEST);
			dispatcher.forward(request, response);
		}
	}

}
