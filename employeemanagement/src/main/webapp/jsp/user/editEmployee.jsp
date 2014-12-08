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
	<form action="${ pageContext.request.contextPath }/GeneralController?action=saveEmployee" method="POST">
		<p>First name:</p>
		<input type="text" name="firstName" value="${ userData.currentEmployee.firstName }"/>
		<p>Last name:</p>
		<input type="text" name="lastName" value="${ userData.currentEmployee.lastName }"/>
		<p>Status:</p>
		<select name="status">
			<option value=""></option>
			<c:forEach items="${statusList}" var="option">
				<option value="${option}" selected="${ userData.currentEmployee.status == option ? selected : false }">
					<c:out value="${option.status}"/>
				</option>
			</c:forEach>
		</select>
		<p>City:</p>
		<input type="text" name="city" value="${ userData.currentEmployee.address != null ? userData.currentEmployee.address.city : '' }"/>
		<p>Street:</p>
		<input type="text" name="street" value="${ userData.currentEmployee.address != null ? userData.currentEmployee.address.street : '' }"/>
		<br><a href="${ pageContext.request.contextPath }/GeneralController?action=editPersonal" class="action">Edit personal info</a>
		<c:if test="${not empty userData.currentEmployee.projects}">
			<br><br>Projects:
			<c:forEach items="${userData.currentEmployee.projects}" var="project">
				<br><input type="text" value="${ project.name }" disabled="disabled"/>
			</c:forEach>
		</c:if>
		<a href="${ pageContext.request.contextPath }/GeneralController?action=selectProject" class="action">Add project</a>
		<c:if test="${empty userData.currentEmployee.unit}">
			<a href="${ pageContext.request.contextPath }/GeneralController?action=selectUnit" class="action">Select unit</a>
		</c:if>
		<c:if test="${not empty userData.currentEmployee.unit}">
			<br><br>Unit:
			<br><input type="text" value="${ userData.currentEmployee.unit.name }" disabled="disabled"/>
			<a href="${ pageContext.request.contextPath }/GeneralController?action=selectUnit" class="action">Change unit</a>
		</c:if>
		<br><input type="submit" value="Save"/>
		<br><a href="${ pageContext.request.contextPath }/GeneralController?action=viewEmployees" class="action">Back</a>
	</form>
</body>
</html>