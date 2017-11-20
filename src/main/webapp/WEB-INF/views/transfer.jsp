
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

		<h2>Transfer Money</h2>
		<form:form action="${customer.id}" modelAttribute="transaction"
			method="post">
			<table class="table table-hover">
				<thead>
					<tr>
					<th></th>
                     <th> </th>
					</tr>
				</thead>
				<tbody>

					<c:forEach var="account" items="${accounts}">
						<tr>
							<td><h4>${account.typeAccount}</h4></td>
							
						</tr>
						<tr>
							<td><label for=${account.accountNumber}><h6>From
										Account :</h6> </label> <input type="radio" id="${account.accountNumber}"
								name="accountFrom" value="${account.accountNumber}" />${account.accountNumber}<br />
								Account Balance: ${account.balance}</td>
						</tr>
					</c:forEach>
						<tr>
							<td><h4>Transfer Amount</h4></td>
								<td><form:label for="transactionAmount"
								path="transactionAmount">Amount</form:label>
						<form:input class="form-control" path="transactionAmount" />
						<input type="submit" value="submit" /></td>
							
						</tr>
					<c:forEach var="account" items="${accounts}">
						<tr>
							<td><label for=${account.typeAccount}><h6>Transfer
										To Your Own</h6> </label><br>${account.typeAccount}: <input type="radio"
								id="${account.typeAccount}" name="accountTo"
								value="${account.accountNumber}" />${account.accountNumber}<br />
								Account Balance: ${account.balance}</td>
						</tr>
					</c:forEach>
					<tr>
						<td><h6>
								<label>Transfer To Other LucyBank Member Account </label>
							</h6></td>
					</tr>
					<c:forEach var="account" items="${accountOther}">
						<tr>

							<td><label for=${account.accountNumber}> ${account.typeAccount}:</label><input
								type="radio" id="${account.accountNumber
								}"
								name="accountTo" value="${account.accountNumber}" />${account.accountNumber}</td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		</form:form>
	</div>
</body>
</html>
