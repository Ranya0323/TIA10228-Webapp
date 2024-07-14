package com.drinkCart.model;

import java.util.*;

public interface DrinkCartDAO_interface {
          public void insert(DrinkCartVO drinkVO);
          public void update(DrinkCartVO drinkVO);
          public void delete(Integer drinkno); //一般來說 資料庫不會做刪除動做
          public DrinkCartVO findByPrimaryKey(Integer drinkno);
          public List<DrinkCartVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
