package com.drinkCart.model;

import java.sql.Date;

public class DrinkCartVO implements java.io.Serializable {
	private Integer drinkCart_id;
	private Integer user_id;
	private Integer drink_id;
	private Integer drinkCart_isHot;
	private Integer drinkCart_useCup;
	private Integer drinkCart_amount;
	private Integer drinkCart_isJibei;
	private Integer drinkCart_price;

	public Integer getDrinkCart_id() {
		return drinkCart_id;
	}

	public void setDrinkCart_id(Integer drinkCart_id) {
		this.drinkCart_id = drinkCart_id;
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

	public Integer getDrinkCart_isHot() {
		return drinkCart_isHot;
	}

	public void setDrinkCart_isHot(Integer drinkCart_isHot) {
		this.drinkCart_isHot = drinkCart_isHot;
	}

	public Integer getDrinkCart_useCup() {
		return drinkCart_useCup;
	}

	public void setDrinkCart_useCup(Integer drinkCart_useCup) {
		this.drinkCart_useCup = drinkCart_useCup;
	}

	public Integer getDrinkCart_amount() {
		return drinkCart_amount;
	}

	public void setDrinkCart_amount(Integer drinkCart_amount) {
		this.drinkCart_amount = drinkCart_amount;
	}

	public Integer getDrinkCart_isJibei() {
		return drinkCart_isJibei;
	}

	public void setDrinkCart_isJibei(Integer drinkCart_isJibei) {
		this.drinkCart_isJibei = drinkCart_isJibei;
	}

	public Integer getDrinkCart_price() {
		return drinkCart_price;
	}

	public void setDrinkCart_price(Integer drinkCart_price) {
		this.drinkCart_price = drinkCart_price;
	}

}