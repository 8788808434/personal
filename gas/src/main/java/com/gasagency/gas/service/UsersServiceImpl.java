package com.gasagency.gas.service;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gasagency.gas.entity.Users;
import com.gasagency.gas.repository.UsersRepository;
import com.gasagency.gas.utility.BaseResponse;
import com.gasagency.gas.utility.CommonConstants;
import com.gasagency.gas.utility.ConversionConstants;
import com.gasagency.gas.utility.EncryptioDecryption;

@Service
public class UsersServiceImpl implements UsersServiceInterf{

	@Autowired
	UsersRepository usersRepository;
	
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

	
}
