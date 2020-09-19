package com.gasagency.gas.service;

import com.gasagency.gas.model.UsersLoginModel;
import com.gasagency.gas.utility.BaseResponse;

public interface UserLoginServiceInterf {

	public BaseResponse<?> login(UsersLoginModel usersLoginModel);
}
