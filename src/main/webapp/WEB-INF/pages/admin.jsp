<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Page</title>
</head>
<style type="text/css">
body {
	color: #000000;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 13px;
	font-style: normal;
	font-variant: normal;
	font-weight: 700;
	line-height: normal;
}

td {
	font-size: 13px;
	max-width: 300px;
    word-wrap: break-word;
}

.padlrb {
	padding-bottom: 20px;
}

.borderBottom {
	border-bottom: 3px solid #848484;
	padding-bottom: 15px;
}

.pad10 {
	padding: 10px;
}

.pad20top {
	padding-left: 10px;
	padding-top: 20px;
}

.floatRight {
	float: right;
}

table.b4 {
	width: 100%
}

table.b4,table.b4 tr,table.b4 td {
	border: 1px solid #ccc;
	border-style: groove;
	padding: 15px;
	border-collapse: collapse;
}

.bold {
	font-weight: bold;
}

.font15 {
	font-size: 15px;
}
</style>
<body>
	<table width="900px" border="0" align="center" cellspacing="0"
		cellpadding="0">
		<tr>
			<td>
				<div class="floatRight">
					<table>
						<tr valign="top">
							<td><a href="/easylister/admin">HISTORY</a></td>
							<td>|</td>
							<td><a href="/easylister/admin?highPrice=true">HIGH
									PRICE LISTINGS</a></td>
						</tr>
					</table>
				</div>
			</td>
		</tr>

		<tr>
			<td>
				<div class="borderBottom">
					<a href="/easylister/welcome"><img src="http://s20.postimg.org/5uhdqunf1/logo7.png" width="400"
						align="middle" alt="Easylister" /></a>
				</div>
			</td>
		</tr>
		<tr>
			<td>
				<div style="float:right">
				<form action="/easylister/admin" method="post">
		Search Transaction : 
		<input type="text" id="id" name="id" size="10"
							value="${id}">
							<input type="submit" value="Submit" />
					</form>
				</div>
			</td>
		</tr>
		<tr>
			<td>
				<table>
					<tr>
						<td class="pad20top">Hello <b>ADMIN</b>,
						</td>
					</tr>
					<tr>
						<td class="pad10">Here is the hitory of transactions on
							easylister</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table class="b4">
					<tr>
						<td class="bold font15">ID</td>
						<td class="bold font15">Source URL</td>
						<td class="bold font15">Target URL</td>
						<td class="bold font15">Listing Price</td>
						<td class="bold font15">Listing Date</td>
					</tr>
					<c:forEach var="records" items="${records}">
						<tr>
							<td>${records.transactionId}</td>
							<td>${records.sourceUrl}</td>
							<td>${records.targetUrl}</td>
							<td>${records.listingPrice}</td>
							<td>${records.date}</td>
						</tr>
					</c:forEach>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>