package com.vipin.dao;

import com.vipin.dto.Student;

public interface StudentDao {
	public String add(Student std);
	public Student search(String sid);
	public String delete(String sid);

}
