<html>
<title>Listing Confirmation Page</title>
<style type="text/css">
body {
	border: 0px none;
	text-align: center;
}

.center {
	margin: auto;
}

.inputField {
	clear: both;
	display: block;
	width: 600px;
	border: 2px solid #9f9f9f;
}

tr.spaceUnder>td {
	padding-bottom: 2em;
}

.floatRight {
	float: right;
}

.smallText {
	font-size: 12px;
	font-family: "Verdana", Sans-serif;
}

.green {
font-family: arial, 'times new roman';
font-size: 2em;
color: #0B610B;
font-weight: bold;
}
.size {
font-family: arial, 'times new roman';
font-size: 1em;
}
</style>
<body>
	<table width=900 class="center">
		<tr>
			<td>
				<div class="floatRight">
					<table height="150">
						<tr valign="top">
							<td><a href="/easylister/history?user=testuser_paarora">HISTORY</a></td>
							<td>|</td>
							<td><a href="/easylister/welcome">HOME</a></td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
	<form name="form" method="GET" action="/easylister/form">
		<table width=900 class="center">
			<tr class="spaceUnder">
				<td colspan="2" align="center">
				<a href="/easylister/welcome"><img
					src="http://s20.postimg.org/5uhdqunf1/logo7.png" width="450"
					align="middle" alt="Easylister" /></a></td>
			</tr>
			<tr class="spaceUnder">
				<td colspan="2" align="center" class="green">Congratulations! you have successfully listed your item.</td>
			</tr>
			<tr class="spaceUnder">
				<td colspan="2" align="center" class="size">The link to your listed item is <a href="${url}">here</a>.</td>
			</tr>
		</table>
	</form>


</body>
</html>