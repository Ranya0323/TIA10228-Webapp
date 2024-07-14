package com.cupRecord.model;

import java.util.*;

public interface CupRecordDAO_interface {
          public void insert(CupRecordVO empVO);
          public void update(CupRecordVO empVO);
          public void delete(Integer empno); //一般來說 資料庫不會做刪除動做
          public CupRecordVO findByPrimaryKey(Integer empno);
          public List<CupRecordVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
