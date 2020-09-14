package com.gasagency.gas.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CascadeType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name="mst_agency")
public class Agency
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="agency_id")
	private Integer agencyId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="country")
	private String country;
	
	@Column(name="state")
	private String state;
	
	@Column(name="city")
	private String city;
	
	@Column(name="zip")
	private String zip;
	
	@OneToMany(mappedBy="agencyId")
	private List<User> userlist;
	
	
}
