package com.vipin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vipin.dto.Student;
import com.vipin.factory.StudentServiceFactory;
import com.vipin.service.StudentService;

@WebServlet("*.do")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String req_Path=request.getRequestURI();
		//System.out.println(req_Path);
		
		StudentService studentService=StudentServiceFactory.getStudentService();
		if(req_Path.endsWith("add.do")) {
			String sid=request.getParameter("sid");
			String sname=request.getParameter("sname");
			String saddr=request.getParameter("saddr");
			
			Student std=new Student();
			std.setSid(sid);
			std.setSname(sname);
			std.setSaddr(saddr);
			String status=studentService.addStudent(std);
			RequestDispatcher requestDispather=null;
			
			if(status.equals("success")) {
				requestDispather=request.getRequestDispatcher("success.html");
				requestDispather.forward(request, response);
			}
			if(status.equals("failure")) {
				requestDispather=request.getRequestDispatcher("failure.html");
				requestDispather.forward(request, response);
			}
			if(status.equals("existed")) {
				requestDispather=request.getRequestDispatcher("existed.html");
				requestDispather.forward(request, response);
			}
			
		}
		if(req_Path.endsWith("search.do")) {
			String sid=request.getParameter("sid");
			Student std=studentService.searchStudent(sid);
			RequestDispatcher requestDispather= null;
			if(std==null) {
				requestDispather =request.getRequestDispatcher("notexisted.html");
				requestDispather.forward(request, response);
				
			}else {
			request.setAttribute("std", std);
				requestDispather=request.getRequestDispatcher("display.jsp");
				requestDispather.forward(request, response);
		}
			
	}
		if(req_Path.endsWith("delete.do")) {
			String sid=request.getParameter("sid");
			String status=studentService.deleteStudent(sid);
			RequestDispatcher requestDispather=null;
			if(status.equals("success")) {
				requestDispather=request.getRequestDispatcher("success.html");
				requestDispather.forward(request, response);
			}
			if(status.equals("failure")) {
				requestDispather=request.getRequestDispatcher("failure.html");
				requestDispather.forward(request, response);
			}
			if(status.equals("notexisted")) {
					requestDispather=request.getRequestDispatcher("notexisted.html");
					requestDispather.forward(request, response);	
			}
		}
	}

}

