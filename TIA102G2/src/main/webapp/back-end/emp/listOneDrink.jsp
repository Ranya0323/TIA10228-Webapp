<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.drink.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
  DrinkVO drinkVO = (DrinkVO) request.getAttribute("drinkVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>

<html>
<head>
<title>���~��� - listOneEmp.jsp</title>

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
	width: 600px;
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

<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>���~��� - listOneEmp.jsp</h3>
		 <h4><a href="/TIA102G2/back-end/emp/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
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
	<tr>
		<td><%=drinkVO.getDrink_id()%></td>
		<td><%=drinkVO.getDrink_name()%></td>
		<td><%=drinkVO.getDrink_price()%></td>
		<td><%=drinkVO.getDrink_des()%></td>
		<td>
		<img src="<%=request.getContextPath()%>/DrinkDBGifReader4?drink_id=${drinkVO.drink_id}" width="100" height="100"/>
		</td>
		<td><%=drinkVO.getDrink_tag()%></td>
		<td><%=drinkVO.getDrink_status()%></td>
	</tr>
</table>

</body>
</html>