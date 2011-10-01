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

 	</head>
	<body>
		<h1>Tim's <em>Official</em> Chess Spreadsheet</h1>
		
		<div id="top">
				<ul id="global-nav">
					<li><a href="${urlStandings}" class="here">Player Standings</a></li>
					<li><a href="${urlTeams}" >Teams</a></li>
					<li><a href="${urlGames}" >Games</a>
						<ul>
							<li><a href="?sort=player" >Game List</a></li>
							<li><a href="?sort=gamesplayed" class="here">Post a Game Result</a></li>
						</ul></li>
				</ul>
		</div>

		<form method="post" >
			<div>
				<label for="playDate">Play Date</label><input type="text" name="playDate" />
				<table>
				<thead><tr><td>White</td><td>Player</td><td>Black</td></tr></thead>
				<tbody>
				<c:forEach varStatus="status" var="player" items="${players}" >
					<tr>
						<td><input type="radio" name="${playerSide}" value="white" /></td>
						<c:url var="urlPlayer" value="/player/${player}" />
						<td><a href="${urlPlayer}">${player}</a></td>
						<td><input type="radio" name="${playerSide}" value="black" /></td>
					</tr>
				</c:forEach>
				<tr class="emptyRow"><td><!-- empty --></td><td><!-- empty --></td><td><!-- empty --></td></tr>
				<tr>
					<td><input type="radio" name="winner" value="white" /></td>
					<td>Winner</td>
					<td><input type="radio" name="winner" value="black" /></td>
				</tr>
				</tbody></table>
			</div>
			<input type="submit" value="Submit" />
		</form>
		<jsp:directive.include file="foot-boilerplate.xml" />
  	</body>
</html>
</jsp:root>
