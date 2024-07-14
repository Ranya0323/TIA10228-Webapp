package com.userJibei.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class UserJibeiJDBCDAO implements UserJibeiDAO_interface{
	private static final String CHECK_EXISTENCES_STMT =
			"SELECT * FROM userJibei WHERE user_id =? AND drink_id =?";
	private static final String CHECK_AMOUNT_STMT =
			"SELECT userJibei_amount FROM userJibei WHERE user_id =? AND drink_id =?";
	private static final String CHECK_STMT =
			"SELECT * FROM userJibei WHERE user_id =? ";
	private static final String INSERT_STMT =
			"INSERT INTO userJibei (user_id, drink_id, userJibei_amount) VALUES (?,?,?)";
	private static final String UPDATE_STMT = 
			"UPDATE userJibei SET userJibei_amount = userJibei_amount +? WHERE user_id =? AND drink_id =?";
	private static final String USE_STMT = 
			"UPDATE userJibei SET userJibei_amount = userJibei_amount -? WHERE user_id =? AND drink_id =?";
	private static final String DELETE_STMT = 
			"DELETE FROM userJibei WHERE user_id =? AND drink_id =?";
	
	@SuppressWarnings("resource")
	@Override
	public void add(UserJibeiVO userJibeiVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TIA102G2");
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(CHECK_EXISTENCES_STMT); // 檢查user是否有相同的drink_id
			pstmt.setInt(1, userJibeiVO.getUser_id());
			pstmt.setInt(2, userJibeiVO.getDrink_id());
			rs = pstmt.executeQuery();
			
			if(rs.next() && rs.getInt(1)>0) {					//如果存在，更新數量
				pstmt = con.prepareStatement(UPDATE_STMT);
				pstmt.setInt(1, userJibeiVO.getUserJibei_amount());
	            pstmt.setInt(2, userJibeiVO.getUser_id());
	            pstmt.setInt(3, userJibeiVO.getDrink_id());
	            pstmt.executeUpdate();
			}else {												//如果不存在，新增資料
				pstmt = con.prepareStatement(INSERT_STMT);
				pstmt.setInt(1, userJibeiVO.getUser_id());
				pstmt.setInt(2, userJibeiVO.getDrink_id());
				pstmt.setInt(3, userJibeiVO.getUserJibei_amount());
				
				pstmt.executeUpdate();
			}
			
		}catch(NamingException | SQLException e) {
			throw new RuntimeException("Database operation failed. "
					+ e.getMessage());
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@SuppressWarnings("resource")
	@Override
	public boolean use(UserJibeiVO userJibeiVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TIA102G2");
			con = ds.getConnection();
			
			//檢查有沒有該寄杯飲品
	        pstmt = con.prepareStatement(CHECK_AMOUNT_STMT);					
	        pstmt.setInt(1, userJibeiVO.getUser_id());
			pstmt.setInt(2, userJibeiVO.getDrink_id());
	        rs = pstmt.executeQuery();
	        
	        if(rs.next()) {
	        	//寄杯數大於預訂數,減少寄杯數
	        	if(rs.getInt(1) > userJibeiVO.getUserJibei_amount()) {
	        		pstmt = con.prepareStatement(USE_STMT);
	        		pstmt.setInt(1, userJibeiVO.getUserJibei_amount());
	        		pstmt.setInt(2, userJibeiVO.getUser_id());
	        		pstmt.setInt(3, userJibeiVO.getDrink_id());
	        		pstmt.executeUpdate();
	        		return true;
	       		//寄杯數等於預訂數,刪該筆資料
	        	}else if(rs.getInt(1)== userJibeiVO.getUserJibei_amount()) {
	        		pstmt = con.prepareStatement(DELETE_STMT);
	        		pstmt.setInt(1, userJibeiVO.getUser_id());
	        		pstmt.setInt(2, userJibeiVO.getDrink_id());
	        		return true;
	        	}else {
        		//寄杯數小於預訂數,執行失敗
	        		return false;
	        	}
	        //無該寄杯飲品，執行失敗
	        }else {
	        	return false;	        	
	        }
		}catch(NamingException | SQLException e) {
			throw new RuntimeException("Database operation failed. "
					+ e.getMessage());
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public List<UserJibeiVO> findByUser_id(Integer user_id) {
		List<UserJibeiVO> userJibeiList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TIA102G2");
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(CHECK_STMT);
			pstmt.setInt(1, user_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				UserJibeiVO userJibeiVO = new UserJibeiVO();
				userJibeiVO.setUser_id(rs.getInt("user_id"));
				userJibeiVO.setDrink_id(rs.getInt("Drink_id"));
				userJibeiVO.setUserJibei_amount(rs.getInt("UserJibei_amount"));
				
				userJibeiList.add(userJibeiVO);
			}
			
		}catch(NamingException | SQLException e) {
			throw new RuntimeException("Database operation failed. "
					+ e.getMessage());
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return userJibeiList;
	}
	
}
