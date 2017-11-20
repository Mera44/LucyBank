 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Login</title>
</head>
<body onload='document.loginForm.j_username.focus();'>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1><spring:message code="profile.welcome"/></h1>
			</div>
		
		
		
			<div class="pull-right" style="padding-right: 50px">
				<a href="?language=en">English</a>|<a href="?language=fr">French</a>
			</div>

		</div>
	
	</section>
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title"><spring:message code="profile.please"/></h3>
					</div>
					<div class="panel-body">
						<c:if test="${not empty error}">
							<div class="alert alert-danger">
								<spring:message
									code="AbstractUserDetailsAuthenticationProvider.badCredentials" />
								<br />
							</div>
						</c:if>
						
						<c:url var="loginUrl" value="/login" />
						
						<%
		String errorString = (String) request.getAttribute("error");
		if (errorString != null && errorString.trim().equals("true")) {
			out.println("<span class=\"errorblock\">Incorrect login name or password. Please try again");
		}
	%>
						
                      <%--   <form action="${loginUrl}" method="post" class="form-horizontal"> --%>
                        
                        <form name='loginForm' action="<c:url value='login' />" method='POST'>
                            <c:if test="${param.error != null}">
                                <div class="alert alert-danger">
                                    <p>Invalid username and password......</p>
                                </div>
                            </c:if>
                            <c:if test="${param.logout != null}">
                                <div class="alert alert-success">
                                    <p>You have been logged out successfully.</p>
                                </div>
                            </c:if>
                           
                            <div class="input-group input-sm">
                                <label class="" for="username"><spring:message code="profile.form.username.label"/> </label>
                                <input type="text" class="form-control" id="username" name="username" placeholder="Enter Username" required>
                            </div>
                            <div class="input-group input-sm">
                                <label class="" for="password"><spring:message code="profile.form.password.label"/></label> 
                                <input type="password" class="form-control" id="password" name="password" placeholder="Enter Password" required>
                            </div>
                            <input type="hidden" name="${_csrf.parameterName}"   value="${_csrf.token}" />
                                 
                            <div class="form-actions">
                                <input type="submit"
                                    class="btn btn-block btn-primary btn-default" value="Log in">
                            </div>
                        </form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body> 

<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
<title>Login Page</title>

<style>
.errorblock {
	color: #ff0000;
	background-color: #ffEEEE;
}
</style>
</head>
<body onload='document.loginForm.j_username.focus();'>
	<h3>Login Page</h3>

	<%
		String errorString = (String) request.getAttribute("error");
		if (errorString != null && errorString.trim().equals("true")) {
			out.println("<span class=\"errorblock\">Incorrect login name or password. Please try again");
		}
	%>

	<form name='loginForm' action="<c:url value='login' />" method='POST'>

		<table>
			<tr>
				<td>User:</td>
				<td><input type='text' name='username' value=''></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='password' /></td>
			</tr>
			<tr>
				<td><input name="submit" type="submit" value="submit" /></td>
				<td><input name="reset" type="reset" /></td>
			</tr>
		</table>

	</form>
</body>
</html>  --%>