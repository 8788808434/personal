package com.gasagency.gas.model;



import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UsersLoginModel {

	@Column(name="user_name")
	private String userName;
	
	@Column(name="password")
	private String password;
}
