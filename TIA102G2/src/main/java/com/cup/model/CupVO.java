package com.cup.model;

import java.sql.Date;

public class CupVO implements java.io.Serializable {
	private Integer cup_id;
	private Integer user_id;
	private Integer store_id;
	private Integer cup_status;
	private Date cup_rentDate;
	private Date cup_ctime;
	private Integer member_id;

	public Integer getCup_id() {
		return cup_id;
	}

	public void setCup_id(Integer cup_id) {
		this.cup_id = cup_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getStore_id() {
		return store_id;
	}

	public void setStore_id(Integer store_id) {
		this.store_id = store_id;
	}

	public Integer getCup_status() {
		return cup_status;
	}

	public void setCup_status(Integer cup_status) {
		this.cup_status = cup_status;
	}

	public Date getCup_rentDate() {
		return cup_rentDate;
	}

	public void setCup_rentDate(Date cup_rentDate) {
		this.cup_rentDate = cup_rentDate;
	}

	public Date getCup_ctime() {
		return cup_ctime;
	}

	public void setCup_ctime(Date cup_ctime) {
		this.cup_ctime = cup_ctime;
	}

	public Integer getMember_id() {
		return member_id;
	}

	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}

}
