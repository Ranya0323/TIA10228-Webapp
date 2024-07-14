<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.drink.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
	DrinkJDBCDAO dao = new DrinkJDBCDAO();
    List<DrinkVO> list = dao.getAll();       // ���檺list�ܼ�(����)�N����page1.file����11����o�d�ߨ쪺�`���ơA�A��page1.file�i��������ݭn
    pageContext.setAttribute("list",list); // �N�W�@�檺list�ܼ�(����)�s�J��e����pageContext�A�A�ѩ��U����73���JSTL��forEach�C�L�X���G
%>


<html>
<head>
<title>�Ҧ����Ƹ�� - listAllDrink1_byDAO.jsp</title>

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

<h4>�����m�߱ĥ� EL ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>�Ҧ����Ƹ�� - listAllDrink1_byDAO.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
<%-- 		 <h4><img src="<%=request.getContextPath()%>/back-end/emp/images/back1.gif" width="100" height="32" border="0"></h4> --%>
	</td></tr>
</table>

<table>
	<tr>
		<th>���~�s��</th>
		<th>���~�W��</th>
		<th>���~����</th>
		<th>���~����</th>
		<th>���~�Ϥ�(�ȵL)</th>
		<th>���~����</th>
		<th>���~���A</th>
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
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="drink_id"  value="${drinkVO.drink_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/drink/drink.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="drink_id"  value="${drinkVO.drink_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>