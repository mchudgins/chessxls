<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
    xmlns:c="http://java.sun.com/jsp/jstl/core"
    xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
    xmlns="http://www.w3.org/1999/xhtml"
    xmlns:fn="htp://java.sun.com/jsp/jstl/functions" version="2.0">
	<jsp:directive.page contentType="text/html" isELIgnored="false" pageEncoding="UTF-8" />
		<jsp:output omit-xml-declaration="true" />
		<jsp:output doctype-root-element="HTML" 
		doctype-system="about:legacy-compat" /> 
<!-- $Id$ -->   

<html>
	<head>
		<title>SPiNS ${pageTitle}</title>
		<jsp:directive.include file="head-boilerplate.xml" />
	</head>
	<body>
		<h1>${pageTitle}</h1>
		<h2>Selected Request Info</h2>
		<p>${message}</p>
		<p>${app.name} Version: ${app.version}</p>
		<p>Session ID: ${pageContext.session.id}</p>
		<p>User Principal: ${pageContext.request.userPrincipal}</p>
		<p>Credential Key:  ${credential.key}</p>
		<p>Access Token:  ${credential.accessToken}</p>
		<p>User Locale:  ${userLocale}</p>
		
		<h2>Servlet Request</h2>
		<table>
			<tr><td>Request URI</td><td>${requestURI}</td></tr>
			<tr><td>Request URL</td><td>${requestURL}</td></tr>
		</table>

		<h2>Page Request</h2>
		<table>
			<tr><td>Request URI</td><td>${pageContext.request.requestURI}</td></tr>
			<tr><td>Request URL</td><td>${pageContext.request.requestURL}</td></tr>
		</table>
		
		<h2>Servlet Context</h2>
		<table>
			<tr><td>Server Info</td><td>${pageContext.servletContext.serverInfo}</td></tr>
			<tr><td>Context Path</td><td>${pageContext.servletContext.contextPath}</td></tr>
<!-- 			<tr><td>Real Path "/"</td><td>${pageContext.servletContext.realPath( "/" )}</td></tr>  -->
			<tr><td>Major Version</td><td>${pageContext.servletContext.majorVersion}</td></tr>
			<tr><td>Minor Version</td><td>${pageContext.servletContext.minorVersion}</td></tr>
		</table>

		<h2>Here is your preferred locale info...</h2>

		<c:set var="clientLocale" value="${pageContext.request.locale}" />
		<c:set var="clientLocales" value="${pageContext.request.locales}" />
		
		Preferred locale: ${clientLocale.displayName}
		 <br />
		Preferred locale country: ${clientLocale.displayCountry}
		<br />
		Preferred locale language: ${clientLocale.displayLanguage}
		<h3>Lower priority locales...</h3>
		<c:forEach var="loc" items="${clientLocales}" begin="1">
		    Locale: ${loc.displayName}
		    <br />
		     Locale country: ${loc.displayCountry}
		   <br />
		    Locale language: ${loc.displayLanguage}
		  <br /><br />
		</c:forEach>
		<jsp:directive.include file="foot-boilerplate.xml" />
		
	</body>
</html>
</jsp:root>