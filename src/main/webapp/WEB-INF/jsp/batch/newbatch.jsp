<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
    xmlns:c="http://java.sun.com/jsp/jstl/core"
    xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
    xmlns:fn="htp://java.sun.com/jsp/jstl/functions" version="2.0">
    <jsp:directive.page contentType="text/html" isELIgnored="false"/>
<!--
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
  -->
  
<html>
	<head>
		<title>SPiNS Test Jig</title>
		<link rel="icon" type="image/ico" href="/images/favicon.ico" />
		<link rel="stylesheet" type="text/css" href="/css/clean-v0.css" />
	</head>
	<body>
		<h1>Test Jig</h1>
		<h2>Selected Request Info</h2>
		<p>${message}</p>
		<p>${app.name} Version: ${app.version}</p>
		<p>Session ID: ${pageContext.session.id}</p>
		<p>User Principal: ${pageContext.request.userPrincipal}</p>
		<p>Credential Key:  ${credential.key}</p>
		<p>Access Token:  ${credential.accessToken}</p>
		<p>User Locale:  ${userLocale}</p>

		<h2>Batch List</h2>

		<c:set var="batchList" value="${pageContext.request.locales}" />
		
		<c:forEach var="batchItem" items="${clientLocales}" begin="1">
		    ID: ${batchItem.Id}
		    <br />
		     Status: ${batchItem.status}
		    <br />
		     Archive Key:  ${batchItem.sliceFilename}
		  <br /><br />
		</c:forEach>
		
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
	</body>
</html>
</jsp:root>