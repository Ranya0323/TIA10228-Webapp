package com.drink.model;

import java.util.*;

public interface DrinkDAO_interface {
          public void insert(DrinkVO drinkVO);
          public void update(DrinkVO drinkVO);
          public void delete(Integer drinkno); //一般來說 資料庫不會做刪除動做
          public DrinkVO findByPrimaryKey(Integer drinkno);
          public List<DrinkVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
