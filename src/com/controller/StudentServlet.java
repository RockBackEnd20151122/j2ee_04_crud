package com.controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.StudentDal;
import com.pojo.Student;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public StudentServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String router =request.getParameter("router");
		if(router==null || router.equals(""))
		{
			router="listall";
		}

		String id;
		String name = "";
		int age = 0;
		String address = "";
		Student student = new Student();
		
		try {
			id = request.getParameter("id");
		} catch (Exception e) {
			id = "";
		}
		try {
			name = request.getParameter("name");
		} catch (Exception e) {
			name = "";
		}

		try {
			age = Integer.parseInt(request.getParameter("age"));
		} catch (Exception e) {
			age = 0;
		}
		try {
			address = request.getParameter("address");
		} catch (Exception e) {
			address = "";
		}

		student.setId(id);
		student.setAddress(address);
		student.setAge(age);
		student.setName(name);

		switch (router) {
		case "add": {
			StudentDal dal = new StudentDal();
			dal.save(student);
			listall(request, response);
		}
			break;
		case "delete": {
			StudentDal dal = new StudentDal();
			dal.delete(student);
			listall(request, response);
		}
			break;
		case "modify": {
			StudentDal dal = new StudentDal();
//			dal.update(student);
			
			request.setAttribute("studentId", id );
			request.getRequestDispatcher("/update.jsp").forward(request,response);
		}
			break;
		case "listall": {
			listall(request, response);
		}
			break;
		case "listpage": {

		}
			break;
		case "listwhere": {

		}
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String router =request.getParameter("router");
		if(router==null || router.equals(""))
		{
			router="listall";
		}

		String id;
		String name = "";
		int age = 0;
		String address = "";
		Student student = new Student();
		
		try {
//			id = Integer.parseInt(request.getParameter("id"));
			id = UUID.randomUUID().toString();
		} catch (Exception e) {
			id = "";
		}
		try {
			name = request.getParameter("name");
		} catch (Exception e) {
			name = "";
		}

		try {
			age = Integer.parseInt(request.getParameter("age"));
		} catch (Exception e) {
			age = 0;
		}
		try {
			address = request.getParameter("address");
		} catch (Exception e) {
			address = "";
		}

		student.setId(id);
		student.setAddress(address);
		student.setAge(age);
		student.setName(name);

		switch (router) {
		case "add": {
			StudentDal dal = new StudentDal();
			dal.save(student);
			listall(request, response);
		}
			break;
		case "delete": {
			StudentDal dal = new StudentDal();
			dal.delete(student);
			listall(request, response);
		}
			break;
		case "modify": {

		}
			break;
		case "listall": {
			listall(request, response);
		}
			break;
		case "listpage": {

		}
			break;
		case "listwhere": {

		}
			break;
		}
	}

	void listall(HttpServletRequest request, HttpServletResponse response)
	{
		StudentDal dal = new StudentDal();
		List<Student> listStudent = dal.findAll(null);
		request.getSession().setAttribute("listStudent", listStudent);
		try {
			response.sendRedirect("studentlist.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
