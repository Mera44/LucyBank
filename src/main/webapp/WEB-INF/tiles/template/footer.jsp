<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<p>&copy; Lucy Bank 2017</p>
  <div class="nav">
	  		
	  		<span><a href="<%=request.getContextPath() %>/welcome"><spring:message code="profile.home"/> |</a></span>
	  		<span><a href="<%=request.getContextPath() %>/banker/customers"><spring:message code="profile.customers"/> |</a></span>
	  		
	  		<span><a href="<%=request.getContextPath() %>/customer/profile"><spring:message code="profile.profile"/> |</a></span>
	  		<span><a href="<%=request.getContextPath() %>/customer/payCredit"><spring:message code="profile.paycredit"/> |</a></span>
	  		<span><a href="<%=request.getContextPath() %>/customer/accountSummery"><spring:message code="profile.accountsummary"/> |</a></span>
	  		<span><a href="<%=request.getContextPath() %>/customer/statment"><spring:message code="profile.statement"/> |</a></span>
	  		<span><a href="<%=request.getContextPath() %>/customer/transfer"><spring:message code="profile.transfer"/>|</a></span>
	  		
	  		<span><a href="<%=request.getContextPath() %>/teller/deposit"><spring:message code="profile.deposit"/> |</a></span>
	  		<span><a href="<%=request.getContextPath() %>/teller/withdraw"><spring:message code="profile.withdraw"/> |</a></span>
	  		<span><a href="<%=request.getContextPath() %>/logout"><spring:message code="profile.logout"/> </a></span>
	    		
	  </div>