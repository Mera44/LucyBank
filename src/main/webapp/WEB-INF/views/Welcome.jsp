
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<html>

 <tiles:insertDefinition name="baseLayout">
    <tiles:putAttribute name="title"> welcome.title </tiles:putAttribute>
    <tiles:putAttribute name="heading"> welcome.heading </tiles:putAttribute>
    <tiles:putAttribute name="tagline"> welcome.tagline </tiles:putAttribute>
    <tiles:putAttribute name="body">
   
	<h1></h1>
	  	
			
    </tiles:putAttribute>

</tiles:insertDefinition>


<html>
<head>
<title></title>
</head>
<body>
<div>
  <div class="front-moto"><h2>Join our bank where Experience  meets Customer satisfaction</h2> </div>
  <div class="front-box-div">
  	<div class="front-box">Personal Checking accounts</div>
  	<div class="front-box">Personal Saving accounts</div>
  	<div class="front-box">PLucy Bank Credit Cards</div>
  	<div class="front-box">Special Offers</div>
  </div>	
</div>
</body>
</html>

