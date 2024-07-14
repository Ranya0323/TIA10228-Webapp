package com.drink.model;

import java.sql.Date;
import java.util.List;

public class DrinkService {
	
	private DrinkDAO_interface dao;

	public DrinkService() {
		dao = new DrinkJDBCDAO();
	}

	public DrinkVO addDrink(String drink_name, Integer drink_price, String drink_des,
			byte[] drink_pic, String drink_tag, Integer drink_status, Integer create_by) {

		DrinkVO drinkVO = new DrinkVO();

		
		drinkVO.setDrink_name(drink_name);
		drinkVO.setDrink_price(drink_price);
		drinkVO.setDrink_des(drink_des);
		drinkVO.setDrink_pic(drink_pic); // 飲料圖片
		drinkVO.setDrink_tag(drink_tag);
		drinkVO.setDrink_status(drink_status); // 上架
		drinkVO.setCreated_by(create_by); // 目前為測試用途
		dao.insert(drinkVO);

		return drinkVO;
	}

	public DrinkVO updateDrink(Integer drink_id, String drink_name, Integer drink_price, String drink_des,
			byte[] drink_pic, String drink_tag, Integer drink_status, Date drink_updateDate) {

		DrinkVO drinkVO = new DrinkVO();

		drinkVO.setDrink_id(drink_id);
		drinkVO.setDrink_name(drink_name);
		drinkVO.setDrink_price(drink_price);
		drinkVO.setDrink_des(drink_des);
		drinkVO.setDrink_pic(drink_pic); // 飲料圖片
		drinkVO.setDrink_tag(drink_tag);
		drinkVO.setDrink_status(drink_status); // 上架
		drinkVO.setDrink_updateDate(drink_updateDate);
		dao.update(drinkVO);

		return drinkVO;
	}

	public void deleteEmp(Integer empno) {
		dao.delete(empno);
	}

	public DrinkVO getOneDrink(Integer drink_id) {
		return dao.findByPrimaryKey(drink_id);
	}

	public List<DrinkVO> getAll() {
		return dao.getAll();
	}
}
