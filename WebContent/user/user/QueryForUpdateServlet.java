package user;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.dto.UserDto;

public class QueryForUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public QueryForUpdateServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");

		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/test";
		String user = "root";
		String passwordMysql = "";
		Connection con = null;
		// 加载驱动
		try {
			Class.forName(driver);
			// 获取连接
			con = DriverManager.getConnection(url, user, passwordMysql);
			// 拼接sql
			String sql = "select ut.user_id,ut.user_name,ut.password,ut.status from user_test ut where ut.user_id = ?";
			PreparedStatement s = con.prepareStatement(sql);
			s.setString(1, userId);
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				UserDto userDto = new UserDto();
				userDto.setUserId(rs.getString(1));
				userDto.setUserName(rs.getString(2));
				userDto.setPassword(rs.getString(3));
				userDto.setStatus(rs.getString(4));
				request.setAttribute("userDto", userDto);
			}
			request.getRequestDispatcher("user/update.jsp").forward(request,
					response);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
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

}
