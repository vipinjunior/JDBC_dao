package com.vipin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.vipin.dto.Student;
import com.vipin.factory.ConnectionFactory;

public class StudentDaoImpl implements StudentDao {

	@Override
	public String add(Student std) {
		
		String status="";
		try {
			
			Connection con=ConnectionFactory.getConnection();
			PreparedStatement pst=con.prepareStatement("select * from student1 where SID=?");
			pst.setString(1, std.getSid());
			ResultSet rs=pst.executeQuery();
			boolean b=rs.next();
			if(b==true) {
				status="existed";
			}else {
				pst=con.prepareStatement("insert into student1 values(?,?,?)");
				pst.setString(1, std.getSid());
				pst.setString(2, std.getSname());
				pst.setString(3, std.getSaddr());
				int rowCount=pst.executeUpdate();
				if(rowCount==1) {
					status="success";
				}else {
					status="failure";
				}
			}
						
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return status;
	}

	@Override
	public Student search(String sid) {
		
		Student std=null;
		try {
			Connection con=ConnectionFactory.getConnection();
			PreparedStatement pst=con.prepareStatement("select * from student1 where SID=?");
			pst.setString(1, sid);
			ResultSet rs=pst.executeQuery();
			boolean b=rs.next();
			
			if(b==true) {
				
				std=new Student();
				std.setSid(rs.getString("SID"));
				std.setSname(rs.getString("SNAME"));
				std.setSaddr(rs.getString("SADDR"));
			}else {
				std=null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return std;
	}

	@Override
	public String delete(String sid) {
		String status=null;
		try {
			Connection con=ConnectionFactory.getConnection();
			PreparedStatement pst=con.prepareStatement("select * from student1 where SID=?");
			pst.setString(1, sid);
			ResultSet rs=pst.executeQuery();
			boolean b=rs.next();
			if(b==true) {
				pst=con.prepareStatement("delete from student1 where SID=?");
				pst.setString(1, sid);
				int rowcount=pst.executeUpdate();
				if(rowcount==1) {
					status="success";
				}else
				{
					status="failure";
				}
			}else
			{
				status="notexisted";
			}
			
		} catch (Exception e) {
			status="failure";
			e.printStackTrace();
		}
		
		return status;
	}

}
