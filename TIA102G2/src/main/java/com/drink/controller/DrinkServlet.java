package com.drink.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.drink.model.DrinkJDBCDAO;
import com.drink.model.DrinkService;
import com.drink.model.DrinkVO;

@MultipartConfig
public class DrinkServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getAll".equals(action)) {
			/*************************** 開始查詢資料 ****************************************/
			DrinkJDBCDAO dao = new DrinkJDBCDAO();
			List<DrinkVO> list = dao.getAll();

			/*************************** 查詢完成,準備轉交(Send the Success view) *************/
			HttpSession session = req.getSession();
			session.setAttribute("list", list); // 資料庫取出的list物件,存入session
			// Send the Success view
			String url = "/back-end/emp/listAllDrink2_getFromSession.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交listAllEmp2_getFromSession.jsp
			successView.forward(req, res);
			return;
		}

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("drink_id");
			if (str == null || (str.trim()).length() == 0) { // str == null 防呆用 如果省略也可以
				errorMsgs.add("請輸入飲品ID");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer drink_id = null;
			try {
				drink_id = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("飲品ID格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			DrinkJDBCDAO dao = new DrinkJDBCDAO();
			DrinkVO drinkVO = dao.findByPrimaryKey(drink_id);
//				EmpVO empVO = dao.findByPrimaryKey(Integer.valueOf(req.getParameter("empno")));
			if (drink_id == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("drinkVO", drinkVO); // 資料庫取出的empVO物件,存入req
			String url = "/back-end/emp/listOneDrink.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			

			/*************************** 1.接收請求參數 ****************************************/
			Integer drink_id = Integer.valueOf(req.getParameter("drink_id"));

			/*************************** 2.開始查詢資料 ****************************************/
			DrinkService drinkSvc = new DrinkService();
			DrinkVO drinkVO = drinkSvc.getOneDrink(drink_id);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			String param = "?drink_id=" + drinkVO.getDrink_id() 
						 + "&drink_name=" + drinkVO.getDrink_name() 
						 + "&drink_price="+ drinkVO.getDrink_price() 
						 + "&drink_des=" + drinkVO.getDrink_des() 
						 + "&drink_pic="+ drinkVO.getDrink_pic() 
						 + "&drink_tag=" + drinkVO.getDrink_tag() 
						 + "&drink_status="+ drinkVO.getDrink_status() 
						 + "&drink_updateDate=" + drinkVO.getDrink_updateDate();
			String url = "/emp/update_drink_input.jsp" + param;
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer drink_id = Integer.valueOf(req.getParameter("drink_id").trim());

			String drink_name = req.getParameter("drink_name");
			String drink_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (drink_name == null || drink_name.trim().length() == 0) {
				errorMsgs.put("drink_name", "飲品名稱: 請勿空白");
			} else if (!drink_name.trim().matches(drink_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.put("drink_name", "飲品名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}
			
			Integer drink_price = null;
			try {
				drink_price = Integer.valueOf(req.getParameter("drink_price").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("drink_price", "價格請填數字");
			}

//			String job = req.getParameter("job").trim();
//			if (job == null || job.trim().length() == 0) {
//				errorMsgs.put("job", "職位請勿空白");
//			}
			
			String drink_des = req.getParameter("drink_des").trim();
			
			 Part filePart = req.getPart("drink_pic");
	         byte[] drink_pic = null;
	            if (filePart != null && filePart.getSize() > 0) {
	                InputStream inputStream = filePart.getInputStream();
	                drink_pic = inputStream.readAllBytes();
	            }
			
			String drink_tag = req.getParameter("drink_tag").trim();
			if (drink_tag == null || drink_tag.trim().length() == 0) {
				errorMsgs.put("drink_tag", "商品標籤請勿空白");
			}

			Integer drink_status = null;
			try {
				drink_status = Integer.valueOf(req.getParameter("drink_status").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("drink_status", "狀態請填數字");
			}
			
			java.sql.Date drink_updateDate = null;
			try {
				drink_updateDate = java.sql.Date.valueOf(req.getParameter("drink_updateDate").trim());
			} catch (IllegalArgumentException e) {
				errorMsgs.put("drink_updateDate", "請輸入日期");
			}

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/emp/update_emp_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			DrinkService drinkSvc = new DrinkService();
			DrinkVO drinkVO = drinkSvc.updateDrink(drink_id, drink_name, drink_price, drink_des, drink_pic, drink_tag, drink_status, drink_updateDate);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("drinkVO", drinkVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/emp/listOneDrink.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}
		
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求  

		    Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		    req.setAttribute("errorMsgs", errorMsgs);

		    /***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
		    String drink_name = req.getParameter("drink_name");
		    String drink_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
		    if (drink_name == null || drink_name.trim().length() == 0) {
		        errorMsgs.put("drink_name", "飲品名稱: 請勿空白");
		    } else if (!drink_name.trim().matches(drink_nameReg)) {
		        errorMsgs.put("drink_name", "飲品名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
		    }

		    Integer drink_price = null;
		    try {
		        drink_price = Integer.valueOf(req.getParameter("drink_price").trim());
		    } catch (NumberFormatException e) {
		        errorMsgs.put("drink_price", "價格請填數字");
		    }

		    String drink_des = req.getParameter("drink_des").trim();

		    Part filePart = req.getPart("drink_pic");
		    byte[] drink_pic = null;
		    if (filePart != null && filePart.getSize() > 0) {
		        InputStream inputStream = filePart.getInputStream();
		        drink_pic = inputStream.readAllBytes();
		    }

		    String drink_tag = req.getParameter("drink_tag").trim();
		    if (drink_tag == null || drink_tag.trim().length() == 0) {
		        errorMsgs.put("drink_tag", "商品標籤請勿空白");
		    }

		    Integer drink_status = null;
		    try {
		        drink_status = Integer.valueOf(req.getParameter("drink_status").trim());
		    } catch (NumberFormatException e) {
		        errorMsgs.put("drink_status", "狀態請填數字");
		    }

		    Integer created_by = null;
		    try {
		        created_by = Integer.valueOf(req.getParameter("created_by").trim());
		    } catch (NumberFormatException e) {
		        errorMsgs.put("created_by", "建立者請填數字");
		    }

		    // Send the user back to the form, if there were errors
		    if (!errorMsgs.isEmpty()) {
		        RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/addDrink.jsp");
		        failureView.forward(req, res);
		        return;
		    }

		    /***************************2.開始新增資料***************************************/
		    DrinkService drinkSvc = new DrinkService();
		    drinkSvc.addDrink(drink_name, drink_price, drink_des, drink_pic, drink_tag, drink_status, created_by);

		    /***************************3.新增完成,準備轉交(Send the Success view)***********/
		    res.sendRedirect(req.getContextPath() + "/back-end/emp/listAllDrink1_byDAO.jsp"); // 使用 sendRedirect 重定向
//		    String url = "/back-end/emp/listAllDrink.jsp";
//		    RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllDrink.jsp
//		    successView.forward(req, res);
		}
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.接收請求參數***************************************/
				Integer drink_id = Integer.valueOf(req.getParameter("drink_id"));
				
				/***************************2.開始刪除資料***************************************/
				DrinkService drinkSvc = new DrinkService();
				drinkSvc.deleteEmp(drink_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/emp/listAllDrink1_byDAO.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
		}

	}
}
