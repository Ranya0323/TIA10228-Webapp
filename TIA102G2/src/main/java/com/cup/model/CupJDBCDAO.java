package com.cup.model;

import java.util.*;
import java.sql.*;

public class CupJDBCDAO implements CupDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/db01?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "123456";

	private static final String CREATE_CUP =
			"INSERT INTO cup (store_id,cup_status,member_id) VALUES (?, ?, ?))";
	private static final String INSERT_STMT = 
		"INSERT INTO cup (user_id,store_id,cup_status,member_id) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT empno,ename,job,hiredate,sal,comm,deptno FROM emp2 order by empno";
	private static final String GET_ONE_STMT = 
		"SELECT empno,ename,job,hiredate,sal,comm,deptno FROM emp2 where empno = ?";
	private static final String DELETE = 
		"DELETE FROM cup where empno = ?";
	private static final String UPDATE = 
		"UPDATE cup set ename=?, job=?, hiredate=?, sal=?, comm=?, deptno=? where empno = ?";

	// 新增杯子
	@Override
	public void create(CupVO cupVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(CREATE_CUP);

//			 新增杯子應該不用User_id
			// 新增時間因為table有current time屬性 我看GPT說不用加
			pstmt.setInt(1, cupVO.getStore_id());
			pstmt.setInt(2, cupVO.getCup_status());
			pstmt.setInt(3, cupVO.getMember_id());
			
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
	
	// 租借杯子
	@Override
	public void insert(CupVO cupVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

//			pstmt.setInt(1, cupVO.getCup_id());
//			 
			pstmt.setInt(1, cupVO.getUser_id());
			pstmt.setInt(2, cupVO.getStore_id());
			pstmt.setInt(3, cupVO.getCup_status());
			pstmt.setInt(4, cupVO.getMember_id());
			
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
	public void update(CupVO cupVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, cupVO.getCup_id());
			pstmt.setInt(2, cupVO.getUser_id());
			pstmt.setInt(3, cupVO.getStore_id());
			pstmt.setInt(4, cupVO.getCup_status());
			pstmt.setDate(5, cupVO.getCup_rentDate());
			pstmt.setInt(7, cupVO.getMember_id());

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
	public void delete(Integer empno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, empno);

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
	public CupVO findByPrimaryKey(Integer cup_id) {

		CupVO cupVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, cup_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				cupVO = new CupVO();
				cupVO.setCup_id(rs.getInt("cup_id"));
				cupVO.setUser_id(rs.getInt("user_id"));
				cupVO.setStore_id(rs.getInt("store_id"));
				cupVO.setCup_status(rs.getInt("cup_status"));
				cupVO.setCup_rentDate(rs.getDate("cup_rentDate"));
				cupVO.setCup_ctime(rs.getDate("cup_ctime"));
				cupVO.setMember_id(rs.getInt("member_id"));
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
		return cupVO;
	}

	@Override
	public List<CupVO> getAll() {
		List<CupVO> list = new ArrayList<CupVO>();
		CupVO cupVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				cupVO = new CupVO();
				cupVO.setCup_id(rs.getInt("cup_id"));
				cupVO.setUser_id(rs.getInt("user_id"));
				cupVO.setStore_id(rs.getInt("store_id"));
				cupVO.setCup_status(rs.getInt("cup_status"));
				cupVO.setCup_rentDate(rs.getDate("cup_rentDate"));
				cupVO.setCup_ctime(rs.getDate("cup_ctime"));
				cupVO.setMember_id(rs.getInt("member_id"));
				list.add(cupVO); // Store the row in the list
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

		CupJDBCDAO dao = new CupJDBCDAO();

		// 新增
//		EmpVO empVO1 = new EmpVO();
//		empVO1.setEname("吳永志1");
//		empVO1.setJob("MANAGER");
//		empVO1.setHiredate(java.sql.Date.valueOf("2005-01-01"));
//		empVO1.setSal(new Double(50000)); // 或寫50000.0 或 Double.valueOf(50000)
//		empVO1.setComm(new Double(500));
//		empVO1.setDeptno(10);
//		dao.insert(empVO1);

		// 修改
//		EmpVO empVO2 = new EmpVO();
//		empVO2.setEmpno(7001);
//		empVO2.setEname("吳永志2");
//		empVO2.setJob("MANAGER2");
//		empVO2.setHiredate(java.sql.Date.valueOf("2002-01-01"));
//		empVO2.setSal(new Double(20000));
//		empVO2.setComm(new Double(200));
//		empVO2.setDeptno(20);
//		dao.update(empVO2);

		// 刪除
//		dao.delete(7014);

		// 查詢
//		EmpVO empVO3 = dao.findByPrimaryKey(7001);
//		System.out.print(empVO3.getEmpno() + ",");
//		System.out.print(empVO3.getEname() + ",");
//		System.out.print(empVO3.getJob() + ",");
//		System.out.print(empVO3.getHiredate() + ",");
//		System.out.print(empVO3.getSal() + ",");
//		System.out.print(empVO3.getComm() + ",");
//		System.out.println(empVO3.getDeptno());
//		System.out.println("---------------------");

		// 查詢
		List<CupVO> list = dao.getAll();
		for (CupVO aEmp : list) {
			System.out.print(aEmp.getCup_id() + ",");
			System.out.print(aEmp.getUser_id() + ",");
			System.out.print(aEmp.getStore_id() + ",");
			System.out.print(aEmp.getCup_status() + ",");
			System.out.print(aEmp.getCup_rentDate() + ",");
			System.out.print(aEmp.getCup_ctime() + ",");
			System.out.print(aEmp.getMember_id());
			System.out.println();
		}
	}
}