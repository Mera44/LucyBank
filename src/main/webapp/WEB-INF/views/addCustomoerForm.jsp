
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
		    <div class="input-group">
		        <input class="form-control" type="text" name="username" placeholder="Username">&nbsp;&nbsp;&nbsp;&nbsp;
		        <input class="form-control" type="text" name="password" placeholder="Password">
		        <span class="input-group-btn">
		            <button  class="btn btn-primary" type="button">Sign in</button>
		          </span>
		    </div>
		</div>    
	 
 
    </tiles:putAttribute>

</tiles:insertDefinition>
	  
<html>
<head>
<title></title>
</head>
<body>
		
<div class="content-div">
		<form:form class="form-group" modelAttribute="customer" action="banker/customer/add" method="post">
			<label for="firstname"></label>
			<form:input class="form-control" id="firstname" path="firstName" placeholder="First name"/>
			<label for="lastname"></label>
			<form:input class="form-control" id="lastname" path="lastName" placeholder="Lastname"/>
			<label for="firstname"></label>
			<form:input class="form-control" id="firstname" path="firstName" placeholder="First name"/>
			<label for="username"></label>
			<form:input class="form-control" id="username" path="userName" placeholder="Username"/>
		
		</form:form>
</div>
</body>
</html>

