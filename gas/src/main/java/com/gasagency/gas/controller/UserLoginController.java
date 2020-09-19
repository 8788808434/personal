package com.gasagency.gas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gasagency.gas.model.UsersLoginModel;
import com.gasagency.gas.service.UserLoginServiceInterf;
import com.gasagency.gas.utility.BaseResponse;
import com.gasagency.gas.utility.CommonConstants;

@RestController
@RequestMapping("/api/v1")
public class UserLoginController {

	@Autowired
	UserLoginServiceInterf userLoginServiceInterf;
	
	@PostMapping("/userLogin")
	public ResponseEntity<BaseResponse<?>> userLogin(@RequestBody UsersLoginModel users)
	{
		BaseResponse<?> baseResponse=new BaseResponse<>();
		if(users!=null)
		{
			BaseResponse<?> jwtBaseResponse=userLoginServiceInterf.login(users);
			if(jwtBaseResponse.getStatus().equalsIgnoreCase(CommonConstants.SUCCESS))
			{
				baseResponse.setStatus(jwtBaseResponse.getStatus());
				baseResponse.setReasonText(jwtBaseResponse.getReasonText());
				baseResponse.setJwt(jwtBaseResponse.getJwt());
				return new ResponseEntity<BaseResponse<?>>(baseResponse,null,HttpStatus.ACCEPTED);
			}
			else
			{
				baseResponse.setStatus(jwtBaseResponse.getStatus());
				baseResponse.setReasonText(jwtBaseResponse.getReasonText());
				return new ResponseEntity<BaseResponse<?>>(baseResponse,null,HttpStatus.ACCEPTED);
			}
			
		}
		else
		{
			baseResponse.setStatus(CommonConstants.FAIL);
			baseResponse.setReasonText(CommonConstants.INPUT_OBJECT_NULL);
			return new ResponseEntity<BaseResponse<?>>(baseResponse,null,HttpStatus.ACCEPTED);
		}
	}
}
