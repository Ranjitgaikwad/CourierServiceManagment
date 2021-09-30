package com.app.pojo;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.SecondaryTable;

import org.hibernate.annotations.UpdateTimestamp;

/*@Embeddable


public class CourierAction {
	@Enumerated(EnumType.STRING)
	@Column(length = 20,table="courier_action")
	private Status status;
	@Column(length = 50,table="courier_action")
	private String remark;
	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	@UpdateTimestamp
	@Column(name = "upd_date",table="courier_action")
	private Date updateAt;
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
	public Date getUpdateAt() {
		return updateAt;
	}
	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}
	public CourierAction(Status status, String remark, Date updateAt) {
		super();
		this.status = status;
		this.remark = remark;
		this.updateAt = updateAt;
	}
	public CourierAction() {
		
	}
	@Override
	public String toString() {
		return "CourierAction [status=" + status + ", remark=" + remark + ", updateAt=" + updateAt + "]";
	}
	
}*/
