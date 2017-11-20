
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
	<h3>
	You might be interested in:
	<ul><li>
		- Personal checking accounts</li>
		<li>	- Personal Saving accounts</li>
  	<li>	- Lucy Bank Credit Cards</li>
	
 	<li>	- Special Offers</li>
 		</ul>
  	</h3>
  
  

</body>
</html>

