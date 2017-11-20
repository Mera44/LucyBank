
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>

<tiles:insertDefinition name="baseLayout">
	<tiles:putAttribute name="title"> teller.title </tiles:putAttribute>
	<tiles:putAttribute name="heading"> teller.heading </tiles:putAttribute>
	<tiles:putAttribute name="body">

		<p>Customer Account</p>

	</tiles:putAttribute>

</tiles:insertDefinition>
<html>
<head>
<title></title>
</head>
<body>
	<form:form action="${customer.id}" modelAttribute="transaction"
		method="post">
		<label for="accountType">Choose From Account :</label>
		<c:forEach var="account" items="${accounts}">
			<input type="radio" name="accountFrom"
				value="${account.accountNumber}" />${account.typeAccount}  ${account.accountNumber} 
         </c:forEach>
		<br>
		<label for="accountType"> Pay To Account :</label>
		<input type="radio" name="accountTo"
			value="${accountOther.accountNumber}" /> ${accountOther.typeAccount} ${accountOther.accountNumber} 
     
    	
		<br>
		<form:label for="transactionAmount" path="transactionAmount">Amount</form:label>
		<form:input type="text" path="transactionAmount" />
		<br>
		<input type="submit" value="submit" />
	</form:form>
</body>
</html>
