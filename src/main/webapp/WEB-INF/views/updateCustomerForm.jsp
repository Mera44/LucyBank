
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>

<html>
 
 <tiles:insertDefinition name="baseLayout">
    <tiles:putAttribute name="title"> welcome.title </tiles:putAttribute>
    <tiles:putAttribute name="heading"> welcome.heading </tiles:putAttribute>
    <tiles:putAttribute name="tagline"> welcome.tagline </tiles:putAttribute>
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
	<spring:message code="profile.firstname" var="first"/>
	<spring:message code="profile.lastname" var="last"/>
	<spring:message code="profile.email"  var="email"/>
	<spring:message code="profile.username" var="uName"/>
	<spring:message code="profile.password" var="pass"/>
	<spring:message code="profile.confirm" var="confirm"/>
	<spring:message code="address.street" var="street"/>
	<spring:message code="address.state"  var="state"/>
	<spring:message code="address.zipcode" var="zip"/>
		<form:form class="form-group" modelAttribute="customer" action="/LucyBank/banker/customer/update" method="post" >
				 <%-- <form:errors  path = "*"/>  --%>
		<div class="personal-info">
		<spring:message code="customer.profile" /><br />
			
			<label for="email"></label>
			<input type="hidden" name="id" value="${editCustomer.id}"  />
			<form:input class="form-control" id="email" path="profile.email" value="${editCustomer.profile.email}"/>
			<form:errors style="color:red;" path="profile.email" />
			<label for="birthdate"></label>
			<form:input class="form-control" id="username" path="profile.userName" value="${editCustomer.profile.userName}"/>
			<form:errors style="color:red;" path="profile.userName" />
			<label for="password"></label>
			<form:input type="password" class="form-control" id="password" path="profile.password" placeholder="password"/>
			<form:errors style="color:red;" path="profile.password" />
				<label for="password"></label>
			<form:input type="password" class="form-control" id="confirmpassword" path="profile.confirmpassword" placeholder="confirmpassword"/>
			<form:errors style="color:red;" path="profile" />
			<label for="image"><spring:message code="profile.image" /></label>
			<form:input path="profile.image" id="image" type="file"/>
			
		</div>
		<div class="address">
			<spring:message code="profile.address" />
			
			<label for="street"></label>
			<form:input class="form-control" id="street" path="profile.address.street" value="${editCustomer.profile.address.street}"/>
			<form:errors style="color:red;" path="profile.address.street" />
			<label for="street"></label>
			<form:input class="form-control" id="street" path="profile.address.state" value="${editCustomer.profile.address.state}"/>
			<form:errors style="color:red;" path="profile.address.state" />
			<label for="street"></label>
			<form:input class="form-control" id="street" path="profile.address.zipcode" value="${editCustomer.profile.address.zipcode}"/>
			<form:errors style="color:red;" path="profile.address.zipcode" />
			
			
	
		</div>
		<div class="submmit-button">
			<input class="btn btn-primary" type="submit" value="Update Customer" />
		</div>
		</form:form>
</div>
</body>
</html>

