
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
   
	<p></p>
 
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
        <td><c:out value="${customerDetail.profile.firstName}"></c:out><br />
        <c:out value="${customerDetail.profile.lastName}"></c:out><br />
         <c:out value="${customerDetail.profile.email}"></c:out></td>
      </tr>
      <c:forEach var="account" items="${accounts}" >
      <tr><td><h3>${account.typeAccount}</h3></td></tr>
      <tr>
      		<td>
	      		<div>
	      			Account Number: ${account.accountNumber}
	      		</div>
	      		<div id="${account.typeAccount}">
	      			Account Balance:  ${account.balance}
	      		</div>
      		</td>
      </tr>
      <tr>
      	<td><input class="form-control" type="number" id="${account.accountNumber}" /></td>
      </tr>
      <tr>
          <td class="link"><span><a href="#" onclick="deposit('${account.accountNumber}','${account.typeAccount}');">Deposit</a></span>
          <td class="link"><span><a href="#" onclick="withdraw('${account.accountNumber}','${account.typeAccount}');">Withdraw</a></span>
 
      </tr>
     </c:forEach> 
    </tbody>
  </table>
</div>
</body>
</html>

