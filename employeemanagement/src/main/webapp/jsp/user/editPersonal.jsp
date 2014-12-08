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
	<p>Employee:</p>
	<input type="text" value="${ userData.currentEmployee.firstName } ${ userData.currentEmployee.lastName }" disabled="disabled"/>
	<form action="${ pageContext.request.contextPath }/GeneralController?action=performEditPersonal" method="POST">
		<p>Phone number:</p>
		<input type="text" name="phoneNumber" value="${ userData.currentEmployee.personalInfo.phoneNumber }"/>
		<p>Email:</p>
		<input type="text" name="email" value="${ userData.currentEmployee.personalInfo.email }"/>
		<p>Education:</p>
		<textarea rows="20" cols="65" name="education" >${ userData.currentEmployee.personalInfo.education }</textarea>
		<br><input type="submit" value="Save"/>
	</form>
</body>
</html>