package com.cup.model;

import java.util.*;

public interface CupDAO_interface {
          public void insert(CupVO empVO);
          public void create(CupVO empVO);
          public void update(CupVO empVO);
          public void delete(Integer empno); //一般來說 資料庫不會做刪除動做
          public CupVO findByPrimaryKey(Integer empno);
          public List<CupVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
