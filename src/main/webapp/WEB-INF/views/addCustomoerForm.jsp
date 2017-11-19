
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
	
		<form:form class="form-group" modelAttribute="customer" action="add" method="post">
		
		<div class="personal-info">
		Personal:<br />
			<label for="firstname"></label>
			<form:input class="form-control" id="firstname" path="profile.firstName" placeholder="First name"/>
			<label for="lastname"></label>
			<form:input class="form-control" id="lastname" path="profile.lastName" placeholder="Lastname"/>
			<label for="email"></label>
			<form:input class="form-control" id="email" path="profile.email" placeholder="Email"/>
			<label for="birthdate"></label>
			<%-- <form:input type="text" class="form-control" id="birthdate" path="profile.birthdate" placeholder="Birth day"/>
			<label for="username"></label> --%>
			<form:input class="form-control" id="username" path="profile.userName" placeholder="Username"/>
			<label for="password"></label>
			<form:input class="form-control" id="password" path="profile.password" placeholder="password"/>
			
		</div>
		<div class="address">
			Address:
			
			<label for="street"></label>
			<form:input class="form-control" id="street" path="profile.address.street" placeholder="Street"/>
			<label for="street"></label>
			<form:input class="form-control" id="street" path="profile.address.state" placeholder="State"/>
			<label for="street"></label>
			<form:input class="form-control" id="street" path="profile.address.zipcode" placeholder="Zipcode"/>
			
		<br />
			Accounts:<br />	
			 <input type="radio" name="checking" value="checking"> Checking<br>
  			<input type="radio" name="saving" value="saving"> Saving<br>
  			<input type="radio" name="credit" value="credit"> Credit
			
			<form:input type="hidden" path="profile.role" value="customer" />
	
		</div>
		<div class="submmit-button">
			<input class="btn btn-primary" type="submit" value="Add Customer" />
		</div>
		</form:form>
</div>
</body>
</html>

