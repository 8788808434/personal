package com.gasagency.gas.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gasagency.gas.GasApplication;
import com.gasagency.gas.model.UsersLoginModel;
import com.gasagency.gas.utility.BaseResponse;
import com.gasagency.gas.utility.CommonConstants;
import com.gasagency.gas.utility.EncryptioDecryption;



@Service
public class UserLoginServiceImpl implements UserLoginServiceInterf{

	@Value("${jwt.url}")
	private String url;
	
	@Override
	public BaseResponse<?> login(UsersLoginModel usersLoginModel) {
		BaseResponse<?> baseResponse=new BaseResponse<>();
		try
		{
			if(usersLoginModel.getPassword()!=null)
			{
				usersLoginModel.setPassword(EncryptioDecryption.encrypt(usersLoginModel.getPassword()));
				
			}
			else
			{
				baseResponse.setStatus(CommonConstants.FAIL);
				baseResponse.setReasonText(CommonConstants.INPUT_PASSWORD_NULL);
				return baseResponse;
			}
			RestTemplate restTemplate=GasApplication.get();
			BaseResponse<?> jwtBaseResponse=restTemplate.postForObject(url, usersLoginModel, BaseResponse.class);
			//BaseResponse<?> jwtBaseResponse=response.getBody();
			if(jwtBaseResponse.getStatus().equalsIgnoreCase(CommonConstants.SUCCESS))
			{
				baseResponse.setStatus(jwtBaseResponse.getStatus());
				baseResponse.setReasonText(jwtBaseResponse.getReasonText());
				baseResponse.setJwt(jwtBaseResponse.getJwt());
				return baseResponse;
			}
			else
			{
				baseResponse.setStatus(jwtBaseResponse.getStatus());
				baseResponse.setReasonText(jwtBaseResponse.getReasonText());
				return baseResponse;
				
			}
		}
		catch(Exception e)
		{
			baseResponse.setStatus(CommonConstants.FAIL);
			baseResponse.setReasonText(e.getMessage());
			return baseResponse;
		}
		
	}

}
