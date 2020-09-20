package com.gasagency.gas.service;



import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gasagency.gas.GasApplication;
import com.gasagency.gas.entity.Users;
import com.gasagency.gas.model.UsersModel;
import com.gasagency.gas.repository.UsersRepository;
import com.gasagency.gas.utility.BaseResponse;
import com.gasagency.gas.utility.Cmpress;
import com.gasagency.gas.utility.CommonConstants;
import com.gasagency.gas.utility.ConversionConstants;
import com.gasagency.gas.utility.EncryptioDecryption;

@Service
public class UsersServiceImpl implements UsersServiceInterf{

	@Autowired
	UsersRepository usersRepository;
	
	
	@Value("${jwtVerify.url}")
	private String verifyString;
	
	@Override
	public BaseResponse<Users> saveUsers(Users users) {
		BaseResponse<Users> baseResponse=new BaseResponse<>();
		List<Users> listUsers=new ArrayList<>();
		try
		{
			int count=0;
			listUsers=usersRepository.findAll();
			if(!listUsers.isEmpty())
			{
				for(Users getUser:listUsers)
				{
					if(getUser.getUserName().equalsIgnoreCase(users.getUserName()) || getUser.getEmailId().equalsIgnoreCase(users.getEmailId()) || getUser.getMobileNumber().equalsIgnoreCase(users.getMobileNumber()))
					{
						count=1;
						baseResponse.setStatus(CommonConstants.FAIL);
						baseResponse.setReasonText(CommonConstants.DUPLICATION_USERNAME_MOBILENO_EMAIL);
						return baseResponse;
					}
					if(count==1)
					{break;}
				}
	
				if(count==0)
				{
				try
				{
					users.setPassword(EncryptioDecryption.encrypt(users.getPassword()));
					users.setAdharCard(ConversionConstants.byteToString(users.getAdharCard()));
					users.setPanCard(ConversionConstants.byteToString(users.getPanCard()));
					users.setProfile(ConversionConstants.byteToString(users.getProfile()));
				}
				catch(Exception e)
				{
					baseResponse.setStatus(CommonConstants.FAIL);
					baseResponse.setReasonText(e.getMessage());
					return baseResponse;
				}
				Users UsersResponse=usersRepository.save(users);
				if(UsersResponse!=null)
				{
					baseResponse.setStatus(CommonConstants.SUCCESS);
					baseResponse.setReasonText(CommonConstants.USERS_SAVED_SUCCESS);
					baseResponse.setResponseObject(UsersResponse);
					return baseResponse;
				}
				else
				{
					baseResponse.setStatus(CommonConstants.FAIL);
					baseResponse.setReasonText(CommonConstants.USERS_SAVED_FAILURE);
					baseResponse.setResponseObject(UsersResponse);
					return baseResponse;
				}
			}
		}
				else
				{
					try
					{
						users.setPassword(EncryptioDecryption.encrypt(users.getPassword()));
						users.setAdharCard(ConversionConstants.byteToString(users.getAdharCard()));
						users.setPanCard(ConversionConstants.byteToString(users.getPanCard()));
						users.setProfile(ConversionConstants.byteToString(users.getProfile()));
					}
					catch(Exception e)
					{
						baseResponse.setStatus(CommonConstants.FAIL);
						baseResponse.setReasonText(e.getMessage());
						return baseResponse;
					}
					Users UsersResponse=usersRepository.save(users);
					if(UsersResponse!=null)
					{
						baseResponse.setStatus(CommonConstants.SUCCESS);
						baseResponse.setReasonText(CommonConstants.USERS_SAVED_SUCCESS);
						baseResponse.setResponseObject(UsersResponse);
						return baseResponse;
					}
					else
					{
						baseResponse.setStatus(CommonConstants.FAIL);
						baseResponse.setReasonText(CommonConstants.USERS_SAVED_FAILURE);
						baseResponse.setResponseObject(UsersResponse);
						return baseResponse;
					}
				}
			}
			catch(Exception e)
			{
				baseResponse.setStatus(CommonConstants.SUCCESS);
				baseResponse.setReasonText(e.getMessage());
				return baseResponse;
			}
		return baseResponse;
		
		
		}

	@Override
	public BaseResponse<UsersModel> getUsers() {
		
		
		List<UsersModel> listUserModel=new ArrayList<>();
		BaseResponse<UsersModel> userBaseResponse=new BaseResponse<>();
		try
		{
			//UsersModel usersModel=new UsersModel();
			/*RestTemplate response=GasApplication.get();
			HttpHeaders headers = new HttpHeaders();
			headers.set("jwt",jwt);
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			
			HttpEntity request = new HttpEntity(headers);
			ResponseEntity<BaseResponse>verifyResponse=response.exchange(verifyString, HttpMethod.GET, request, BaseResponse.class);
			BaseResponse<?> baseResponse=verifyResponse.getBody();*/
			//if(baseResponse.getStatus().equalsIgnoreCase("verify"))
			//{
				List<Users> usersList=usersRepository.findAll();
				if(!usersList.isEmpty())
				{
				for(Users users:usersList)
				{
					UsersModel usersModel=new UsersModel();
					usersModel.setAdharCard(Cmpress.decompressBytes(Base64.getDecoder().decode(users.getAdharCard())));
					usersModel.setPanCard(Cmpress.decompressBytes(Base64.getDecoder().decode(users.getPanCard())));
					usersModel.setProfile(Cmpress.decompressBytes(Base64.getDecoder().decode(users.getProfile())));
					usersModel.setUserId(users.getUserId());
					usersModel.setFirstName(users.getFirstName());
					usersModel.setMiddleName(users.getMiddleName());
					usersModel.setLastName(users.getLastName());
					usersModel.setDateOfBirth(users.getDateOfBirth());
					usersModel.setEmailId(users.getEmailId());
					usersModel.setLocalAddress(users.getLocalAddress());
					usersModel.setPermanantAddress(users.getPermanantAddress());
					usersModel.setMobileNumber(users.getMobileNumber());
					
					listUserModel.add(usersModel);
				}
				userBaseResponse.setStatus(CommonConstants.SUCCESS);
				userBaseResponse.setReasonText(CommonConstants.USERS_GET_SUCCESS);
				userBaseResponse.setResponseListObject(listUserModel);
				return userBaseResponse;
				}
				else
				{
					userBaseResponse.setStatus(CommonConstants.FAIL);
					userBaseResponse.setReasonText(CommonConstants.INPUT_OBJECT_NULL);
					userBaseResponse.setResponseListObject(listUserModel);
					return userBaseResponse;
				}
			//}
			/*else
			{
				userBaseResponse.setStatus(CommonConstants.FAIL);
				userBaseResponse.setReasonText(CommonConstants.USERS_GET_FAILURE);
				userBaseResponse.setResponseListObject(listUserModel);
				return userBaseResponse;
			}*/
		}
		catch(Exception e)
		{
			userBaseResponse.setStatus(CommonConstants.FAIL);
			userBaseResponse.setReasonText(e.getMessage());
			return userBaseResponse;
		}
	}

	
	
}
