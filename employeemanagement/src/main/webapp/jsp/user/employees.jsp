<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${ pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css"/>
<title>Employees Management</title>
</head>
<body>
	<h3>List of employees</h3>
	
		<table>
			<tr>
				<th>Id</th>
				<th colspan="2">Name</th>
				<th>Status</th>
				<th>City</th>
				<th>Street</th>
				<th>Unit</th>
				<th colspan="2"><a href="${ pageContext.request.contextPath }/GeneralController?action=addEmployee" class="action">Create</a></th>
				<th><a href="${ pageContext.request.contextPath }/GeneralController?action=findEmployee" class="action">Find</a></th>
			</tr>
			<c:if test="${!empty employeeList}">
				<c:forEach items="${employeeList}" var="employee">
				<tr>
					<td>${employee.employeeId}</td>
					<td>${employee.firstName}</td>
					<td>${employee.lastName}</td>
					<td>${employee.status.status}</td>
					<td>${employee.address.street}</td>
					<td>${employee.address.city}</td>
					<c:if test="${!empty employee.unit}">
						<td>${employee.unit.name}</td>
					</c:if>
					<c:if test="${empty employee.unit}">
						<td>no unit assigned</td>
					</c:if>
					<td><a href="${ pageContext.request.contextPath }/GeneralController?action=viewEmployee&id=${employee.employeeId}" class="action">View</a></td>
					<td><a href="${ pageContext.request.contextPath }/GeneralController?action=editEmployee&id=${employee.employeeId}" class="action">Edit</a></td>
					<td><a href="${ pageContext.request.contextPath }/GeneralController?action=deleteEmployee&id=${employee.employeeId}" class="action">Delete</a></td>
				</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty employeeList}">
				<tr><td colspan="4"> list is empty </td></tr>
			</c:if>
		</table>
	<a href="${ pageContext.request.contextPath }/GeneralController?action=initUserData" class="action">Back</a>
</body>
</html>