<%@ page contentType="text/html;charset=UTF-8"%>
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


<link href="http://getbootstrap.com/dist/css/bootstrap.css"
	rel="stylesheet">

<link href="http://getbootstrap.com/examples/jumbotron/jumbotron.css"
	rel="stylesheet">
<!-- <link href="/resources/css/style.css" rel="stylesheet" /> -->
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<style type="text/css">
body {
	position: relative;
	margin: 0;
	padding-bottom: 6rem;
	min-height: 100%;
	font-family: "Helvetica Neue", Arial, sans-serif;
}

.header-div {
	width: 100%;
	height: 100px;
	background-color: rgb(27, 72, 145);
	top: 0;
	left: 0;
	position: fixed;
	color: white;
}

.bodyAndFooter {
	margin-top: 100px;
}

.footer {
	position: absolute;
	right: 0;
	bottom: 0;
	left: 0;
	padding: 1rem;
	background-color: #efefef;
	text-align: center;
}

.footer .nav {
	width: 75%;
	margin-left: auto;
	margin-top: 0;
	font-size: 1em;
}

.nav {
	width: 70%;
	margin-left: 300px;
	margin-top: 40px;
	font-size: 1.1em;
}

.logo-img {
	width: 250px;
}

.logo {
	margin-top: 20px;
	width: 10%;
	float: left;
}

.nav span {
	margin-right: 10px;
}
.image-div{
	width:200px;
	
}
.nav sapan a:link {
	color: white;
}

.language {
	float: right;
	margin-right: 20px;
}

.header-right {
	color: white;
	margin-left: 600px;
	margin-top: 40px;
	width: 500px;
}

.img-circle {
	border-radius: 50%;
	width: 10%;
}

.content-div {
	padding: 20px;
	maring-top: 300px;
	width: 70%;
	margin-left: 200px;
}

.content-div .personal-info {
	width: 300px;
	padding: 20px;
	border-right: 1px solid black;
	float: left;
}

.content-div .address {
	width: 300px;
	padding: 20px;
	border-right: 1px solid black;
	float: left;
}

.content-div .submmit-button {
	width: 300px;
	padding: 20px;
	clear: both;
}

td.link span {
	margin-right: 100px;;
}

form:errors {
	color: red;
}
.front-moto{
	width:70%;
	margin-left:400px;
	margin-top:100px;
}
.front-box-div{
	width:90%;
	margin-left:200px;
	margin-top:20px;
}
.front-box{
	width:300px;
	height:200px;
	border-radius:10px;
	padding:30px;
	font-size:2em;
	color:white;
	background-color: rgb(27, 72, 145);
	float:left;
	box-shadow: 10px 10px 5px #888888;
	margin-right:20px;
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
