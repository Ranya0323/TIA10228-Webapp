<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Drink: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>IBM Drink: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Drink: Home</p>

<h3>��Ƭd��:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='<%=request.getContextPath()%>/back-end/emp/listAllDrink1_byDAO.jsp'>List</a> all Drinks    <h4>(byDAO)         </h4></li>
  <br>
  <li hidden><a href='drink.do?action=getAll'> List</a> all Drinks    <h4>(getFromSession)</h4> <br><br><br></li>
  
  <li>
    <FORM METHOD="post" ACTION="/TIA102G2/drink/drink.do" >  <!-- �o��@���X�� -->
        <b>��J���~�s�� (�p1,2):</b>
         <input type="text" name="drink_id">  <!--required = "required" -->
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">                   <h4>(��Ʈ榡����  by Controller )</h4> 
    </FORM>
  </li>
  
  <li hidden>
    <FORM METHOD="post" ACTION="/TIA102G2/drink/drink.do" name="form1" >
        <b>��J���~�s�� (�p1,2):</b>
        <input type="text" name="drink_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="button" value="�e�X" onclick="fun1()">  <h4>(��Ʈ榡����  by Java Script).</h4> 
    </FORM>
  </li>
<%-- useBean�� EL ${}�O���t  ���ΥάݦA��--%>
  <jsp:useBean id="dao" scope="page" class="com.drink.model.DrinkJDBCDAO" />
<%--   <% com.emp.model.EmpDAO daoXX = new com.emp.model.EmpDAO(); %> <%  pageContext.setAttribute("dao", daoXX); %> --%>
   
  <li>
     <FORM METHOD="post" ACTION="/TIA102G2/drink/drink.do" >
       <b>��ܶ��~�s��:</b>
       <select size="1" name="drink_id">
         <c:forEach var="drinkVO" items="${dao.all}" > 
          <option value="${drinkVO.drink_id}">${drinkVO.drink_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="/TIA102G2/drink/drink.do" >
       <b>��ܶ��~�W��:</b>
       <select size="1" name="drink_id">
         <c:forEach var="drinkVO" items="${dao.all}" > 
          <option value="${drinkVO.drink_id}">${drinkVO.drink_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="/TIA102G2/drink/drink.do" >
       <b>��ܶ��~����:</b>
       <select size="1" name="drink_id">
         <c:forEach var="drinkVO" items="${dao.all}" > 
          <option value="${drinkVO.drink_id}">${drinkVO.drink_price}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  </li>
</ul>

<h3>���~�޲z</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/back-end/emp/addDrink.jsp'>Add</a> a new Drink.</li>
</ul>

<script>    
   function fun1(){
      with(document.form1){
         if (drink_id.value=="") 
             alert("�п�J���~�s��!");
         else if (isNaN(drink_id.value)) 
             alert("���~�s���榡�����T!");
         else if ((drink_id.value < 1) || (drink_id.value > 3)) 
             alert("�ж�g����1�M3�������Ʀr!");
         else
             submit();
      }
   }
</script>

</body>
</html>