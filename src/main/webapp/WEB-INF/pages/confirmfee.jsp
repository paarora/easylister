<html>

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
font-size: 24px;
color: #0B610B;
font-weight: bold;
}
</style>
<body>
	<table width=900 class="center">
		<tr>
			<td>
				<div class="floatRight">
					<table height="150">
						<tr valign="top">
							<td>HISTORY</td>
							<td>|</td>
							<td>HOME</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
	
		<table width=900 class="center">
			<tr class="spaceUnder">
				<td colspan="2" align="center"><img
					src="http://s20.postimg.org/5uhdqunf1/logo7.png" width="450"
					align="middle" alt="Easylister" /></td>
			</tr>
			<tr class="spaceUnder">
				<td colspan="2" align="center" class="green">You will be charged ${fee} for listing
					this item.</td>
			</tr>
			<tr class="spaceUnder">
				<td colspan="2" align="center">Please press the submit button
					to confirm that you agree to pay the above mentioned fee to list your item.</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<table>
						<tr>
							<td width="140">
							<form action="/easylister/listed" method="post">
							<input type="hidden" name="confirm" value="true">
							<input type="image"
								src="http://s20.postimg.org/d93yazfnx/submitbutton.png"
								width="130" alt="submit" name="submit" />
							</form></td>
							<td style="border-left: 1px solid #ccc; padding-left: 10px;"><a
								href="/easylister/welcome">Cancel</a></td>
						</tr>

					</table>
			</tr>
		</table>
	

</body>
</html>