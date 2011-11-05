<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
    xmlns:c="http://java.sun.com/jsp/jstl/core"
    xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
    xmlns="http://www.w3.org/1999/xhtml"
    xmlns:fn="htp://java.sun.com/jsp/jstl/functions" version="2.0">
	<jsp:directive.page contentType="text/html" isELIgnored="false" pageEncoding="UTF-8" />
		<jsp:output omit-xml-declaration="true" />
		<jsp:output doctype-root-element="HTML" 
		doctype-system="about:legacy-compat" />    

<html>
	<head>
		<title>ChessXLS ${pageTitle}</title>
		<jsp:directive.include file="head-boilerplate.xml" />
	</head>
	<body>
		<h1>Test Jig</h1>
		<h2>Selected Request Info</h2>
		<p>${app.name} Version: ${app.version}</p>
		<p>App Config Location:  ${testBean.value}</p>
		<p>Context Path:  ${pageContext.request.contextPath}</p>
		<p>Original URI:  ${requestScope['javax.servlet.forward.request_uri']}</p>
		<p>Request URI:  ${pageContext.request.requestURI}</p>
		<p>Request URL:  ${pageContext.request.requestURL}</p>
		<p>Servlet Path:  ${pageContext.request.servletPath}</p>
		<p>Path Info:  ${pageContext.request.pathInfo}</p>
		<p>Path Translated:  ${pageContext.request.pathTranslated}</p>
		<p>Session ID: ${pageContext.session.id}</p>
		<p>User Principal: ${pageContext.request.userPrincipal}</p>
		<p>Credential Key:  ${credential.key}</p>
		<p>Access Token:  ${credential.accessToken}</p>
		<p>User-Agent:  ${header[ "User-Agent" ] }</p>
<!--
 		<p>Credentials: ${fn:length( credentials )} elements</p>
		<table>
			<tr><th>ID</th><th>Access Token</th></tr>
			<c:forEach var="c" items="${credentials}" >
				<tr>
				<td>${c.key}</td><td>${c.accessToken}</td>
				</tr>
			</c:forEach>
		</table>
  -->

		<jsp:directive.include file="foot-boilerplate.xml" />
	</body>
</html>
</jsp:root>