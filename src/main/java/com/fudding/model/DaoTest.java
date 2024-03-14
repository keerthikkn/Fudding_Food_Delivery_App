package com.fudding.model;
import java.util.List;

import com.fudding.*;
import com.fudding.daoImpl.UserDaoImpl;

public class DaoTest{
	public static void main(String[] args) {
		UserDaoImpl userdao = new UserDaoImpl();
		User user = new User(2,"keekooo","keekj@gmail.com",744380455,"bangalore","usscername","password"); 
		
		//userdao.addUser(user);
		
		//userdao.updateUser(user);

		//userdao.deleteUser(2);

		/*List<User> hell = userdao.getAllUser();
		for(int i=0;i<hell.size();i++){
			User obj = hell.get(i);
		    System.out.println(obj.getUserName()+" "+ obj.getPassword());
		} */

		//User name = userdao.getUser(1); System.out.println(name.getPhoneNo());
		
	}
}
