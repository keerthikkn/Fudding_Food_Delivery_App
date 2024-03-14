package com.fudding.dao;
import java.util.List;

import com.fudding.model.User;

public interface UserDao {
	
	public void addUser(User user);
	public User getUser(int userId);
	public void updateUser(User user);
	public void deleteUser(int userId);
	public List<User> getAllUser();


}
