package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MysqlHelper {
	java.sql.Connection conn =null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	//��ɾ�Ĳ�
	//1��ɾ��: ��ȡ���ݿ������,Ȼ��ִ��sql���,�ر����ݿ�.
	//2��:��ȡ���ݿ������,��ȡ���͹�,Ȼ��ִ��sql���,�رռ��͹�,�ر����ݿ�.
	
	private Connection getConnection(){
		// TODO Auto-generated method stub
		String driver="com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/userTest?useUnicode=true&characterEncoding=UTF-8";
		String user="root";
		String password="";
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
	//1��ɾ��
	public int excute(String sql)
	{
		Connection conn = getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeAll();
		return 1;
	}

	//2��
	public ResultSet query(String sql)
	{
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
	
	public void closeAll()
	{
		try
		{
			rs.close();
			rs=null;
		}
		catch(Exception e){
			rs=null;
		}
		try
		{
			stmt.close();
			stmt=null;
		}
		catch(Exception e){
			stmt=null;
		}
		try
		{
			conn.close();
			conn=null;
		}
		catch(Exception e){
			conn=null;
		}
	}
}
