package com.gasagency.gas.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gasagency.gas.entity.Users;
import com.gasagency.gas.model.UsersModel;
import com.gasagency.gas.service.UsersServiceInterf;
import com.gasagency.gas.utility.BaseResponse;
import com.gasagency.gas.utility.CommonConstants;

@RestController
@RequestMapping("api/v1")
public class UsersController {

	@Autowired
	UsersServiceInterf usersServiceInterf;
	
	/*
	 * @Author:User
	 * createdBy:Avinash B Patil
	 * Date:18/09/2020
	 */
	@PostMapping("/saveUser")
	public ResponseEntity<BaseResponse<Users>> addUsers(@RequestBody Users users)
	{
		BaseResponse<Users> usersBaseResponse=new BaseResponse<>();
		if(users!=null)
		{
			
			BaseResponse<Users> UsersResponse=usersServiceInterf.saveUsers(users);
			if(UsersResponse.getStatus().equalsIgnoreCase(CommonConstants.SUCCESS))
			{
				usersBaseResponse.setStatus(UsersResponse.getStatus());
				usersBaseResponse.setReasonText(UsersResponse.getReasonText());
				usersBaseResponse.setResponseObject(UsersResponse.getResponseObject());
				return new ResponseEntity<BaseResponse<Users>>(usersBaseResponse,null,HttpStatus.CREATED);
			}
			else
			{
				usersBaseResponse.setStatus(UsersResponse.getStatus());
				usersBaseResponse.setReasonText(UsersResponse.getReasonText());
				usersBaseResponse.setResponseObject(UsersResponse.getResponseObject());
				return new ResponseEntity<BaseResponse<Users>>(usersBaseResponse,null,HttpStatus.NOT_FOUND);
			}
		}
		else
		{
			usersBaseResponse.setStatus(CommonConstants.FAIL);
			usersBaseResponse.setReasonText(CommonConstants.INPUT_OBJECT_NULL);
			return new ResponseEntity<BaseResponse<Users>>(usersBaseResponse,null,HttpStatus.NO_CONTENT);
		}
	}
	
	
	
	
	@GetMapping("/getUser")
	public ResponseEntity<BaseResponse<UsersModel>> getUser(HttpServletRequest request)
	{
		BaseResponse<UsersModel> baseResponse=new BaseResponse<>();
		
			BaseResponse<UsersModel> usersData=usersServiceInterf.getUsers();
			if(usersData.getStatus().equalsIgnoreCase("SUCCESS"))
			{
				baseResponse.setStatus(usersData.getStatus());
				baseResponse.setReasonCode(usersData.getReasonText());
				baseResponse.setResponseListObject(usersData.getResponseListObject());
				return new ResponseEntity<BaseResponse<UsersModel>>(baseResponse,null,HttpStatus.FOUND);
			}
			else
			{
				baseResponse.setStatus(usersData.getStatus());
				baseResponse.setReasonCode(usersData.getReasonText());
				baseResponse.setResponseListObject(usersData.getResponseListObject());
				return new ResponseEntity<BaseResponse<UsersModel>>(baseResponse,null,HttpStatus.NOT_FOUND);
			}
			
		}
		
	}

