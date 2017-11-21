<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>


 <tiles:insertDefinition name="baseLayout">
    <tiles:putAttribute name="title"> noFund.title </tiles:putAttribute>
    <tiles:putAttribute name="heading"> noFund.heading </tiles:putAttribute>
    <tiles:putAttribute name="tagline"> noFund.tagline </tiles:putAttribute>
    <tiles:putAttribute name="body"> 
	   <p></p>
 
    </tiles:putAttribute>

</tiles:insertDefinition>

<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1 class="alert alert-danger"> ${withdrawId}</h1>
			</div>
		</div>
	</section>

	<section>
		<div class="container">
			<p>${url}</p>
			<p>${exception}</p>
		</div>

		<div class="container">
			<p>
				<a href="${requestScope['javax.servlet.forward.request_uri']}"  class="btn btn-primary">
					<span class="glyphicon-hand-left glyphicon"></span> Back to Accounts
				</a>
			</p>
		</div>
		
	</section>
</body>
</html>
