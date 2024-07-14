<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.drink.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
   // 取得EmpServlet.java(Concroller)，第31行存入session的list物件
   // List<EmpVO> list = (List<EmpVO>)session.getAttribute("list"); // list變數(物件)將提供page1.file的第11行取得查詢到的總筆數，再由page1.file進行分頁的需要
%>
   <%-- 以下等同第9行--%>
   <jsp:useBean id="list" scope="session" type="java.util.List<DrinkVO>" />

<html>
<head>
<title>所有飲品資料 - listAllDrink2_getFromSession.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有飲品資料 - listAllDrink2_getFromSession.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>飲品編號</th>
		<th>飲品名稱</th>
		<th>飲品價格</th>
		<th>飲品介紹</th>
		<th>飲品圖片(暫無)</th>
		<th>飲品標籤</th>
		<th>飲品狀態</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="drinkVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr>
			<td>${drinkVO.drink_id}</td>
			<td>${drinkVO.drink_name}</td>
			<td>${drinkVO.drink_price}</td>
			<td>${drinkVO.drink_des}</td>
			<td>${drinkVO.drink_pic}</td>
			<td>${drinkVO.drink_tag}</td>
			<td>${drinkVO.drink_status}</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>