
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>

<tiles:insertDefinition name="baseLayout">
	<tiles:putAttribute name="title"> customerDetail.title </tiles:putAttribute>
	<tiles:putAttribute name="heading"> customerDetail.heading </tiles:putAttribute>
	<tiles:putAttribute name="tagline"> customerDetail.tagline </tiles:putAttribute>
	<tiles:putAttribute name="body">

		<p></p>

	</tiles:putAttribute>

</tiles:insertDefinition>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>TRANSACTION EXCEPTION</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h3 class="alert alert-danger"> ${withdrawId}</h3>
			</div>
			
		</div>
	</section>

	
	
</body>
</html>
