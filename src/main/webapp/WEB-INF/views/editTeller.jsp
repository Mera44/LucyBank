
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script type="text/javascript"
	src="<spring:url value="/resource/js/edit.js"/>"></script>
<html>
<head>
<title></title>
</head>
<body>

	<div id="global">
		<h1>Edit Profile</h1>
		<!-- Success - or Validation errors -->
		<div id="result" style="display: none">
			<p id="success"></p>
			<p id="errors"></p>
		</div>


		<div class="content-div" id="formInput">
			<form id="editProfileForm" >
				<div class="personal-info">
					Put New Email:<br /> <label for="email"></label> <input
						class="form-control" id="email" 
						placeholder="Email" />

				</div>
				<div class="address">
					Put New Address: ${name} <label for="street"></label> <input
						class="form-control" id="street" 
						placeholder="Street" /> <label for="street"></label> <input
						class="form-control" id="state" 
						placeholder="State" /> <label for="street"></label> <input
						class="form-control" id="zipcode" 
						placeholder="Zipcode" />

				</div>
				<div class="submmit-button">
					<input type="button" value="Edit Customer"
						onclick="editTeller('${name}');return false;" />
				</div>
			</form>
		</div>

	</div>
</body>
</html>

