package com.starryfei.common;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 通过jdbc连接数据库
 * @author starryfei
 *
 */
public class Common {
	private final String  JDBC_DRIVER ="com.mysql.jdbc.Driver";
	private final String DB_URL="jdbc:mysql://localhost:3306/graduate?useUnicode=true&characterEncoding=UTF-8";
	private final String NAME="root";
	private final String PWD="123";
	Connection con = null;
	Statement stmt = null;
	private static final Common instance = new Common();
	
	private Common() {
	}
	public static Common getInstance() {
		return instance;
	}
	public Statement connectMysql() {
		try {
			//register JDBC driver
			Class.forName(JDBC_DRIVER);
			System.out.println("connect to mysql ...");
			try {
				//open connect 
				con = DriverManager.getConnection(DB_URL,NAME,PWD);
				System.out.println("connect success...");
				//execue queryu
				stmt = con.createStatement();
//				String sql = "select * from test ";
//				ResultSet rs = stmt.executeQuery(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
//		}finally {
//			if(stmt !=null) {
//				try {
//					stmt.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				if(con != null) {
//					try {
//						con.close();
//						System.out.println("close mysql..");
//					} catch (SQLException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//			}
		}
		return stmt;
	}
	public void closeMysql() {
		try {
			stmt.close();
			con.close();
			System.out.println("close mysql success");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
