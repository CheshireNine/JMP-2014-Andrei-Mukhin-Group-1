<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${ pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css"/>
<title>Employees Management</title>
</head>
<body>
	<form action="${ pageContext.request.contextPath }/GeneralController?action=performFindEmployee" method="POST">
		<p>Input employee id:</p>
		<input type="text" name="id" value=""/>
		<br><input type="submit" value="Find employee"/>
	</form>
</body>
</html>