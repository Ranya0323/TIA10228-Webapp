package com.notify.model;

import java.sql.Date;

public class NotifyVO implements java.io.Serializable {
	private Integer notify_id;
	private Integer user_id;
	private String notify_subject;
	private String notify_message;
	private Date notify_time;

	public Integer getNotify_id() {
		return notify_id;
	}

	public void setNotify_id(Integer notify_id) {
		this.notify_id = notify_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getNotify_subject() {
		return notify_subject;
	}

	public void setNotify_subject(String notify_subject) {
		this.notify_subject = notify_subject;
	}

	public String getNotify_message() {
		return notify_message;
	}

	public void setNotify_message(String notify_message) {
		this.notify_message = notify_message;
	}

	public Date getNotify_time() {
		return notify_time;
	}

	public void setNotify_time(Date notify_time) {
		this.notify_time = notify_time;
	}

}
