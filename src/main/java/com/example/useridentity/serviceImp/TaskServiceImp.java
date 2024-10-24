package com.example.useridentity.serviceImp;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.useridentity.entity.Task;
import com.example.useridentity.entity.Users;
import com.example.useridentity.exception.APIException;
import com.example.useridentity.exception.TaskNotFound;
import com.example.useridentity.exception.UserNotFound;
import com.example.useridentity.repository.TaskRepository;
import com.example.useridentity.repository.UserRepository;
import com.example.useridentity.service.TaskService;
import com.example.useridentitypayload.TaskDao;
@Service
public class TaskServiceImp implements TaskService {

	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TaskRepository taskRepository;
	 
	
	@Override
	public TaskDao saveTask(long userid, TaskDao taskDao) {
 
		Users user=userRepository.findById(userid).orElseThrow(
				()-> new UserNotFound(String.format("User id %d not found", userid))
				);
		Task task = modelMapper.map(taskDao, Task.class);
		task.setUsers(user);
		//After setting the user , we are storing the data in DB
		Task saveTask = taskRepository.save(task);
		return modelMapper.map(saveTask, TaskDao.class);
	}


	@Override
	public List<TaskDao> getAllTask(long userid) {
		
		userRepository.findById(userid).orElseThrow(
				()-> new UserNotFound(String.format("User Id %d not found", userid))
				);
             List<Task> tasks=taskRepository.findAllByUsersId(userid);
             return tasks.stream().map(
     				task -> modelMapper.map(task, TaskDao.class))
     				.collect(Collectors.toList());
		
	}
	
	
	public TaskDao getTask(long userid, long taskid) {
		 Users users=userRepository.findById(userid).orElseThrow(
				()-> new UserNotFound(String.format("Userid %d not found", userid)));
		 Task task=taskRepository.findById(taskid).orElseThrow(
				 ()-> new TaskNotFound(String.format("task id %d not found",taskid)));
		 if(users.getId() !=task.getUsers().getId())
		 {
			 throw new  APIException(String.format("taskid %d is not belongs to User Id %d, taskid",taskid,userid));
		 }
		 
		return modelMapper.map(task, TaskDao.class);
	}


	@Override
	public void deleteTask(long userid, long taskid) {
		
		 Users users=userRepository.findById(userid).orElseThrow(
					()-> new UserNotFound(String.format("Userid %d not found", userid)));
			 Task task=taskRepository.findById(taskid).orElseThrow(
					 ()-> new TaskNotFound(String.format("task id %d not found",taskid)));
			 if(users.getId() !=task.getUsers().getId())
			 {
				 throw new  APIException(String.format("taskid %d is not belongs to User Id %d, taskid",taskid,userid));
			 }
			 taskRepository.deleteById(taskid);  //delete the task
	}

}
