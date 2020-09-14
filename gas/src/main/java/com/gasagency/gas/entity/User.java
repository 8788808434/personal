package com.gasagency.gas.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name="txn_user")
public class User 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private Integer userId;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="middle_name")
	private String middleName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email_id")
	private String emailId;
	
	@Column(name="mobile_number")
	private String mobileNumber;
	
	@Lob
	@Column(name="pan_card")
	private byte[] panCard;
	
	@Lob
	@Column(name="adhar_card")
	private byte[] adharCard;
	
	@Column(name="date_of_birth")
	private Date dateOfBirth;
	
	@Column(name="register_date")
	private Date registerDate;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="password")
	private String password;
	
	@Column(name="login_attempt")
	private Integer loginAttempt;
	
	@Column(name="status")
	private Integer status;
	
	@Lob
	@Column(name="profile")
	private byte[] profile;
	
	@JoinColumn(name="agency_id")
	@ManyToOne
	private Agency agencyId;

	@JoinColumn(name="local_address_id")
	@OneToOne
	private LocalAddress localAddress;
	
	@JoinColumn(name="permanant_address_id")
	@OneToOne
	private PermanantAddress permanantAddress;
	
	@OneToMany(mappedBy="userId")
	private List<BookingDetails> bookingList;

}
