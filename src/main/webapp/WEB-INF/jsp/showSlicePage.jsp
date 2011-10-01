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
		<title>SPiNS Data Entry</title>
		<jsp:directive.include file="head-boilerplate.xml" />
		
		<script type="text/javascript" src="http://spins.dstinput.com/js/jquery-1.3.2.min.js" ><!--FIXME jsp processor is mucking up the script tag --></script>
		<script type="text/javascript" src="http://spins.dstinput.com/js/jquery.maskedinput-1.2.2.min.js" ><!--FIXME jsp processor is mucking up the script tag --></script>
		<script type="text/javascript" src="http://spins.dstinput.com/js/jquery-ui-1.7.2.custom.min.js" ><!--FIXME jsp processor is mucking up the script tag --></script>
		<script type="text/javascript" src="http://spins.dstinput.com/js/showSlicePage.js" ><!--FIXME jsp processor is mucking up the script tag --></script>
		<link rel="stylesheet" type="text/css" href="http://spins.dstinput.com/css/clean-v0.css" />
		<link rel="stylesheet" type="text/css" href="http://spins.dstinput.com/css/slice.css" />
		<link rel="stylesheet" type="text/css" href="http://spins.dstinput.com/css/redmond/jquery-ui-1.7.2.custom.css" />
 	</head>
	<body>
	<h1>SPiNS Data Entry</h1>
	<div id="contentwrap">
		<div id="main">
		    <!-- work area -->
		    <div id="slice_work_area" ><form id="workForm" method="post">
		    	<div id="finished" style="display: none;" >
		    		<img id="finishedImage" src="http://spins.dstinput.com/images/checkerFlag.jpg" alt="No Work Available." />
		    		<br />
			    	<span id="NoWork">No slices are available.  Please <a id="refresh" href="#" >try again</a> later.</span>
		    	</div>

			<div id="expired" style="display: none;" >
				<img id="expiredImage" src="http://spins.dstinput.com/images/expired.jpg" alt="Time has expired." />
				<br />
				<span id="ExpiredText">Time has expired.  Please <a id="refresh2" href="/work" >refresh</a> the page
				or <a id="logout" href="/logout">log out.</a></span>
			</div>

			<div id="slice" style="display: none;">
			          <img id="viewImage" alt="Your current slice of work." style="border: 2px solid red; padding: 10px;"/>
			          <img id="prefetchImage" alt="Your Next Slice of Work" style="display: none;"/>
			        <br />

			        Enter <b><span id="fieldtype_name">Data</span></b> : <span id="dataEntryWrapper">
						<input id="dataEntry" type="text" name="data" />
						<select id="combobox" name="combobox" >
							<option></option>
						</select>
					</span>
			        <br />

				<div id="sliceButtons">
					<button type="button" id="enterButton">Enter</button>
					<button type="button" id="skipButton">Skip ...</button>
					<button type="submit" id="nextBatch" disabled="disabled" >Next Batch</button>
				</div>

				<div id="sliceResultSet" style="display: none;">
					<c:forEach varStatus="status" var="s" items="${slices}" >
					<input type="text" id="slice_${s.slice.id}" name="slice_${s.slice.id}" />
					</c:forEach>
				</div>

				<div id="progressBar" ></div>
				<div id="batchInfo" >
					<p>You are working on item <span id="itemCount">0</span> in a batch of <span id="batchSize">0</span></p>
				</div>
		        </div></form>
		    </div>

			<div id="userDashboard" style="margin: 0 20%;">
	         <table id="usageGrid" style="width: 500px; height: 150px;" >
	<!--       	<caption>Work History</caption>	-->
	                  <thead>
	                    <tr>
	                      <th>Work History</th>
	                      <th>Assigned</th>
	                      <th>Completed</th>
	                      <th>Skipped</th>
	                      <th>Errors</th>
	<!--                      <th>Average Time</th> -->
	                    </tr>
	                  </thead>
	                    <tr class="odd" >

	                    	<td class="userStatsDesc" >Today</td>
	                    	<td class="userStatsTD" id="todayassignedStats" >${userWorkStats.today.assigned}</td>
	                    	<td class="userStatsTD" id="todaycompletedStats" >${userWorkStats.today.completed}</td>
	                    	<td class="userStatsTD" id="todayskippedStats" >${userWorkStats.today.skipped}</td>
				<td class="userStatsTD" id="placeholder" />
	                    </tr>
	                    <tr>
	                    	<td class="userStatsDesc" >Yesterday</td>
	                    	<td class="userStatsTD" id="yesterdayassignedStats" >${userWorkStats.yesterday.assigned}</td>
	                    	<td class="userStatsTD" id="yesterdaycompletedStats" >${userWorkStats.yesterday.completed}</td>
	                    	<td class="userStatsTD" id="yesterdayskippedStats" >${userWorkStats.yesterday.skipped}</td>
	                    	<td class="userStatsTD" id="yesterdayerrorsStats" >${userWorkStats.yesterday.errors}</td>
	                    </tr>
	                    <tr class="odd" >
	                    	<td class="userStatsDesc" >This Month</td>
	                    	<td class="userStatsTD" id="monthassignedStats" >${userWorkStats.month.assigned}</td>
	                    	<td class="userStatsTD" id="monthcompletedStats" >${userWorkStats.month.completed}</td>
	                    	<td class="userStatsTD" id="monthskippedStats" >${userWorkStats.month.skipped}</td>
	                    	<td class="userStatsTD" id="montherrorsStats" >${userWorkStats.month.errors}</td>
	                    </tr>
	                </table>
			</div>

			<div id="theme_photo">
				<img src="http://spins.dstinput.com/images/keyboard.jpg" alt="Just Key It!" />
			</div>

		</div>
	</div>

	<div id="DataDiv" style="display: none;" >
		<div id="SecureUpdatePage" url="http://sandbox/sliceUpdate" />
		<div id="slicesDiv" >
<!-- 
			{foreach from=$images item=image}
			<div class="slice" id="{$image.id}" url="{$image.url}" expires="{$image.expires}" mask="{$image.mask}" name="{$image.name}" >
{if count( $image.FieldtypeValues ) > 0 }
				<div id="choices">
					{foreach from=$image.FieldtypeValues item=opt}
					<div class="choice">{$opt}</div>
					{/foreach}
				</div>
{/if}

			</div>
			{/foreach}
 -->			
			<c:forEach varStatus="status" var="s" items="${slices}" >
				<div class="slice" id="${s.slice.id}" url="${s.imageURL}" expires="${s.expirationMillis}" mask="" name="A field" >
					<!--FIXME jsp processor is mucking up the script tag -->
				</div>
			</c:forEach>
			
		</div>
		<div id="skipReasons" title="Skipping slice..." style="display: none;" >
			<div id="skipReasonsDiv" >
				<form><fieldset>
					<legend>Please tell me why you are skipping this slice</legend>
					<label for="skipRadio0"><input value="1" type="radio" name="skipRadio" id="skipRadio0" />
					No writing - no data entry needed</label><br />
					<label for="skipRadio1"><input value="2" type="radio" name="skipRadio" id="skipRadio1" />
					Can't read writing</label><br />
<!--					<label for="skipRadio2"><input value="3" type="radio" name="skipRadio" id="skipRadio2" />
					The image displayed doesn't match the input field</label> -->
					<label for="skipRadio3"><input value="4" type="radio" name="skipRadio" id="skipRadio3" />
					Multiple fund or account numbers are displayed</label>
					<label for="skipRadio4"><input value="5" type="radio" name="skipRadio" id="skipRadio4" />
					Incomplete account number/image chopped off as displayed</label>
					<label for="skipRadio5"><input value="6" type="radio" name="skipRadio" id="skipRadio5" />
					Data displayed is not a fund or account number</label>

				</fieldset></form>
			</div>
		</div>
	</div>
	<jsp:directive.include file="foot-boilerplate.xml" />
	</body>
</html>
</jsp:root>
