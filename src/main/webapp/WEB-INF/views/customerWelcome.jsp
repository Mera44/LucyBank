
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
 
 <tiles:insertDefinition name="baseLayout">
    <tiles:putAttribute name="title"> customerWelcome.title </tiles:putAttribute>
    <tiles:putAttribute name="heading"> customerWelcome.heading </tiles:putAttribute>
    <tiles:putAttribute name="tagline"> customerWelcome.tagline </tiles:putAttribute>
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
      
        <td>
        		<c:out value="${loggedCustomer.profile.firstName}"></c:out><br />
        		<c:out value="${loggedCustomer.profile.lastName}"></c:out><br />
         		<c:out value="${loggedCustomer.profile.email}"></c:out></td>
        <td>
        		Street: <c:out value="${loggedCustomer.profile.address.street}"></c:out><br />
        		Zip-code: <c:out value="${loggedCustomer.profile.address.zipcode}"></c:out><br />
         		State: <c:out value="${loggedCustomer.profile.address.state}"></c:out>
        </td>
         <%-- <td class="link"><span><a href="#" onclick="deposit('${loggedCustomer.id}');">Edit Profile</a></span> --%>
      </tr>
      <form:form id="${loggedCustomer.id}" modelAttribute="checks" action="depositChecks" method="post" enctype="multipart/form-data">
      <c:forEach var="account" items="${loggedCustomer.accounts}" >
      	<tr>
      		<td>
      				<fieldset>
      				<legend>${account.typeAccount}</legend>
      				
      					 <input type="radio" name="accNum" value="${account.accountNumber}" />${account.accountNumber}<br />
      					Balance: ${account.balance}
      				</fieldset>
      				
      		</td>
     	 </tr>
       </c:forEach>
      <tr>    
          <td class="link"><span><a href="account/transfer/${loggedCustomer.id}">Transfer</a></span>
          <span>
			
					<form:input class="form-control" path="depositAmount" type="number"  />
					<%-- <form:input class="form-control" path="accountNumber" type="hidden" value="${account.accountNumber}"  /> --%>
					<%-- <form:input class="form-control" path="accountType" type="hidden" value="${account.typeAccount}" /> --%>
    				<form:input class="form-control" type="file" path="checkPhoto" id="checkPhoto" />
    				<form:input type="hidden" path="customerId" value="${loggedCustomer.id}" />
    				<input class="btn btn-danger" id="submit" type="submit" tabindex="5" value="Deposit Checks">
    		
		</span></td>
       </tr>
     </form:form>
    </tbody>
  </table>
</div>
</body>
</html>

