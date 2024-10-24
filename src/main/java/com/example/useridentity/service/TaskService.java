package com.example.useridentity.service;

import java.util.List;

import com.example.useridentitypayload.TaskDao;

public interface TaskService {
	
	public  TaskDao  saveTask(long userid,  TaskDao taskDao);
	
	public List<TaskDao> getAllTask(long userid);
	

	public TaskDao getTask(long userid, long taskid);
	
	
	public void deleteTask(long userid, long taskid);
}



