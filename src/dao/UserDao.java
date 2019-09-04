package dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;


import bean.User;
import util.C3P0Utils;

public class UserDao {

	public User login(String username, String password) throws SQLException {
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		String sql ="select * from user where username = ? and password = ?";
		
		User user =runner.query(sql, new BeanHandler<>(User.class), username,password);
		return user;
	}

	public boolean register(User user) {
		QueryRunner runner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "insert into user values(?,?,?)";
		Object[] params ={null,user.getUsername(),user.getPassword()};
		boolean flag = false;
		try {
			int i = runner.update(sql, params);
			if (i==1) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	
}
