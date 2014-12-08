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

	<p><c:out value="First name:"/></p>
	<c:out value="${ userData.currentEmployee.firstName }"/>
	<br><p><c:out value="Last name:"/></p>
	<c:out value="${ userData.currentEmployee.lastName }"/>
		
	<br><p><c:out value="Status:"/></p>
	<c:out value="${  userData.currentEmployee.status.status }"/>

	<br><p><c:out value="City:"/></p>
	<c:out value="${ userData.currentEmployee.address != null ? userData.currentEmployee.address.city : '' }"/>

	<br><p><c:out value="Street:"/></p>
	<c:out value="${ userData.currentEmployee.address != null ? userData.currentEmployee.address.street : '' }"/>

	<c:if test="${not empty userData.currentEmployee.projects}">
		<br><br><c:out value="Projects:"/>
		<c:forEach items="${userData.currentEmployee.projects}" var="project">
			<br><input type="text" value="${ project.name }" disabled="disabled"/>
		</c:forEach>
	</c:if>
	
	<c:if test="${not empty userData.currentEmployee.projects}">
		<br><c:out value="No projects"/>
	</c:if>

	<c:if test="${empty userData.currentEmployee.unit}">
		<br><c:out value="No unit"/>
	</c:if>
	<c:if test="${not empty userData.currentEmployee.unit}">
		<br><br><c:out value="Unit:"/>
		<br><input type="text" value="${ userData.currentEmployee.unit.name }" disabled="disabled"/>
	</c:if>
	<br><a href="${ pageContext.request.contextPath }/GeneralController?action=viewEmployees" class="action">Back</a>
</body>
</html>