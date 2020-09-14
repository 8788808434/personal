package com.gasagency.gas.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name="mst_state")
public class State
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="state_id")
	private Integer stateId;
	
	@Column(name="name")
	private String name;
	
	@JoinColumn(name="country_id")
	@ManyToOne
	private Country country;
	
	@OneToMany(mappedBy="state")
	private List<City> cityList;
}
