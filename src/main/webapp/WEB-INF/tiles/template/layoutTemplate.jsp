<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

		
<title>header</title>


<link href="http://getbootstrap.com/dist/css/bootstrap.css"	rel="stylesheet">

<link href="http://getbootstrap.com/examples/jumbotron/jumbotron.css"	rel="stylesheet">
<style type="text/css">
	.header-div{
	    width: 100%;
	    height:100px;
	    background-color: rgb(27, 72, 145);
	    top: 0;
	    left: 0;
	    position: fixed;
	    color:white;
}
.bodyAndFooter{
	margin-top:100px;
}
</style>
</head>

<body>
		<div class="header-div">
			<ul class="nav nav-pills pull-right">
				<tiles:insertAttribute name="navigation" />
			</ul>
			<h3 class="text-muted">Web Store</h3>
		</div>
		
	<div class="bodyAndFooter">
		

		

		<div class="row">
			<tiles:insertAttribute name="body" />
		</div>

		<div class="footer">
			<tiles:insertAttribute name="footer" />
		</div>

	</div>
</body>
</html>
