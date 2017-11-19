
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

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
<form:form  action="withdraw/${customer.id}" modelAttribute="transaction" method= "post" >
<c:forEach var= "account"  items="${account}">
    <input type="radio" name= accountNumber"/>
</c:forEach>
<span><a href="withdraw?id=${customer.id}"> Withdraw</a></span>
<span><a href="deposit?id=${customer.id}"> Deposit</a></span>
<span><a href="transfer?id=${customer.id}"> Make Transfer</a></span>
<span><a href="paybill?id=${customer.id}"> Pay Bill</a></span>
</form:form>
</body>
</html>	