package com.userJibei.model;

public class UserJibeiVO implements java.io.Serializable {
	private Integer userJibei_id;
	private Integer user_id;
	private Integer drink_id;
	private Integer userJibei_amount;

	public Integer getUserJibei_id() {
		return userJibei_id;
	}

	public void setUserJibei_id(Integer userJibei_id) {
		this.userJibei_id = userJibei_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getDrink_id() {
		return drink_id;
	}

	public void setDrink_id(Integer drink_id) {
		this.drink_id = drink_id;
	}

	public Integer getUserJibei_amount() {
		return userJibei_amount;
	}

	public void setUserJibei_amount(Integer userJibei_amount) {
		this.userJibei_amount = userJibei_amount;
	}
}
