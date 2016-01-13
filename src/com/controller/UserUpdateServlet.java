package com.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.MysqlHelper;
import com.pojo.User;

/**
 * Servlet implementation class UserUpdateServlet
 */
@WebServlet("/UserUpdateServlet")
public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserUpdateServlet() {
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
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		MysqlHelper sqlHelper = new MysqlHelper();
		String userId = request.getParameter("userId");

		// String sql = "select * from users where user_id=?";
		// Connection con = sqlHelper.getConnection();
		// PreparedStatement st = con.prepareStatement(sql);
		// st.setString(1, userId);

		// String sql=String.format("insert into student(id, name,age,address)
		// values('%s','%s',%d,'%s')",student.getId()
		// ,student.getName(),student.getAge(),student.getAddress());
		String sql = String.format("select * from users where user_id='%s'", userId );

		ResultSet rs = sqlHelper.listUsers(sql);
		User user = new User();
		
		try {
			while( rs.next() ){
				user.setGender(rs.getString(rs.findColumn("gender")));
				user.setUserName(rs.getString(rs.findColumn("user_name")));
				user.setPassword(rs.getString(rs.findColumn("password")));
				user.setUserId(rs.getString(rs.findColumn("user_id")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			request.getSession().setAttribute("user", user);
			request.getRequestDispatcher("userUpdate.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void goModifyUser(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		response.setCharacterEncoding("UTF-8");
		
		MysqlHelper sqlHelper = new MysqlHelper();
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");

//		UPDATE users t set user_name='Rock', password='fuck' where t.user_id in ('01','2') and t.user_name='Rock01';
		String sql = String.format("UPDATE users set user_name='%s', password='%s', gender='%s' where user_id='%s'", userName, password, gender, userId);

		sqlHelper.excute(sql);
		sqlHelper.listUsers(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		goModifyUser( request, response );
	}

}
