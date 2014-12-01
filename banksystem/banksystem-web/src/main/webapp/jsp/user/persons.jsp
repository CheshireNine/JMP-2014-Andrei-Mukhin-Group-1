<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${ pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css"/>
<title>Bank's Internal System</title>
</head>
<body>
	<h3>List of persons</h3>
	
		<table>
			<tr>
				<th colspan="2">Name</th>
				<th colspan="3"><a href="${ pageContext.request.contextPath }/GeneralController?service=person&action=add" class="action">Create</a></th>
			</tr>
			<c:if test="${!empty personsList}">
				<c:forEach items="${personsList}" var="person">
				<tr>
					<td>${person.firstName}</td>
					<td>${person.lastName}</td>
					<td><a href="${ pageContext.request.contextPath }/GeneralController?service=person&action=view&id=${person.personId}" class="action">View</a></td>
					<td><a href="${ pageContext.request.contextPath }/GeneralController?service=person&action=edit&id=${person.personId}" class="action">Edit</a></td>
					<td><a href="${ pageContext.request.contextPath }/GeneralController?service=person&action=delete&id=${person.personId}" class="action">Delete</a></td>
				</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty personsList}">
				<tr><td colspan="4"> list is empty </td></tr>
			</c:if>
		</table>
	<a href="${ pageContext.request.contextPath }/GeneralController?service=bank&action=view&id=${userData.currentBank.bankId}" class="action">Back</a>
</body>
</html>