<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<html>
 
 <tiles:insertDefinition name="baseLayout">
    <tiles:putAttribute name="title"> customer.title </tiles:putAttribute>
    <tiles:putAttribute name="heading"> customer.heading </tiles:putAttribute>
    <tiles:putAttribute name="tagline"> customer.tagline </tiles:putAttribute>
    <tiles:putAttribute name="body">
   
	   
		 
		
		 
	<div>Hello this is from tiles body</div>
	 
 
    </tiles:putAttribute>

</tiles:insertDefinition>
	  

</html>
=======
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Welcome</title>
</head>
<body>
	<div>This is from profile page body</div>
	
</body>
</html>

