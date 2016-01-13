package com.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pojo.User;

public class MysqlHelper {
	java.sql.Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;

	public Connection getConnection() {
		// TODO Auto-generated method stub
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/userTest?useUnicode=true&characterEncoding=UTF-8";
		String user = "root";
		String password = "";
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	public void excuteAddUser(HttpServletRequest request, HttpServletResponse response) {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");

		User user = new User();
		user.setUserId(UUID.randomUUID().toString());
		user.setUserName(userName);
		user.setPassword(password);
		user.setGender(gender);
		excute(user);
	}

	public void excute(User user) {
		Connection con = getConnection();
		// 拼接sql
		String sql = "INSERT INTO users(user_id,user_name,password,gender)" + "VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement s = con.prepareStatement(sql);
			s.setString(1, UUID.randomUUID().toString());
			s.setString(2, user.getUserName());
			s.setString(3, user.getPassword());
			s.setString(4, user.getGender());
			s.execute();

			// String sql=String.format("insert into student(id, name,age,address) values('%s','%s',%d,'%s')",student.getId()
			// ,student.getName(),student.getAge(),student.getAddress());
//			ResultSet rs = sqlHelper.listUsers(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public ResultSet listUsers(String sql) {
		Connection conn = getConnection();
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// closeAll();
		return rs;

	}

	// method NO1
	public void listUsers(HttpServletRequest request, HttpServletResponse response) {

		response.setCharacterEncoding("UTF-8");
		String sql = "select * from users";
		
		ResultSet rs = listUsers(sql);
		request.getSession().setAttribute("results", rs);
		List<User> list = new ArrayList<User>();

		try {
			while (rs.next()) {
				User user = new User();
				// userDto.setUserId(rs.getString(1));
				// stu.setId(rs.getString(rs.findColumn("id")));
				
				user.setGender(rs.getString(rs.findColumn("gender")));
				user.setUserId(rs.getString(rs.findColumn("user_id")));
				 user.setPassword(rs.getString(rs.findColumn("password")));
				user.setUserName(rs.getString(rs.findColumn("user_name")));
				list.add(user);
			}

			request.getSession().setAttribute("result", list);
			request.getRequestDispatcher("userList.jsp").forward(request, response);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// method NO2
	private void listAll(HttpServletRequest request, HttpServletResponse response) {
		String sql = "select * from users";
		response.setCharacterEncoding("UTF-8");

		ResultSet rs = listUsers(sql);
		request.getSession().setAttribute("results", rs);
		List<User> list = new ArrayList<User>();

		try {
			while (rs.next()) {
				User user = new User();
				// userDto.setUserId(rs.getString(1));
				// stu.setId(rs.getString(rs.findColumn("id")));
				user.setGender(rs.getString(rs.findColumn("gender")));
				user.setUserId(rs.getString(rs.findColumn("user_id")));
				// user.setPassword()
				user.setUserName(rs.getString(rs.findColumn("user_name")));
				;
				list.add(user);
			}

			request.getSession().setAttribute("result", list);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			response.sendRedirect("userList.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// method NO3
	private void listThem(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		String sql = "select * from users";

		ResultSet rs = listUsers(sql);
		request.getSession().setAttribute("results", rs);
		List<User> list = new ArrayList<User>();

		try {
			while (rs.next()) {
				User user = new User();
				// userDto.setUserId(rs.getString(1));
				// stu.setId(rs.getString(rs.findColumn("id")));
				user.setGender(rs.getString(rs.findColumn("gender")));
				user.setUserId(rs.getString(rs.findColumn("user_id")));
				// user.setPassword()
				user.setUserName(rs.getString(rs.findColumn("user_name")));
				;
				list.add(user);
			}
			request.setAttribute("result", list);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			request.getRequestDispatcher("userList.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Boolean deleteById(String userId) {
		String sql = "delete from users where user_id=?";
		Connection con = getConnection();
		Boolean b = true;

		try {
			PreparedStatement s = con.prepareStatement(sql);
			s.setString(1, userId);
			s.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			b = false;
		}
		closeAll();
		return b;
	}

	public void excute(String sql) {
		Connection conn = getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeAll();
	}

	public ResultSet query(String sql) {
		Connection conn = getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	public void closeAll() {
		try {
			rs.close();
			rs = null;
		} catch (Exception e) {
			rs = null;
		}
		try {
			stmt.close();
			stmt = null;
		} catch (Exception e) {
			stmt = null;
		}
		try {
			conn.close();
			conn = null;
		} catch (Exception e) {
			conn = null;
		}
	}
}
