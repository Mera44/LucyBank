
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
  	
  <h2>Customer Account Detail</h2>            
  <table class="table table-hover">
    <thead>
      <tr>
  
      </tr>
    </thead>
    <tbody>
    	
      <tr>
        <td><c:out value="${customer.profile.firstName}"></c:out><br />
        <c:out value="${customer.profile.lastName}"></c:out><br />
         <c:out value="${customer.profile.email}"></c:out></td>
      </tr>
      <c:forEach var="account" items="${account}" >
      <tr><td>${account.typeAccount}</td></tr>
      <tr>
      		<td>Account Number: ${account.accountNumber}<br />
      		Account Balance:  ${account.balance}</td>
      </tr>
     </c:forEach> 
      <tr>
          <td><a href="deposit/${customer.id}">Deposit</a></td>
           <td><a href="withdraw/${customer.id}">Withdraw</a></td>
            <td><a href="transfer/${customer.id}">Transfer</a></td>
            <td><a href="paybill/${customer.id}"> PayCreditBill </a></td>
      </tr>
    </tbody>
  </table>
</div>
</body>
</html>