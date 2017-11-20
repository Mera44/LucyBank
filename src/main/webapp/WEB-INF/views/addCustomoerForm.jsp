
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
			<form:errors style="color:red;" path="profile.firstName" />
			<label for="lastname"></label>
			<form:input class="form-control" id="lastname" path="profile.lastName" placeholder="Lastname"/>
			<form:errors style="color:red;" path="profile.lastName" />
			<label for="email"></label>
			<form:input class="form-control" id="email" path="profile.email" placeholder="Email"/>
			<form:errors style="color:red;" path="profile.email" />
			<label for="birthdate"></label>
			<form:input class="form-control" id="username" path="profile.userName" placeholder="Username"/>
			<form:errors style="color:red;" path="profile.userName" />
			<label for="password"></label>
			<form:input class="form-control" id="password" path="profile.password" placeholder="password"/>
			<form:errors style="color:red;" path="profile.password" />
			
		</div>
		<div class="address">
			Address:
			
			<label for="street"></label>
			<form:input class="form-control" id="street" path="profile.address.street" placeholder="Street"/>
			<form:errors style="color:red;" path="profile.address.street" />
			<label for="street"></label>
			<form:input class="form-control" id="street" path="profile.address.state" placeholder="State"/>
			<form:errors style="color:red;" path="profile.address.state" />
			<label for="street"></label>
			<form:input class="form-control" id="street" path="profile.address.zipcode" placeholder="Zipcode"/>
			<form:errors style="color:red;" path="profile.address.zipcode" />
		<br />
			Accounts:<br />	
			<fieldset>
			 <input type="checkbox" name="accTypes" value="checking" required> Checking<br>
  			<input type="checkbox" name="accTypes" value="saving" required> Saving<br>
  			<input type="checkbox" name="accTypes" value="credit"> Credit
  			</fieldset>
			
			
	
		</div>
		<div class="submmit-button">
			<input class="btn btn-primary" type="submit" value="Add Customer" />
		</div>
		</form:form>
</div>
</body>
</html>

