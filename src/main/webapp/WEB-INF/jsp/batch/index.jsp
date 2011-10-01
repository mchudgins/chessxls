<?xml version="1.0" encoding="utf-8"?>

<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
    xmlns:c="http://java.sun.com/jsp/jstl/core"
    xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
    xmlns:fn="htp://java.sun.com/jsp/jstl/functions" version="2.1" >

    <jsp:directive.page contentType="text/html" isELIgnored="false" />
<!-- $Id: index.jsp 795 2011-01-11 01:18:27Z mchudgins@dstsystems.com $ -->

<html>
	<head>
		<title>SPiNS Batch Index</title>
		<link rel="icon" type="image/ico" href="/images/favicon.ico" />
		<link rel="stylesheet" type="text/css" href="/css/clean-v0.css" />
	</head>
	<body>
		<h1>Batches</h1>
		<ul>
			<li>You can <a href="/batch/create">create</a> a new batch, or</li>
			<li><a href="/batch/monitor">Monitor</a> the status of a specific batch.</li>
		</ul>
		<h2>Selected Request Info</h2>
		<p>${message}</p>
		<p>${app.name} Version: ${app.version}</p>
		<p>Session ID: ${pageContext.session.id}</p>
		<p>User Principal: ${pageContext.request.userPrincipal}</p>
		<p>Credential Key:  ${credential.key}</p>
		<p>Access Token:  ${credential.accessToken}</p>
		<p>User Locale:  ${userLocale}</p>
		
		<h2>Test List </h2>
		<c:if test="${empty testList}" >
			<p>testList has items in it.</p>
		</c:if>
		<p>${testList[ 0 ]}, ${testList[ 1 ]}, ${testList[ 2 ]}</p>
		<c:forEach var="tItem" items="${testList}" begin="0" varStatus="status" >
			<p>Item:  ${tItem}, ${status.count}</p>
		</c:forEach>
		
		<h2>Batch List</h2>
		<c:set var="bList" value="${batchList}" />
		<p>${bList.size} items in list</p>
		<c:forEach var="batchItem" items="${bList}" begin="1">
		    ID: ${batchItem.Id}
		    <br />
		     Status: ${batchItem.status}
		    <br />
		     Archive Key:  ${batchItem.sliceFilename}
		  <br /><br />
		</c:forEach>

	</body>
</html>
</jsp:root>