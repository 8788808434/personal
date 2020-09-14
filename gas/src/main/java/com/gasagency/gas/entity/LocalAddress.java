package com.gasagency.gas.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name="txn_local_address")
public class LocalAddress 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="local_Address_Id")
	private Integer localAddressId;
	
	@Column(name="country")	
	private String country;
	
	@Column(name="state")
	private String state;
	
	@Column(name="city")
	private String city;
	
	@Column(name="street")
	private String street;
	
	@Column(name="zip")
	private String zip;
}
