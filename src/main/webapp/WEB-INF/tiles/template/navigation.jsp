<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div class="header-div">
	<div class="logo">
	 	<img class="logo-img" src="<spring:url value='/resource/images/logo-lucy.png' />" />
	 </div>
	  <div class="nav">
	  		<security:authorize access="isAuthenticated()">
	  		
	  		<span><a href="<%=request.getContextPath() %>/welcome"><spring:message code="profile.home"/> |</a></span>
	  		
	  		<security:authorize access="hasRole('ROLE_BANKER')">
	  		<span><a href="<%=request.getContextPath() %>/banker/customers"><spring:message code="profile.customers"/> |</a></span>
	  		<span><a href="<%=request.getContextPath() %>/customer/profile"><spring:message code="profile.profile"/> |</a></span>
	  		<span><a href="<%=request.getContextPath() %>/customer/payCredit"><spring:message code="profile.paycredit"/> |</a></span>
	  		<span><a href="<%=request.getContextPath() %>/customer/accountSummery"><spring:message code="profile.accountsummary"/> |</a></span>
	  		<span><a href="<%=request.getContextPath() %>/customer/statment"><spring:message code="profile.statement"/> |</a></span>
	  		<span><a href="<%=request.getContextPath() %>/customer/transfer"><spring:message code="profile.transfer"/> |</a></span>
	  		
	  		<span><a href="<%=request.getContextPath() %>/teller/deposit"><spring:message code="profile.deposit"/> |</a></span>
	  		<span><a href="<%=request.getContextPath() %>/teller/withdraw"><spring:message code="profile.withdraw"/> |</a></span>
	  		</security:authorize>
	  		
	  		<span>
	  		
	  		
	  		
				
			
					
	  					Welcome:  <security:authentication property="principal.username" /> 
	  					<a href="<c:url value="/logout" />"><spring:message code="profile.logout"/> </a></span>	
					</security:authorize>
	<security:authorize access="isAnonymous()">
					<a href="<spring:url value='/login' />">Login</a>
				</security:authorize>
	
	    		
	  </div>
		  <div class="language"> 
	  	<a href="?language=en_US">English</a>|<a href="?language=fr">French</a>
	  </div>
</div>