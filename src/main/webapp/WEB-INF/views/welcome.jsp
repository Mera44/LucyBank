<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<html>
 
 <tiles:insertDefinition name="baseLayout">
    <tiles:putAttribute name="title"> welcome.title </tiles:putAttribute>
    <tiles:putAttribute name="heading"> welcome.heading </tiles:putAttribute>
    <tiles:putAttribute name="tagline"> welcome.tagline </tiles:putAttribute>
    <tiles:putAttribute name="body">
   
	    Language : <a href="?language=en_US">English</a>|<a href="?language=zh_CN">Chinese</a>
		 
		<h3>
			welcome.springmvc : <spring:message code="welcome.springmvc" text="default text" />
		</h3>
		 
		<p> Current Locale : ${pageContext.response.locale}</p>
	 
 
    </tiles:putAttribute>

</tiles:insertDefinition>
	  

</html>