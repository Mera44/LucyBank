<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


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
	  		<span><a href="<%=request.getContextPath() %>/welcome/logout">Logout </a></span>
	    		
	  </div>
	  <div class="language"> 
	  	<a href="?language=en_US">English</a>|<a href="?language=zh_CN">Amharic</a>
	  </div>
</div>