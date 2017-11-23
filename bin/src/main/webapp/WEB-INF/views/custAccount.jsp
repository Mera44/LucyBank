
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<html>
 
 <tiles:insertDefinition name="baseLayout">
    <tiles:putAttribute name="title"> customerDetail.title </tiles:putAttribute>
    <tiles:putAttribute name="heading"> customerDetail.heading </tiles:putAttribute>
    <tiles:putAttribute name="tagline"> customerDetail.tagline </tiles:putAttribute>
    <tiles:putAttribute name="body">
   
	<p></p>
 
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
	<div class="image-div">
	
	<img class="img-circle" src="<c:url value="/image/${customer.profile.userName}.png"></c:url>"
						alt="image" style="width: 100%" />
  	</div>
  <h2><spring:message code="profile.detail"/></h2>            
  <table class="table table-hover">
    <thead>
      <tr>
  
      </tr>
    </thead>
    <tbody>
     <tr>
    
        <td><c:out value="${customer.profile.firstName}"></c:out><br />
        <c:out value="${customer.profile.lastName}"></c:out><br />
         <c:out value="${customer.profile.email}"></c:out></td>
      </tr>
      <c:forEach var="account" items="${account}" >
      <tr><td>${account.typeAccount}</td></tr>
      <tr>
      		<td><spring:message code="profile.accountnumber"/>: ${account.accountNumber}<br />
      		<spring:message code="profile.accountbalance"/>:  ${account.balance}<br />
      		 <span><a href="<spring:url value="/checkout?cartId=${account.id}"/>" ><spring:message code="profile.statement"/></a> </span></td>
      		  
      </tr>
     </c:forEach> 
      <tr>
          <td class="link"><span><a href="deposit/${customer.id}"><spring:message code="profile.deposit"/></a></span>
          <span> <a href="withdraw/${customer.id}"><spring:message code="profile.withdraw"/></a></span>
            <span><a href="transfer/${customer.id}"><spring:message code="profile.transfer"/></a></span>
            <span><a href="paybill/${customer.id}"><spring:message code="profile.paycreditbill"/></a></span>
            <%--  <span><a href="/checkout?cartId=${customer.id}"> Statement </a></span> --%>
           
             </td>
      </tr>
    </tbody>
  </table>
</div>
</body>
</html>