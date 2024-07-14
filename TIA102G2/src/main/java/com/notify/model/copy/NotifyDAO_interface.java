package com.notify.model.copy;

import java.util.*;

public interface NotifyDAO_interface {
          public void insert(NotifyVO empVO);
          public void update(NotifyVO empVO);
          public void delete(Integer empno); //一般來說 資料庫不會做刪除動做
          public NotifyVO findByPrimaryKey(Integer empno);
          public List<NotifyVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
