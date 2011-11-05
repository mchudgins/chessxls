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

		<script type="text/javascript" src="https://www.google.com/jsapi"><!-- stupid jsp processor --></script>
		<script type="text/javascript">
			google.load("visualization", "1", {packages:["corechart"]});
			google.setOnLoadCallback(drawChart);
			function drawChart()
				{
				var dataP = new google.visualization.DataTable();
				dataP.addColumn('string', 'Player');
				dataP.addColumn('number', 'Games Played');
				dataP.addColumn('number', 'Wins');
				dataP.addColumn('number', 'Draws');
				dataP.addRows(${fn:length(stats)});
				<c:forEach varStatus="status" var="c" items="${stats}" >
					dataP.setValue( ${status.index}, 0, '${c.player}');
					dataP.setValue( ${status.index}, 1, ${c.games});
					dataP.setValue( ${status.index}, 2, ${c.wins});
					dataP.setValue( ${status.index}, 3, ${c.draws});
				</c:forEach>
				
				var chartP = new google.visualization.BarChart(document.getElementById('chartPerformance'));
				chartP.draw(dataP, {width: 400, height: 300, title: 'Player Performance',
					vAxis: {title: 'Player', titleTextStyle: {color: 'red'}}});
				
				var dataS = new google.visualization.DataTable();
				dataS.addColumn('string', 'Player');
				dataS.addColumn('number', 'Wins');
				dataS.addColumn('number', 'Losses');
				dataS.addRows(${fn:length(stats)});
				<c:forEach varStatus="status" var="c" items="${stats}" >
					dataS.setValue( ${status.index}, 0, '${c.player}');
					dataS.setValue( ${status.index}, 1, ${c.longestWinStreak});
					dataS.setValue( ${status.index}, 2, ${c.longestLossStreak});
				</c:forEach>
				
				var chartS = new google.visualization.BarChart(document.getElementById('chartStreaks'));
				chartS.draw(dataS, {width: 400, height: 300, title: 'Player Streakiness',
					vAxis: {title: 'Player', titleTextStyle: {color: 'red'}}});
				}
		</script>
 	</head>
	<body>
		<h1>Tim's <em>Official</em> Chess Spreadsheet</h1>
		
		<div id="top">
				<ul id="global-nav">
					<li><a href="${urlStandings}" class="here">Player Standings</a>
						<ul>
							<li><a href="?sort=player" class="${classPlayer}" >By Player</a></li>
							<li><a href="?sort=gamesplayed" class="${classGamesPlayed}">By Games Played</a></li>
							<li><a href="?sort=wins" class="${classWins}">By Wins</a></li>
							<li><a href="?sort=winpercentage" class="${classWinpercentage}">By Win%</a></li>
						</ul></li>
					<li><a href="${urlTeams}" >Teams</a></li>
					<li><a href="${urlGames}" >Games</a></li>
				</ul>
		</div>
		<div id="pageContent" >
			<div id="chart_area">
				<div class="center">
					<div id="chartPerformance"><!-- stupid jsp processor --></div>
					<div id="chartStreaks"><!-- stupid jsp processor --></div>
				</div>
			</div>
			
			<div id="statsTable" >
			<table id="table" >
				<thead><tr>
					<th><h3>Player</h3></th>
					<th><h3>Games Played</h3></th>
					<th><h3>Wins</h3></th>
					<th><h3>Win%</h3></th>
					<th><h3>Losses</h3></th>
					<th><h3>Draws</h3></th>
					<th><h3>Longest Winning Streak</h3></th>
					<th><h3>Longest Losing Streak</h3></th>
					<th class="nosort"><h3>Current Streak</h3></th>
					<th class="nosort"><h3>Last 10</h3></th>
				</tr></thead>
				<tbody>
				<c:forEach varStatus="status" var="c" items="${stats}" >
					<c:url var="urlPlayer" value="/player/${c.player}" />
					<tr class="${status.index % 2 == 0 ? 'evenRow' : 'oddRow'}" >
						<td><a href="${urlPlayer}">${c.player}</a></td>
						<td class="number_cell">${c.games}</td>
						<td class="number_cell">${c.wins}</td>
						<td class="number_cell">
							<fmt:formatNumber value="${c.winPercentage}" maxFractionDigits="2" minFractionDigits="2"/></td>
						<td class="number_cell">${c.losses}</td>
						<td class="number_cell">${c.draws}</td>
						<td class="number_cell">${c.longestWinStreak}</td>
						<td class="number_cell">${c.longestLossStreak}</td>
						<td class="number_cell">${c.currentStreak}</td>
						<td class="number_cell">${c.last10}</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
	 		</div>
	 		<div class="notes">
	 			<h4>Notes</h4>
		 		<ol>
		 			<li>The <a name="draws"><em>Draws</em></a> column includes both mutually agreed draws and 'Did Not Finish' games from the spreadsheet.</li>
		 			<li>Data was updated <fmt:formatDate value="${lastUpdate}" dateStyle="FULL" type="both" /></li>
		 		</ol>
	 		</div>
	 	</div>
		<jsp:directive.include file="foot-boilerplate.xml" />
  	</body>
</html>
</jsp:root>
