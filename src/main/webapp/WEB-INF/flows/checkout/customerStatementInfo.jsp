
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>

<html>
 
 <tiles:insertDefinition name="baseLayout">
    <tiles:putAttribute name="title"> customerDetail.title </tiles:putAttribute>
    <tiles:putAttribute name="heading"> customerDetail.heading </tiles:putAttribute>
    <tiles:putAttribute name="tagline"> customerDetail.tagline </tiles:putAttribute>
    <tiles:putAttribute name="body">
   
	<p>List</p>
 
    </tiles:putAttribute>

</tiles:insertDefinition>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset="utf-8">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Invalid cart </title>
</head>
<body>
<form:form modelAttribute="statement" class="form-horizontal">
			<fieldset>
				<legend>Choose Starting Date</legend>

		  	<!-- 	<p>
		  		     <form:errors path="*" cssStyle="color : red;" /> 
		        </p>
   -->

				<div class="form-group">
					<label class="control-label col-lg-2" for="startDate">Starting Date</label>
					<div class="col-lg-10">
						<form:input id="startDate" path="startDate" type="date" class="form:input-large" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-offset-2 col-lg-10">
				
						<input type="submit" id="btnAdd" class="btn btn-primary"
							value="Submit"  name="_eventId_shippingDetailCollected"/>
					</div>
				</div>

			</fieldset>
		</form:form>
</body>
</html>
