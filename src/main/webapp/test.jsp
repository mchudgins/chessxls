<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="http://www.dstresearch.com/css/bootstrap.min.css" />
</head>
<body onLoad="window.initPage()">
<h1>Hello, world.</h1>
<div id="fubar">
	<p>Some text goes here.</p>
</div>
<div id="tableArea" >
<table id="gorf" >
	<thead>
		<tr>
			<th><a href="#" onclick="alert('hello, Column One.' )">Column One</a></th><th>Column Two</th>
		</tr>
	</thead>
	<tbody>
		<tr><td>Column One, Row One</td><td>Column Two, Row One</td></tr>
		<tr><td>Column One, Row Two</td><td>Column Two, Row Two</td></tr>
	</tbody>
</table>
</div>

<div id="control"><p style="background: grey;">Some gray stuff.</p></div>
<script type="text/javascript" src="/chessxls/TestJig/TestJig.nocache.js" ><!-- @#$% jsp compiler! --></script>
<script type="text/javascript" >
	//
	// see if console object exists, if not create one
	// to silently eat .log messages
	
	if ( typeof console !== "object" )
		{
		console = new Object();
		console.log = function( s ) {};
		}
	else
		console.log( "Console detected.  Logging enabled." );

	window.initPage	= function()
		{
		if ( typeof window.pageSetup === "undefined" )
			{
			console.log( "not ready." );
			setTimeout( window.initPage, 100 );
			}
		else
			window.pageSetup( "gorf" );
		};
</script>
</body>
</html>