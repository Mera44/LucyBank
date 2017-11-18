<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<div class="header-div">
	<div class="logo">
	 	<img class="logo-img" src="<spring:url value='/resource/images/logo-lucy.png' />" />
	 </div>
	  <div class="nav">
	  		
	  		<span><a href="<%=request.getContextPath() %>/welcome">Home |</a></span>
	  		<span><a href="<%=request.getContextPath() %>/banker/customers">Customers |</a></span>
	  		
	  		<span><a href="<%=request.getContextPath() %>/customer/profile">Profile |</a></span>
	  		<span><a href="<%=request.getContextPath() %>/customer/payCredit">Pay Credit |</a></span>
	  		<span><a href="<%=request.getContextPath() %>/customer/accountSummery">Account Summery |</a></span>
	  		<span><a href="<%=request.getContextPath() %>/customer/statment">Statement |</a></span>
	  		<span><a href="<%=request.getContextPath() %>/customer/transfer">Transfer |</a></span>
	  		
	  		<span><a href="<%=request.getContextPath() %>/teller/deposit">Deposit |</a></span>
	  		<span><a href="<%=request.getContextPath() %>/teller/withdraw">Withdraw |</a></span>
	  		<span>
	  		
	  		
	  		
				
			
					<security:authorize access="isAuthenticated()">
	  					Welcome:  <security:authentication property="principal.username" />
	  					<a href="<c:url value="/logout" />">Logout</a>
					</security:authorize>
			</span>	
			<span>
				<security:authorize access="isAnonymous()">
					<a href="<spring:url value='/login' />"
						class="btn btn-default pull-right"> Login </a>
				</security:authorize>
			</span>
		
	    		
	  </div>
	  <div class="language"> 
	  	<a href="?language=en_US">English</a>|<a href="?language=zh_CN">Amharic</a>
	  </div>
</div>