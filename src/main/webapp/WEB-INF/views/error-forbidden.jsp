<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
 "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>403 error</title>
</head>
<body>

	<center>

		<img
			src="<spring:url value="/resource/images/403-forbidden-error.jpg"  htmlEscape="true" />"
			alt="Page not found  JSP!!!" /> 
			
			Dear <strong>${user}</strong>, You are not authorized to access this page 
			<a href="<c:url value="/logout" />">Logout</a>

		<p>
			<button type=button onclick=window.location.href="<spring:url value="/welcome" />">Home</button>
		</p>

	</center>
</body>
</html>