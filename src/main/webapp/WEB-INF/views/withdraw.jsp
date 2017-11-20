
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

		<p>List</p>

	</tiles:putAttribute>

</tiles:insertDefinition>

<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script type="text/javascript"
	src="<spring:url value="/resource/js/ajax.js"/>"></script>
<html>
<head>
<title></title>
</head>
<body>

	<div class="container">

		<h2>Choose Account To WithDraw</h2>
		<form:form action="${customer.id}" modelAttribute="transaction"
			method="post">
			<table class="table table-hover">
				<thead>
					<tr>

					</tr>
				</thead>
				<tbody>

					<c:forEach var="account" items="${account}">
						<tr>
							<td>${account.typeAccount}</td>
						</tr>
						<tr>
							<td><label for=${account.accountNumber}>Account
									Number: </label> <input type="radio" id=${account.accountNumber
								} name="accountNumber" value="${account.accountNumber}" />${account.accountNumber}<br />
								Account Balance: ${account.balance}</td>
						</tr>
					</c:forEach>
					<tr>
						<td><form:label for="transactionAmount"
								path="transactionAmount">Amount</form:label></td>
						<td><form:input class="form-control" path="transactionAmount" /></td>
						<td><input type="submit" value="submit" /></td>
					</tr>

				</tbody>
			</table>
		</form:form>
	</div>
</body>
</html>
