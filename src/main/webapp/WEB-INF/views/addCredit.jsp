
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>

<html>
 
 <tiles:insertDefinition name="baseLayout">
    <tiles:putAttribute name="title"> addCredit.title </tiles:putAttribute>
    <tiles:putAttribute name="heading"> addCredit.heading </tiles:putAttribute>
    <tiles:putAttribute name="tagline"> addCredit.tagline </tiles:putAttribute>
    <tiles:putAttribute name="body">
   
	   
		 
		<div class="header-right">
		   <h2>Add Customer</h2>
		</div>    
	 
 
    </tiles:putAttribute>

</tiles:insertDefinition>
	  
<html>
<head>
<title></title>
</head>
<body>
		
<div class="content-div">
	
		<form:form class="form-group" modelAttribute="credit"  method="post" >
				 <%-- <form:errors  path = "*"/>  --%>
		<div class="personal-info">		
			<label for="accountNumber"></label>
			<form:input class="form-control" id="accountNumber" path="accountNumber" placeholder="${accountNumber}" value="${accountNumber}"/>
			<form:errors style="color:red;" path="accountNumber" />
			<label for="cardNumber"></label>
			<form:input class="form-control" id="cardNumber" path="cardNumber" placeholder="${cardNumber}" value="${cardNumber}"/>
			<form:errors style="color:red;" path="cardNumber" />
			<label for="typeAccount"></label>
			<form:input class="form-control" id="typeAccount" path="typeAccount" Value="Credit" readonly="reaonly"/>
			<form:errors style="color:red;" path="typeAccount" />
			<label for="creditLimit"></label>
			<form:input class="form-control" id="creditLimit" path="creditLimit" />
			<form:errors style="color:red;" path="creditLimit" />
				
		</div>
		<div class="submmit-button">
			<input class="btn btn-primary" type="submit" value="Add Credit Account" />
		</div>
		</form:form>
</div>
</body>
</html>

