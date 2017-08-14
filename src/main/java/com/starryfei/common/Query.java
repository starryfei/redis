package com.starryfei.common;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.starryfei.entity.User;

public class Query {
	private Common con = null;
	private Statement st = null;

	public Query() {
		con = Common.getInstance();
		st = con.connectMysql();
	}

	public List<User> query(String sql) {
		List<User> userList = null;
		try {
			ResultSet rs = st.executeQuery(sql);
			userList = new ArrayList<User>();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getString(1));
				user.setName(rs.getString(2));
				user.setPwd(rs.getString(3));
				// System.out.println(user);
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// for(int i = 0;i<userList.size();i++) {
		// System.out.println(userList.get(i));
		// }
		return userList;
	}

	public boolean execSql(String sql) {
		try {
			int rs = st.executeUpdate(sql);
			if (rs == 0) {
				return false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	public void close() {
		con.closeMysql();
	}

}
