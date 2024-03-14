package com.fudding.daoImpl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fudding.dao.UserDao;
import com.fudding.model.User;

public class UserDaoImpl implements UserDao {
	
	private Connection connection = null;

	
	static final String SELECT_ALL_QUERY = "SELECT * FROM `user`";

	static final String SELECT_QUERY = "SELECT * FROM `user` WHERE `userId` = ?";

	static final String UPDATE_QUERY = "UPDATE `user` SET `name` = ?,`email` = ?,`phoneNo` = ?, `address` = ?,  `userName` = ?, `password` = ?, WHERE `userId` = ?";

	static final String DELETE_QUERY = "DELETE FROM `user` WHERE `userId` = ?";

	static final String INSERT_QUERY = "INSERT INTO `user` (`userId`,`name`,`email`,`phoneNo`,`address`,`userName`,`password`,) VALUES(?,?,?,?,?,?,?)";

	
	public UserDaoImpl() {
		final String username = "root";
		final String password = "root";
		final String url = "jdbc:mysql://localhost:3306/fudding";
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url,username,password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addUser(User user) {
		PreparedStatement statement = null;
		try {

			statement = connection.prepareStatement(INSERT_QUERY);

			statement.setInt(1, user.getUserId());
			statement.setString(2, user.getName());
			statement.setString(3, user.getEmail());
			statement.setInt(4, user.getPhoneNo());
			statement.setString(5, user.getAddress());
			statement.setString(6, user.getUserName());
			statement.setString(7, user.getPassword());

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
	}

	@Override
	public User getUser(int userId) {
		PreparedStatement statement = null;
		ResultSet res = null;
		User user = null;
	
		try {
	
			statement = connection.prepareStatement(SELECT_QUERY);
			statement.setInt(1, userId);
			res = statement.executeQuery();
	
			if (res.next()) {
				String name = res.getString("name");
				String email = res.getString("email");
				int phoneNo = res.getInt("phoneNo");
				String address = res.getString("address");
				String userName = res.getString("userName");
				String password = res.getString("password");
				
				
				user = new User(userId, name, email, phoneNo, address, userName, password);
						}
			}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void updateUser(User user) {
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement(UPDATE_QUERY);
			statement.setString(1, user.getName());
			statement.setString(2, user.getEmail());
			statement.setInt(3, user.getPhoneNo());
			statement.setString(4, user.getAddress());
			statement.setString(5, user.getUserName());
			statement.setString(6, user.getPassword());
			statement.setInt(7, user.getUserId());

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteUser(int userId) {
		

		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement(DELETE_QUERY);

			statement.setInt(1, userId);

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	@Override
	public List<User> getAllUser() {

		Statement statement = null;
		ResultSet res = null;
		User user = null;

		ArrayList<User> userList = new ArrayList<User>();
		try {
			statement = connection.createStatement();

			res = statement.executeQuery(SELECT_ALL_QUERY);

			while (res.next()) {
				int userId = res.getInt("userId");
				String name = res.getString("name");
				String email = res.getString("email");
				int phoneNo = res.getInt("phoneNo");
				String address = res.getString("address");
				String userName = res.getString("userName");
				String password = res.getString("password");
				
				
				user = new User(userId, name, email, phoneNo, address, userName, password);
					
				userList.add(user);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return userList;
	}


}
