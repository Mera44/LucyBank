
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

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
	
		
				 <%-- <form:errors  path = "*"/>  --%>
		<div class="personal-info">
		<h3>Personal Info:</h3>
		<br /><br />	
			<label for="firstname"> First Name</label>
			<input class="form-control" id="firstname"  placeholder="${teller.profile.firstName }" readonly/>
			<label for="lastname"> Last Name</label>
			<input class="form-control" id="lastname"  placeholder="${teller.profile.lastName }"readonly/>
			<label for="email">Email</label>
			<input class="form-control" id="email"  placeholder="${teller.profile.email }"readonly/>
			
			
		</div>
		<div class="address">
			
			<h3>Address:</h3>
			<br /><br />
			<label for="street">Street</label>
			<input class="form-control" id="street"  placeholder="${teller.profile.address.street }"readonly/>
			<label for="street">State</label>
			<input class="form-control" id="street"  placeholder="${teller.profile.address.state }"readonly/>
			<label for="street">Zipcode</label>
			<input class="form-control" id="street"  placeholder="${teller.profile.address.zipcode}"readonly/>
		<br />
			
			 <input type="hidden" name="${_csrf.parameterName}"   value="${_csrf.token}" /> 
			 <security:csrfInput />
	
		</div>
		<div class="submmit-button">
			<a href="<spring:url value="/teller/edit/${teller.profile.userName}"/>" > Edit </a> 
		</div>
		
</div>
</body>
</html>

