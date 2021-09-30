package com.app.pojo;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "courier")
public class Courier {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "c_id")
	private int courierId;
	// @OneToOne
	// JoinColumn(name = "b_Id")
	// private Branch branchId;
	//@Column(length = 100,nullable = false)
	//@GeneratedValue(strategy = GenerationType.AUTO)

	//private String trackingNumber;
	@Column(length = 100)
	private String trackingId= ""+((int)(Math.random()*9000)+1000);
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private CourierType courierType;
	@Column(length = 50)
	private String senderName;

	@Column(length = 20)
	private String senderContact;
	@Column(length = 50)
	private String senderAddress;
	@Column(length = 50)
	private String senderCity;

	@Column(length = 20)
	private String senderState;
	@Column(length = 20)
	private String senderPin;
	@Column(length = 20)
	private String senderCountry;
	@Column(length = 50)
	private String recipientName;
	@Column(length = 20)
	private String recipientContact;
	@Column(length = 50)
	private String recipientAddress;
	@Column(length = 20)
	private String recipientCity;
	@Column(length = 20)
	private String recipientState;
	@Column(length = 20)
	private String recipientPin;
	@Column(length = 20)
	private String recipientCountry;
	@Column(length = 20)
	private double courierWeight;
	@Column(length = 20)
	@Enumerated(EnumType.STRING)
	private Mode courierMode;
	@CreationTimestamp
	@Column(name = "cre_date")
	private Date createAt;
	@UpdateTimestamp
	@Column(name = "upd_date")
	private Date updateAt;
	@Column(length = 20)
	private double price;
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private Status status;
	@Column(length = 50)
	private String remark;
	
	public Courier() {
		System.out.println("in ctor of user pojo");
	}

	public Courier(int courierId, String trackingId, CourierType courierType, String senderName, String senderContact,
			String senderAddress, String senderCity, String senderState, String senderPin, String senderCountry,
			String recipientName, String recipientContact, String recipientAddress, String recipientCity,
			String recipientState, String recipientPin, String recipientCountry, double courierWeight, Mode courierMode,
			Date createAt, Date updateAt, double price, Status status, String remark) {
		super();
		this.courierId = courierId;
		this.trackingId = trackingId;
		this.courierType = courierType;
		this.senderName = senderName;
		this.senderContact = senderContact;
		this.senderAddress = senderAddress;
		this.senderCity = senderCity;
		this.senderState = senderState;
		this.senderPin = senderPin;
		this.senderCountry = senderCountry;
		this.recipientName = recipientName;
		this.recipientContact = recipientContact;
		this.recipientAddress = recipientAddress;
		this.recipientCity = recipientCity;
		this.recipientState = recipientState;
		this.recipientPin = recipientPin;
		this.recipientCountry = recipientCountry;
		this.courierWeight = courierWeight;
		this.courierMode = courierMode;
		this.createAt = createAt;
		this.updateAt = updateAt;
		this.price = price;
		this.status = status;
		this.remark = remark;
	}

	public int getCourierId() {
		return courierId;
	}

	public void setCourierId(int courierId) {
		this.courierId = courierId;
	}

	public String getTrackingId() {
		return trackingId;
	}

	//public void setTrackingId(String trackingId) {
	//	this.trackingId = trackingId;
	//}

	public CourierType getCourierType() {
		return courierType;
	}

	public void setCourierType(CourierType courierType) {
		this.courierType = courierType;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getSenderContact() {
		return senderContact;
	}

	public void setSenderContact(String senderContact) {
		this.senderContact = senderContact;
	}

	public String getSenderAddress() {
		return senderAddress;
	}

	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}

	public String getSenderCity() {
		return senderCity;
	}

	public void setSenderCity(String senderCity) {
		this.senderCity = senderCity;
	}

	public String getSenderState() {
		return senderState;
	}

	public void setSenderState(String senderState) {
		this.senderState = senderState;
	}

	public String getSenderPin() {
		return senderPin;
	}

	public void setSenderPin(String senderPin) {
		this.senderPin = senderPin;
	}

	public String getSenderCountry() {
		return senderCountry;
	}

	public void setSenderCountry(String senderCountry) {
		this.senderCountry = senderCountry;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getRecipientContact() {
		return recipientContact;
	}

	public void setRecipientContact(String recipientContact) {
		this.recipientContact = recipientContact;
	}

	public String getRecipientAddress() {
		return recipientAddress;
	}

	public void setRecipientAddress(String recipientAddress) {
		this.recipientAddress = recipientAddress;
	}

	public String getRecipientCity() {
		return recipientCity;
	}

	public void setRecipientCity(String recipientCity) {
		this.recipientCity = recipientCity;
	}

	public String getRecipientState() {
		return recipientState;
	}

	public void setRecipientState(String recipientState) {
		this.recipientState = recipientState;
	}

	public String getRecipientPin() {
		return recipientPin;
	}

	public void setRecipientPin(String recipientPin) {
		this.recipientPin = recipientPin;
	}

	public String getRecipientCountry() {
		return recipientCountry;
	}

	public void setRecipientCountry(String recipientCountry) {
		this.recipientCountry = recipientCountry;
	}

	public double getCourierWeight() {
		return courierWeight;
	}

	public void setCourierWeight(double courierWeight) {
		this.courierWeight = courierWeight;
	}

	public Mode getCourierMode() {
		return courierMode;
	}

	public void setCourierMode(Mode courierMode) {
		this.courierMode = courierMode;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Courier [courierId=" + courierId + ", trackingId=" + trackingId + ", courierType=" + courierType
				+ ", senderName=" + senderName + ", senderContact=" + senderContact + ", senderAddress=" + senderAddress
				+ ", senderCity=" + senderCity + ", senderState=" + senderState + ", senderPin=" + senderPin
				+ ", senderCountry=" + senderCountry + ", recipientName=" + recipientName + ", recipientContact="
				+ recipientContact + ", recipientAddress=" + recipientAddress + ", recipientCity=" + recipientCity
				+ ", recipientState=" + recipientState + ", recipientPin=" + recipientPin + ", recipientCountry="
				+ recipientCountry + ", courierWeight=" + courierWeight + ", courierMode=" + courierMode + ", createAt="
				+ createAt + ", updateAt=" + updateAt + ", price=" + price + ", status=" + status + ", remark=" + remark
				+ "]";
	}

	
}
