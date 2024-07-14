package com.drink.model;

import java.util.*;

import com.emp.model.EmpVO;

import java.sql.*;

public class DrinkJDBCDAO implements DrinkDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/tia102g2?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO drink (drink_name,drink_price,drink_des,drink_pic,drink_tag,drink_status,created_by) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT drink_id,drink_name,drink_price,drink_des,drink_pic,drink_tag,drink_status,drink_updateDate,drink_createDate,edited_by,created_by FROM drink order by drink_id";
	private static final String GET_ONE_STMT = 
		"SELECT drink_id,drink_name,drink_price,drink_des,drink_pic,drink_tag,drink_status,drink_updateDate,drink_createDate,edited_by,created_by FROM drink where drink_id = ?";
	private static final String DELETE = 
		"DELETE FROM drink where drink_id = ?";
	private static final String UPDATE = 
		"UPDATE drink set drink_name=?, drink_price=?, drink_des=?, drink_pic=?, drink_tag=?, drink_status=?, drink_updateDate=? where drink_id = ?";

	@Override
	public void insert(DrinkVO drinkVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			// 新增飲品
			pstmt.setString(1, drinkVO.getDrink_name());
			pstmt.setInt(2, drinkVO.getDrink_price());
			pstmt.setString(3, drinkVO.getDrink_des());
			pstmt.setBytes(4, drinkVO.getDrink_pic());
			pstmt.setString(5, drinkVO.getDrink_tag());
			pstmt.setInt(6, drinkVO.getDrink_status());
			pstmt.setInt(7, drinkVO.getCreated_by());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
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
	public void update(DrinkVO drinkVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			// 更新飲品
			pstmt.setString(1, drinkVO.getDrink_name());
			pstmt.setInt(2, drinkVO.getDrink_price());
			pstmt.setString(3, drinkVO.getDrink_des());
			pstmt.setBytes(4, drinkVO.getDrink_pic());
			pstmt.setString(5, drinkVO.getDrink_tag());
			pstmt.setInt(6, drinkVO.getDrink_status());
			pstmt.setDate(7, drinkVO.getDrink_updateDate());
			pstmt.setInt(8, drinkVO.getDrink_id());
//			pstmt.setDate(7, drinkVO.getDrink_updateDate());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
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
	public void delete(Integer drink_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			// 刪除應該是用不太到 不過先保留
			pstmt.setInt(1, drink_id);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
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
	public DrinkVO findByPrimaryKey(Integer drink_id) {

		DrinkVO drinkVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, drink_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// 搜尋 找出所有資料
				drinkVO = new DrinkVO();
				drinkVO.setDrink_id(rs.getInt("drink_id"));
				drinkVO.setDrink_name(rs.getString("drink_name"));
				drinkVO.setDrink_price(rs.getInt("drink_price"));
				drinkVO.setDrink_des(rs.getString("drink_des"));
				drinkVO.setDrink_pic(rs.getBytes("drink_pic"));
				drinkVO.setDrink_tag(rs.getString("drink_tag"));
				drinkVO.setDrink_status(rs.getInt("drink_status"));
				drinkVO.setDrink_updateDate(rs.getDate("drink_updateDate"));
				drinkVO.setDrink_createDate(rs.getDate("drink_createDate"));
				drinkVO.setCreated_by(rs.getInt("created_by"));
				drinkVO.setEdited_by(rs.getInt("edited_by"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
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
		return drinkVO;
	}

	@Override
	public List<DrinkVO> getAll() {
		List<DrinkVO> list = new ArrayList<DrinkVO>();
		DrinkVO drinkVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				drinkVO = new DrinkVO();
				drinkVO.setDrink_id(rs.getInt("drink_id"));
				drinkVO.setDrink_name(rs.getString("drink_name"));
				drinkVO.setDrink_price(rs.getInt("drink_price"));
				drinkVO.setDrink_des(rs.getString("drink_des"));
				drinkVO.setDrink_pic(rs.getBytes("drink_pic"));
				drinkVO.setDrink_tag(rs.getString("drink_tag"));
				drinkVO.setDrink_status(rs.getInt("drink_status"));
				drinkVO.setDrink_updateDate(rs.getDate("drink_updateDate"));
				drinkVO.setDrink_createDate(rs.getDate("drink_createDate"));
				drinkVO.setCreated_by(rs.getInt("created_by"));
				drinkVO.setEdited_by(rs.getInt("edited_by"));
				list.add(drinkVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
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
		return list;
	}

	public static void main(String[] args) {

		DrinkJDBCDAO dao = new DrinkJDBCDAO();
		
		// 模擬獲取當前員工的 member_id 沒意外會用到session.getAttribute("member_id")
//        Integer currentMemberId = 123; // 假設當前員工的 member_id 是 123

		// 新增
//		DrinkVO drinkVO1 = new DrinkVO();
//		drinkVO1.setDrink_name("墨香紅茶");
//		drinkVO1.setDrink_price(25);
//		drinkVO1.setDrink_des("墨香紅茶介紹...");
//		drinkVO1.setDrink_pic(null); // 飲料圖片
//		drinkVO1.setDrink_tag("紅茶");
//		drinkVO1.setDrink_status(1); // 上架
////		drinkVO1.setDrink_updateDate(null);
////		drinkVO1.setDrink_createDate(java.sql.Date.valueOf("2024-07-06"));
//		drinkVO1.setCreated_by(1); // 目前為測試用途
//		dao.insert(drinkVO1);

		// 修改
//		DrinkVO drinkVO2 = new DrinkVO();
////		
//		drinkVO2.setDrink_id(3);
//		drinkVO2.setDrink_name("泡沫紅茶");
//		drinkVO2.setDrink_price(35);
//		drinkVO2.setDrink_des("泡沫紅茶介紹...");
//		drinkVO2.setDrink_pic(null); // 飲料圖片
//		drinkVO2.setDrink_tag("紅茶");
//		drinkVO2.setDrink_status(1); // 上架
////		drinkVO2.setDrink_updateDate(java.sql.Date.valueOf("2024-07-07"));
////		drinkVO2.setEdited_by(2); // 修改應該也會用到session去抓取當前使用者ID
//		dao.update(drinkVO2);

		// 刪除
//		dao.delete(7014);

		// 查詢
//		DrinkVO drinkVO3 = dao.findByPrimaryKey(3);
//		System.out.print(drinkVO3.getDrink_id() + ",");
//		System.out.print(drinkVO3.getDrink_name() + ",");
//		System.out.print(drinkVO3.getDrink_price() + ",");
//		System.out.print(drinkVO3.getDrink_des() + ",");
//		System.out.print(drinkVO3.getDrink_pic() + ",");
//		System.out.print(drinkVO3.getDrink_tag() + ",");
//		System.out.print(drinkVO3.getDrink_status() + ",");
//		System.out.print(drinkVO3.getEdited_by() + ",");
//		System.out.println(drinkVO3.getCreated_by());
//		System.out.println("---------------------");

		// 查詢 變數aEmp就不更改了
//		List<DrinkVO> list = dao.getAll();
//		for (DrinkVO aEmp : list) {
//			System.out.print(aEmp.getDrink_id() + ",");
//			System.out.print(aEmp.getDrink_name() + ",");
//			System.out.print(aEmp.getDrink_price() + ",");
//			System.out.print(aEmp.getDrink_des() + ",");
//			System.out.print(aEmp.getDrink_pic() + ",");
//			System.out.print(aEmp.getDrink_tag() + ",");
//			System.out.print(aEmp.getDrink_status() + ",");
//			System.out.print(aEmp.getEdited_by() + ",");
//			System.out.print(aEmp.getCreated_by());
//			System.out.println();
//		}
	}
}