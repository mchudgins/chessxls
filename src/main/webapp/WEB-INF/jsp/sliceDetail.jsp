<!-- $Id: sliceDetail.jsp 903 2011-04-15 20:26:43Z mchudgins@dstsystems.com $ -->
<jsp:root
	xmlns:jsp="http://java.sun.com/JSP/Page"
    xmlns:c="http://java.sun.com/jsp/jstl/core"
    xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
    xmlns:fn="http://java.sun.com/jsp/jstl/functions"
    xmlns="http://www.w3.org/1999/xhtml"
    version="2.0">
<jsp:directive.page contentType="text/html" isELIgnored="false" pageEncoding="UTF-8" />
	<jsp:output omit-xml-declaration="true" />
	<jsp:output doctype-root-element="html"
		doctype-system="about:legacy-compat" />    
 	 
<html>
	<head>
		<title>SPiNS ${pageTitle}</title>
		<jsp:directive.include file="head-boilerplate.xml" />
 	</head>
	<body>
		<h1>${pageTitle}</h1>

		<div><img alt="${sliceImageUrl}" src="${sliceImageURL}" /></div>
		<table>
			<tr><td>Slice ID</td><td>${slice.id}</td></tr>
			<tr><td>Image Location</td><td>${sliceImageURL}</td></tr>
			<tr><td></td><td></td></tr>
			<tr><td>Batch ID</td><td>${slice.batchId}</td></tr>
			<tr><td>Document ID</td><td>${slice.docId}</td></tr>
			<tr><td>Page Number</td><td>${slice.pageNum}</td></tr>
			<tr><td>Model ID</td><td>${slice.modelId}</td></tr>
			<tr><td>Field Type ID</td><td>${slice.fieldTypeId}</td></tr>
			<tr><td>Status</td><td>${slice.status}</td></tr>
			<tr><td>Sensitivity</td><td>${slice.sensitivity}</td></tr>
			<tr><td>Assigned To</td><td>${slice.assignedTo}</td></tr>
			<tr><td>Assigned Time</td><td>${slice.assignedTime}</td></tr>
			
		</table>		
		<jsp:directive.include file="foot-boilerplate.xml" />
	</body>
</html>
</jsp:root>
