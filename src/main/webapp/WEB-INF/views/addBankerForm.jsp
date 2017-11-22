
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>

<html>
 
 <tiles:insertDefinition name="baseLayout">
    <tiles:putAttribute name="title"> addBanker.title </tiles:putAttribute>
    <tiles:putAttribute name="heading"> addBanker.heading </tiles:putAttribute>
    <tiles:putAttribute name="tagline"> addBanker.tagline </tiles:putAttribute>
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
	<spring:message code="profile.banker" var="banker"/>
	<spring:message code="address.street" var="street"/>
	<spring:message code="address.state"  var="state"/>
	<spring:message code="address.zipcode" var="zip"/>
		<form:form class="form-group" modelAttribute="banker" action="" method="post">
		
		<div class="personal-info">
		<spring:message code="customer.profile" /><br />
			<label for="firstname"></label>
			<form:input class="form-control" id="firstname" path="profile.firstName" placeholder="${first}"/>
			<form:errors style="color:red;" path="profile.firstName" />
			<label for="lastname"></label>
			<form:input class="form-control" id="lastname" path="profile.lastName" placeholder="${last}"/>
			<form:errors style="color:red;" path="profile.lastName" />
			<label for="email"></label>
			<form:input class="form-control" id="email" path="profile.email" placeholder="${email}"/>
			<form:errors style="color:red;" path="profile.email" />
			<label for="birthdate"></label>
			<form:input class="form-control" id="username" path="profile.userName" placeholder="${uName}"/>
			<form:errors style="color:red;" path="profile.userName" />
			<label for="password"></label>
			<form:input type="password" class="form-control" id="password" path="profile.password" placeholder="${pass}"/>
			<form:errors style="color:red;" path="profile.password" />
			<label for="password"></label>
			<form:input type="password" class="form-control" id="confirmpassword" path="profile.confirmpassword" placeholder="${confirm}"/>
			<form:errors style="color:red;" path="profile.confirmpassword" /><label for="password"></label>
			<form:input type="hidden"  path="profile.role.role" value="ROLE_BANKER"/>
			
		</div>
		<div class="address">
			<spring:message code="profile.address" />
			
			<label for="street"></label>
			<form:input class="form-control" id="street" path="profile.address.street" placeholder="${street}"/>
			<form:errors style="color:red;" path="profile.address.street" />
			<label for="street"></label>
			<form:input class="form-control" id="street" path="profile.address.state" placeholder="${state}"/>
			<form:errors style="color:red;" path="profile.address.state" />
			<label for="street"></label>
			<form:input class="form-control" id="street" path="profile.address.zipcode" placeholder="${zip}"/>
			<form:errors style="color:red;" path="profile.address.zipcode" />
			
		</div>
		<div class="submmit-button">
			<input class="btn btn-primary" type="submit" value="${banker}" />
		</div>
		</form:form>
</div>
</body>
</html>

