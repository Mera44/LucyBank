
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<html>
 
 <tiles:insertDefinition name="baseLayout">
    <tiles:putAttribute name="title"> withdraw.title </tiles:putAttribute>
    <tiles:putAttribute name="heading"> withdraw.heading </tiles:putAttribute>
    <tiles:putAttribute name="tagline"> withdraw.tagline </tiles:putAttribute>
    <tiles:putAttribute name="body">


 <p>list customers</p> 
 
    </tiles:putAttribute>

</tiles:insertDefinition>
	  
<html>
<head>
<title></title>
</head>
<body>
<c:forEach var= "customer"  items="${customers}"> 
    <span>${customer.profile.firstName}</span>
    <span>${customer.profile.lastName}</span> &nbsp; &nbsp;
    <span> <a href="account/	${customer.id }">See Account</a></span></br>
</c:forEach>

</body>
</html>

