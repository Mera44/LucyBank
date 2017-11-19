
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<html>
 
 <tiles:insertDefinition name="baseLayout">
    <tiles:putAttribute name="title"> teller.title </tiles:putAttribute>
    <tiles:putAttribute name="heading"> teller.heading </tiles:putAttribute>
    <tiles:putAttribute name="tagline"> teller.tagline </tiles:putAttribute>
    <tiles:putAttribute name="body">


    </tiles:putAttribute>

</tiles:insertDefinition>
	  
<html>
<head>
<title></title>
</head>
<body>
<c:forEach var= "account"  items="${account}">
    <span>${account.accountNumber}</span>
    <span>${account.balance}</span><br><br>
</c:forEach>
<span><a href="withdraw?id=${customer.id}"> Withdraw</a></span>
<span><a href="deposit?id=${customer.id}"> Deposit</a></span>
<span><a href="deposit?id=${customer.id}"> Make Transfer</a></span>
<span><a href="paybill?id=${customer.id}"> Pay Bill</a></span>
</body>
</html>	