package com.drink.model;

import java.sql.Date;

public class DrinkVO implements java.io.Serializable {
	private Integer drink_id;
	private String drink_name;
	private Integer drink_price;
	private String drink_des;
	private byte[] drink_pic; // Blob
	private String drink_tag;
	private Integer drink_status; // 飲料的上架狀態
	private Date drink_updateDate;
	private Date drink_createDate;
	private Integer edited_by; // 編輯者
    private Integer created_by; // 建立者
	// FK不確定是不是要這樣表示

	public Integer getEdited_by() {
		return edited_by;
	}

	public void setEdited_by(Integer edited_by) {
		this.edited_by = edited_by;
	}

	public Integer getCreated_by() {
		return created_by;
	}

	public void setCreated_by(Integer created_by) {
		this.created_by = created_by;
	}

	public Integer getDrink_id() {
		return drink_id;
	}

	public void setDrink_id(Integer drink_id) {
		this.drink_id = drink_id;
	}

	public String getDrink_name() {
		return drink_name;
	}

	public void setDrink_name(String drink_name) {
		this.drink_name = drink_name;
	}

	public Integer getDrink_price() {
		return drink_price;
	}

	public void setDrink_price(Integer drink_price) {
		this.drink_price = drink_price;
	}

	public String getDrink_des() {
		return drink_des;
	}

	public void setDrink_des(String drink_des) {
		this.drink_des = drink_des;
	}

	public byte[] getDrink_pic() {
		return drink_pic;
	}

	public void setDrink_pic(byte[] drink_pic) {
		this.drink_pic = drink_pic;
	}

	public String getDrink_tag() {
		return drink_tag;
	}

	public void setDrink_tag(String drink_tag) {
		this.drink_tag = drink_tag;
	}

	public Integer getDrink_status() {
		return drink_status;
	}

	public void setDrink_status(Integer drink_status) {
		this.drink_status = drink_status;
	}

	public Date getDrink_updateDate() {
		return drink_updateDate;
	}

	public void setDrink_updateDate(Date drink_updateDate) {
		this.drink_updateDate = drink_updateDate;
	}

	public Date getDrink_createDate() {
		return drink_createDate;
	}

	public void setDrink_createDate(Date drink_createDate) {
		this.drink_createDate = drink_createDate;
	}
}