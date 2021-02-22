package com.vipin.factory;

import com.vipin.service.StudentService;
import com.vipin.service.StudentServiceImpl;

public class StudentServiceFactory {
	
	private static StudentService studentService;
	static {
		
		studentService =new StudentServiceImpl();
	}
	public static StudentService getStudentService() {
		return studentService;
	}

}
