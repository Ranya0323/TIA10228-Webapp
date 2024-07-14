<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.drink.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	DrinkJDBCDAO dao = new DrinkJDBCDAO();
    List<DrinkVO> list = dao.getAll();       // 此行的list變數(物件)將提供page1.file的第11行取得查詢到的總筆數，再由page1.file進行分頁的需要
    pageContext.setAttribute("list",list); // 將上一行的list變數(物件)存入當前頁面pageContext，再由底下的第73行由JSTL的forEach列印出結果
%>


<html>
<head>
<title>所有飲料資料 - listAllDrink1_byDAO.jsp</title>

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
		 <h3>所有飲料資料 - listAllDrink1_byDAO.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
<%-- 		 <h4><img src="<%=request.getContextPath()%>/back-end/emp/images/back1.gif" width="100" height="32" border="0"></h4> --%>
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
<%-- 			<td>${drinkVO.drink_pic}</td> --%>
			<td>
            <img src="<%=request.getContextPath()%>/DrinkDBGifReader4?drink_id=${drinkVO.drink_id}" width="100" height="100"/>
       		</td>
			<td>${drinkVO.drink_tag}</td>
			<td>${drinkVO.drink_status}</td>
			<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/emp/update_drink_input.jsp" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="drink_id"  value="${drinkVO.drink_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/drink/drink.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="drink_id"  value="${drinkVO.drink_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>