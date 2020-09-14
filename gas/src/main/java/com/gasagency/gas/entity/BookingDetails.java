package com.gasagency.gas.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name="txn_booking_details")
public class BookingDetails
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="booking_id")
	private Integer bookingId;
	
	@Column(name="booker_name")
	private String bookerName;
	
	@Column(name="total_cylinder_count")
	private Integer totalCylinderCount;
	
	@Column(name="booking_date")
	private Date bookingDate;
	
	@JoinColumn(name="user_id")
	@ManyToOne
	private User userId;
}
