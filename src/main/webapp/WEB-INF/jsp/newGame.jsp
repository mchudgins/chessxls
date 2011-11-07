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
		
<!-- what time is it? -->
<jsp:useBean id="now" class="java.util.Date" scope="page" />
<fmt:formatDate var="maxDate" value="${now}" pattern="yyyy-MM-dd" />
		
<!-- Setup URLs -->
<c:url var="urlStandings" value="/standings" />
<c:url var="urlTeams" value="/teams" />
<c:url var="urlGames" value="/games" />

<!-- compute class="here" attribute for subnav menu -->
<c:set var="classPlayer" value="${ ( param.sort=='player' || param.sort==null ) ? 'here' : '' }" /> 
<c:set var="classGamesPlayed" value="${param.sort=='gamesplayed' ? 'here' : '' }" />
<c:set var="classWins" value="${param.sort=='wins' ? 'here' : '' }" />   
<c:set var="classWinpercentage" value="${param.sort=='winpercentage' ? 'here' : '' }" />
 	 
<html>
	<head>
		<title>Chess XLS ${pageTitle}</title>
		<jsp:directive.include file="head-boilerplate.xml" />
		<link href="${app.cssPath}/960fluid-gs.css" media="screen" rel="stylesheet" type="text/css" />
		<link href="${app.cssPath}/formalize.css" media="screen" rel="stylesheet" type="text/css" />
 	</head>
	<body>
		<h1>Tim's <em>Official</em> Chess Spreadsheet</h1>
		
		<div id="top">
				<ul id="global-nav">
					<li><a href="${urlStandings}" >Player Standings</a></li>
					<li><a href="${urlTeams}" >Teams</a></li>
					<li><a href="${urlGames}" class="here" >Games</a>
						<ul>
							<li><a href="${urlGames}">Game List</a></li>
							<li><a href="${urlGames}?new" class="here">Post a Game Result</a></li>
						</ul></li>
				</ul>
		</div>

		<div class="container container_12" >
		<form class="grid_12" method="post" >
			<div class="grid_4 prefix_4 suffix_4" >
				<label for="playDate" style="margin-right: 2em;">Play Date</label>
				<input type="date" name="playDate" value="${maxDate}"
					autofocus="on" max="${maxDate}" required="required" />
			</div>
			<div class="grid_6 prefix_3 suffix_3">
				<table id="table" >
				<thead class="headerRow"><tr><th>White</th><th>Player</th><th>Black</th><th>Not Present</th></tr></thead>
				<tbody>
				<c:forEach varStatus="status" var="player" items="${players}" >
					<tr>
						<td class="center"><input type="radio" name="${player}" value="white" /></td>
						<c:url var="urlPlayer" value="/player/${player}" />
						<td class="center"><a href="${urlPlayer}">${player}</a></td>
						<td class="center"><input type="radio" name="${player}" value="black" /></td>
						<td class="center"><input type="radio" name="${player}" value="Not Present" checked="checked"/></td>
					</tr>
				</c:forEach>
				<tr class="winnerRow">
					<td class="center"><input type="radio" name="winner" value="white" /></td>
					<td class="center">Winner</td>
					<td class="center"><input type="radio" name="winner" value="black" /></td>
				</tr>
				</tbody></table>
			</div>
			<div class="grid_1 prefix_8 suffix_3" style="font-size: 80%;" >
				<input type="submit" value="Submit" />
			</div>
		</form>
		</div>
		
		<jsp:directive.include file="foot-boilerplate.xml" />
  	</body>
</html>
</jsp:root>
