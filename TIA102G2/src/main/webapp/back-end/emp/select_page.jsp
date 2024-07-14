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

<h3>資料查詢:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
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
    <FORM METHOD="post" ACTION="/TIA102G2/drink/drink.do" >  <!-- 這邊一直出錯 -->
        <b>輸入飲品編號 (如1,2):</b>
         <input type="text" name="drink_id">  <!--required = "required" -->
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">                   <h4>(資料格式驗證  by Controller )</h4> 
    </FORM>
  </li>
  
  <li hidden>
    <FORM METHOD="post" ACTION="/TIA102G2/drink/drink.do" name="form1" >
        <b>輸入飲品編號 (如1,2):</b>
        <input type="text" name="drink_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="button" value="送出" onclick="fun1()">  <h4>(資料格式驗證  by Java Script).</h4> 
    </FORM>
  </li>
<%-- useBean跟 EL ${}是絕配  先用用看再說--%>
  <jsp:useBean id="dao" scope="page" class="com.drink.model.DrinkJDBCDAO" />
<%--   <% com.emp.model.EmpDAO daoXX = new com.emp.model.EmpDAO(); %> <%  pageContext.setAttribute("dao", daoXX); %> --%>
   
  <li>
     <FORM METHOD="post" ACTION="/TIA102G2/drink/drink.do" >
       <b>選擇飲品編號:</b>
       <select size="1" name="drink_id">
         <c:forEach var="drinkVO" items="${dao.all}" > 
          <option value="${drinkVO.drink_id}">${drinkVO.drink_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="/TIA102G2/drink/drink.do" >
       <b>選擇飲品名稱:</b>
       <select size="1" name="drink_id">
         <c:forEach var="drinkVO" items="${dao.all}" > 
          <option value="${drinkVO.drink_id}">${drinkVO.drink_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="/TIA102G2/drink/drink.do" >
       <b>選擇飲品價格:</b>
       <select size="1" name="drink_id">
         <c:forEach var="drinkVO" items="${dao.all}" > 
          <option value="${drinkVO.drink_id}">${drinkVO.drink_price}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>

<h3>飲品管理</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/back-end/emp/addDrink.jsp'>Add</a> a new Drink.</li>
</ul>

<script>    
   function fun1(){
      with(document.form1){
         if (drink_id.value=="") 
             alert("請輸入飲品編號!");
         else if (isNaN(drink_id.value)) 
             alert("飲品編號格式不正確!");
         else if ((drink_id.value < 1) || (drink_id.value > 3)) 
             alert("請填寫介於1和3之間的數字!");
         else
             submit();
      }
   }
</script>

</body>
</html>