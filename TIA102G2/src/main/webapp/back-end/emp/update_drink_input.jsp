<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.drink.model.*"%>

<%
  // DrinkVO drinkVO = (DrinkVO) request.getAttribute("drinkVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>飲品資料修改 - update_drink_input.jsp</title>

<style>
  table#table-1 {
    width: 450px;
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
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>飲品資料修改修改 - update_drink_input.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message.value}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="/TIA102G2/drink/drink.do" enctype="multipart/form-data" name="form1">
<table>
    <tr>
        <td>飲品編號:<font color=red><b>*</b></font></td>
        <td>${param.drink_id}</td>
    </tr>
    <tr>
        <td>飲品名稱:</td>
        <td><input type="TEXT" name="drink_name" value="${param.drink_name}" size="45"/></td> 
        <td>${errorMsgs.drink_name}</td>
    </tr>
    <tr>
        <td>飲品價格:</td>
        <td><input type="TEXT" name="drink_price" value="${param.drink_price}" size="45"/></td> 
        <td>${errorMsgs.drink_price}</td>
    </tr>
    <tr>
        <td>飲品介紹:</td>
        <td><input type="TEXT" name="drink_des" value="${param.drink_des}" size="45"/></td> 
        <td>${errorMsgs.drink_des}</td>
    </tr>
    <tr>
        <td>飲品圖片(暫無):</td>
        <td><input type="file" name="drink_pic" size="45"/></td> 
        <td>${errorMsgs.drink_pic}</td>
    </tr>
    <tr>
        <td>飲品標籤:</td>
        <td><input type="TEXT" name="drink_tag" value="${param.drink_tag}" size="45"/></td> 
        <td>${errorMsgs.drink_tag}</td>
    </tr>
    <tr>
        <td>飲品狀態:</td>
        <td><input type="TEXT" name="drink_status" value="${param.drink_status}" size="45"/></td> 
        <td>${errorMsgs.drink_status}</td>
    </tr>
    <tr>
        <td>更新日期:</td>
        <td><input name="drink_updateDate" id="f_date1" type="date" value="${param.drink_updateDate}"/></td> 
        <td>${errorMsgs.drink_updateDate}</td>
    </tr>
	<tr>
         <td>建立者:<font color=green><b>*</b></font></td>
         <td><input type="text" name="created_by" value="${drinkVO.created_by}"/></td>
    </tr>

    
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="drink_id" value="${param.drink_id}">
<input type="submit" value="送出修改">
</FORM>

</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '${param.drink_updateDate}', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.以下為某一天之後的日期無法選擇
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>
</html>