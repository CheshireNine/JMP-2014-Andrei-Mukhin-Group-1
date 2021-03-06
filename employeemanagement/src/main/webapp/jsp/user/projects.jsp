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
	<h3>List of projects</h3>
	
		<table>
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th colspan="3"><a href="${ pageContext.request.contextPath }/GeneralController?action=addProject" class="action">Create</a></th>
			</tr>
			<c:if test="${!empty projectList}">
				<c:forEach items="${projectList}" var="project">
				<tr>
					<td>${project.projectId}</td>
					<td>${project.name}</td>
					<td><a href="${ pageContext.request.contextPath }/GeneralController?action=viewProject&id=${project.projectId}" class="action">View</a></td>
					<td><a href="${ pageContext.request.contextPath }/GeneralController?action=editProject&id=${project.projectId}" class="action">Edit</a></td>
					<td><a href="${ pageContext.request.contextPath }/GeneralController?action=deleteProject&id=${project.projectId}" class="action">Delete</a></td>
				</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty projectList}">
				<tr><td colspan="4"> list is empty </td></tr>
			</c:if>
		</table>
	<a href="${ pageContext.request.contextPath }/GeneralController?action=initUserData" class="action">Back</a>
</body>
</html>