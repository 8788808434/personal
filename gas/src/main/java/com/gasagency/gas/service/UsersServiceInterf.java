package com.gasagency.gas.service;

import com.gasagency.gas.entity.Users;
import com.gasagency.gas.utility.BaseResponse;

public interface UsersServiceInterf {

	public BaseResponse<Users> saveUsers(Users users);
}
