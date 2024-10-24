package com.example.useridentity.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.useridentity.entity.Users;
import com.example.useridentity.repository.UserRepository;
import com.example.useridentity.service.UserService;
import com.example.useridentitypayload.UserDao;
@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepository userRepository;
	

	@Override
	public UserDao createUser(UserDao userdao) {
		//userDao is not entity of users
		
		 Users user = userDaoToEntity(userdao);//converted userDao to Users
	        Users savedUser = userRepository.save(user);
		return entityToUserDao(savedUser);
	}
	private Users userDaoToEntity(UserDao userDao)
	{
		Users users=new Users();
		users.setName(userDao.getName());
		users.setEmail(userDao.getEmail());
		users.setPassword(userDao.getPassword());
		
		return users;
		
	}
	
	private UserDao entityToUserDao(Users savedUser)
	{
		UserDao userDao=new UserDao();
		userDao.setId(savedUser.getId());
		userDao.setName(savedUser.getName());
		userDao.setEmail(savedUser.getEmail());
		userDao.setPassword(savedUser.getPassword());
		
		return userDao;
	
		
	}


}
