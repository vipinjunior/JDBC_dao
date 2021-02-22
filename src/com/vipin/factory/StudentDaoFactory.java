package com.vipin.factory;

import com.vipin.dao.StudentDao;
import com.vipin.dao.StudentDaoImpl;

public class StudentDaoFactory {
	private static StudentDao studentDao;
	static {
		studentDao=new StudentDaoImpl();
		
	}
	public static StudentDao getStudentDao() {
		return studentDao;
	}

}
