
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<html>
 
 <tiles:insertDefinition name="baseLayout">
    <tiles:putAttribute name="title"> customerDetail.title </tiles:putAttribute>
    <tiles:putAttribute name="heading"> customerDetail.heading </tiles:putAttribute>
    <tiles:putAttribute name="tagline"> customerDetail.tagline </tiles:putAttribute>
    <tiles:putAttribute name="body">
   
	<p>List</p>
 
    </tiles:putAttribute>

</tiles:insertDefinition>

<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script type="text/javascript"
	src="<spring:url value="/resource/js/ajax.js"/>"></script>
<html>
<head>
<title></title>
</head>
<body>
	
	<div class="container">
  	
  <h2>Customer Statement</h2>            
  <table class="table table-hover">
    <thead>
      <tr>
  
      <th>Month</th>
      <th>Starting Balance</th>
      <th>Ending Balance</th>
      <th>Total WithDraw</th>
      <th>Total Deposit</th>
    </tr>
      </thead>
    <tr>
   
      <td>${statement.monthName}</td>
      <td>${statement.startingBalance}</td>
      <td>${statement.endingBalance}</td>
      <td>${statement.totalWithdraw}</td>
      <td>${statement.totalDeposit}</td>
    </tr>
      
  </table>
</div>
</body>
</html>