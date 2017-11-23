
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

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

<html>
<head>
<title></title>
</head>
<body>

	<div id="global">
		<h1>Edit Customer Profile</h1>
		<!-- Success - or Validation errors -->
		<div id="result" style="display: none">
			<p id="success"></p>
			<p id="errors"></p>
		</div>


		<div class="content-div" id="formInput">
			<form action="" method="post">
			
				<div class="address" >
					Put New Address: <label for="street">
					
					</label> <input
						class="form-control" type="hidden" id="id" name="id" value="${editCustomer.id}" 
						placeholder="Street" />
					
					</label> <input
						class="form-control" id="street" name="street" value="${editCustomer.profile.address.street}" 
						placeholder="Street" /> <label for="street"></label> <input
						class="form-control" id="state" name="state" value="${editCustomer.profile.address.state}"
						placeholder="State" /> <label for="street"></label> <input
						class="form-control" id="zipcode" name="zipcode" value="${editCustomer.profile.address.zipcode}"
						placeholder="Zipcode" />
						

				</div>
				<div class="submmit-button">
					<input class="btn btn-primary" type="submit" value="Update" />
				</div>
				 <input type="hidden" name="${_csrf.parameterName}"   value="${_csrf.token}" />
                            <security:csrfInput /> 
			</form>
		</div>

	</div>
</body>
</html>

