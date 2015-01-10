<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
	<link href="${ pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet" type="text/css" ></link>
	<link href="${ pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css" ></link>
</head>
<body>
	<div class="container">
		<!-- Header -->
		<div class="page-header">
			<tiles:insertAttribute name="header" />
		</div>
		<!-- Menu Page -->
		<div class="col-md-2">
			<tiles:insertAttribute name="menu" />
		</div>
		<!-- Body Page -->
		<div class="col-md-10">
			<tiles:insertAttribute name="body" />
		</div>
	</div>
	<!-- Footer Page -->
	<div class="col-md-12 footer">
		<tiles:insertAttribute name="footer" />
	</div>
</body>
</html>