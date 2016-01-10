package com.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pojo.Student;

public class StudentDal {
	public int save(Student student)
	{
		String sql=String.format("insert into student(id, name,age,address) values('%s','%s',%d,'%s')",student.getId() ,student.getName(),student.getAge(),student.getAddress());
		MysqlHelper mysqlHelper = new MysqlHelper();
		mysqlHelper.excute(sql);
		return 1;
	}
	public int delete(Student student)
	{
		String sql=String.format("delete from student where id=\"%s\"",student.getId());
		MysqlHelper mysqlHelper = new MysqlHelper();
		mysqlHelper.excute(sql);
		return 1;
	}
	public int update(Student student)
	{
		return 1;
	}
	public Student findById(Student student)
	{
		return null;
	}
	public List<Student> findAll(Student student)
	{
		MysqlHelper mysqlHelper = new MysqlHelper();
		String sql = "select * from student";
		ResultSet rs = mysqlHelper.query(sql);
		List<Student>listStudent = new ArrayList<Student>();
		try {
			while(rs.next())
			{
				Student stu = new Student();
				stu.setId(rs.getString(rs.findColumn("id")));
				stu.setName(rs.getString(rs.findColumn("name")));
				stu.setAge(rs.getInt(rs.findColumn("age")));
				stu.setAddress(rs.getString(rs.findColumn("address")));
				listStudent.add(stu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mysqlHelper.closeAll();
		return listStudent;
	}
	public List<Student> findByWhere(Student student)
	{
		return null;
	}
	public List<Student> findByPage(Student student,int page)
	{
		return null;
	}
}
