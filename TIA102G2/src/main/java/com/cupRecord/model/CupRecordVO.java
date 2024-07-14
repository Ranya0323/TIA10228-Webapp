package com.cupRecord.model;

import java.sql.Date;

public class CupRecordVO implements java.io.Serializable {
	private Integer cupRecord_id;
	private Integer user_id;
	private Integer cup_id;
	private Integer drinkOrder_id;
	private Integer store_rent_id;
	private Integer store_return_id;
	private Date cupRecord_rentDate;
	private Date cupRecord_returnDate;

	public Integer getCupRecord_id() {
		return cupRecord_id;
	}

	public void setCupRecord_id(Integer cupRecord_id) {
		this.cupRecord_id = cupRecord_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getCup_id() {
		return cup_id;
	}

	public void setCup_id(Integer cup_id) {
		this.cup_id = cup_id;
	}

	public Integer getDrinkOrder_id() {
		return drinkOrder_id;
	}

	public void setDrinkOrder_id(Integer drinkOrder_id) {
		this.drinkOrder_id = drinkOrder_id;
	}

	public Integer getStore_rent_id() {
		return store_rent_id;
	}

	public void setStore_rent_id(Integer store_rent_id) {
		this.store_rent_id = store_rent_id;
	}

	public Integer getStore_return_id() {
		return store_return_id;
	}

	public void setStore_return_id(Integer store_return_id) {
		this.store_return_id = store_return_id;
	}

	public Date getCupRecord_rentDate() {
		return cupRecord_rentDate;
	}

	public void setCupRecord_rentDate(Date cupRecord_rentDate) {
		this.cupRecord_rentDate = cupRecord_rentDate;
	}

	public Date getCupRecord_returnDate() {
		return cupRecord_returnDate;
	}

	public void setCupRecord_returnDate(Date cupRecord_returnDate) {
		this.cupRecord_returnDate = cupRecord_returnDate;
	}

}
