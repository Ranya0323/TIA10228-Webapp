package com.tia102g2.drinkOrder.model;

import java.sql.Timestamp;

public class DrinkOrder {
	private Integer drinkOrderID;
	private Integer userID;
	private Integer storeID;
	private Integer drinkOrderAmount;
	private Timestamp drinkOrderPickTime;
	private Integer drinkOrderPayM;
	private Integer drinkOrderTTPrice;
	private Integer drinkOrderStatus;
	private Timestamp drinkOrderUpdateTime;
	private Timestamp drinkOrderCreateTime;
	private Integer memberID;
	
	public Integer getDrinkOrderID() {
		return drinkOrderID;
	}
	public void setDrinkOrderID(Integer drinkOrderID) {
		this.drinkOrderID = drinkOrderID;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public Integer getStoreID() {
		return storeID;
	}
	public void setStoreID(Integer storeID) {
		this.storeID = storeID;
	}
	public Integer getDrinkOrderAmount() {
		return drinkOrderAmount;
	}
	public void setDrinkOrderAmount(Integer drinkOrderAmount) {
		this.drinkOrderAmount = drinkOrderAmount;
	}
	public Timestamp getDrinkOrderPickTime() {
		return drinkOrderPickTime;
	}
	public void setDrinkOrderPickTime(Timestamp drinkOrderPickTime) {
		this.drinkOrderPickTime = drinkOrderPickTime;
	}
	public Integer getDrinkOrderPayM() {
		return drinkOrderPayM;
	}
	public void setDrinkOrderPayM(Integer drinkOrderPayM) {
		this.drinkOrderPayM = drinkOrderPayM;
	}
	public Integer getDrinkOrderTTPrice() {
		return drinkOrderTTPrice;
	}
	public void setDrinkOrderTTPrice(Integer drinkOrderTTPrice) {
		this.drinkOrderTTPrice = drinkOrderTTPrice;
	}
	public Integer getDrinkOrderStatus() {
		return drinkOrderStatus;
	}
	public void setDrinkOrderStatus(Integer drinkOrderStatus) {
		this.drinkOrderStatus = drinkOrderStatus;
	}
	public Timestamp getDrinkOrderUpdateTime() {
		return drinkOrderUpdateTime;
	}
	public void setDrinkOrderUpdateTime(Timestamp drinkOrderUpdateTime) {
		this.drinkOrderUpdateTime = drinkOrderUpdateTime;
	}
	public Timestamp getDrinkOrderCreateTime() {
		return drinkOrderCreateTime;
	}
	public void setDrinkOrderCreateTime(Timestamp drinkOrderCreateTime) {
		this.drinkOrderCreateTime = drinkOrderCreateTime;
	}
	public Integer getMemberID() {
		return memberID;
	}
	public void setMemberID(Integer memberID) {
		this.memberID = memberID;
	}

}
