package com.example.useridentity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.useridentity.service.TaskService;
import com.example.useridentitypayload.TaskDao;

@RestController
@RequestMapping("/api")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	
	
	//save the task
	@PostMapping("/{userid}/tasks")
	public ResponseEntity<TaskDao> saveTask(
			@PathVariable(name="userid")long userid,
			@RequestBody TaskDao taskDao){
	
		return new ResponseEntity<>(taskService.saveTask(userid, taskDao),HttpStatus.CREATED);
	}
	
	
	
	//get all task
	@GetMapping("/{userid}/tasks")
	public ResponseEntity<List<TaskDao>> getAllTasks(
			@PathVariable(name = "userid")long userid){
		return new ResponseEntity<>(taskService.getAllTask(userid),HttpStatus.OK);
	}
	
	
	//get indv task
			@GetMapping("/{userid}/tasks/{taskid}")
			  public ResponseEntity<TaskDao> getTask(
					  @PathVariable(name="userid") long userid,
					  @PathVariable(name="taskid") long taskid
					  ){
				return new ResponseEntity<>(taskService.getTask(userid, taskid),HttpStatus.OK);
				  
			  }

		
			
			
		//delete indv task

			@DeleteMapping("/{userid}/tasks/{taskid}")
			  public ResponseEntity<String> deleteTask(
					  @PathVariable(name="userid") long userid,
					  @PathVariable(name="taskid") long taskid
					  ){
				taskService.deleteTask(userid, taskid);
				return new ResponseEntity<>("Task deleted successfully",HttpStatus.OK);
				  
			  }
}
