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
	<form action="${ pageContext.request.contextPath }/GeneralController?action=saveProject" method="POST">
		<p>Project name:</p>
		<input type="text" name="name" value=""/>
		<br><input type="submit" value="Save"/>
		<br><a href="${ pageContext.request.contextPath }/GeneralController?action=viewEmployees" class="action">Back</a>
	</form>
</body>
</html>