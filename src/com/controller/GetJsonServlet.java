package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.StudentDal;
import com.pojo.Student;

/**
 * Servlet implementation class GetJsonServlet
 */
@WebServlet("/GetJsonServlet")
public class GetJsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetJsonServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String router = request.getParameter("router");
		String json="";
		if(router.equals("student"))
		{
			json = getStudent();
		}
	    response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");  
	    PrintWriter out = null;  
	    try {  
	        out = response.getWriter();  
	        out.append(json);   
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        if (out != null) {  
	            out.close();  
	        }  
	    }  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	String getStudent() {
		StudentDal dal = new StudentDal();
		List<Student> listStudent = dal.findAll(null);
		String str = "[";
		for (int i = 0; listStudent != null && listStudent.size() > i; i++) {
			Student student = listStudent.get(i);
			str += "{";
			str += String.format("\"id\":\"%d\",\"name\":\"%s\",\"age\":\"%d\",\"address\":\"%s\"", student.getId(),
					student.getName(), student.getAge(), student.getAddress());
			str += "},";
		}
		str=str.substring(0, str.length()-1);
		str+="]";
		return str;
	}
}
