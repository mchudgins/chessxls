<!-- $Id$ -->
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
		<title>Chess XLS ${pageTitle}</title>
		<jsp:directive.include file="head-boilerplate.xml" />
		<!-- this one little ol' bit o' css is needed in the jsp file because the background image logo url relies on the current app.imagePath -->
		<style type="text/css"> 
        th { background-image: url(${app.imagePath}/sort-up.png); background-repeat: no-repeat; background-position: left top; }
		</style>
 	</head>
	<body>
		<h1>Tim's <em>Official</em> Chess Spreadsheet</h1>
		
		<div id="top">
				<ul id="global-nav">
					<li><a href="${pageContext.request.contextPath}/standings">Player Standings</a></li>
					<li><a href="${pageContext.request.contextPath}/teams">Teams</a></li>
					<li><a href="${pageContext.request.contextPath}/games" class="here">Games</a>
						<ul>
							<li><a href="?sort=player" class="here">Option 1</a></li>
							<li><a href="?sort=winpercentage">Option 2</a></li>
						</ul></li>
				</ul>
		</div>
<!-- 
			<span class="page-bar-right">
				<label>Sorted by</label>
				<select onchange="window.open(this.options[this.selectedIndex].value,'_top')">
					<option value="/chessxls/?sort=player">Player</option>
					<option value="/chessxls/?sort=gamesplayed">Games Played</option>
					<option value="/chessxls/?sort=wins">Wins</option>
					<option selected="" value="/chessxls/?sort=winpercentage">Win Per Centage</option>
				</select>
			</span>
 -->

		<div>
			<p>Not implemented yet.</p>
		</div>
		 		
		<jsp:directive.include file="foot-boilerplate.xml" />
  	</body>
</html>
</jsp:root>
