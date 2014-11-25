<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bank's Internal System</title>
</head>
<body>
	<h3>List of banks</h3>
	
		<table>
			<tr>
				<th>Name</th>
				<th colspan="3"><a href="<%=request.getContextPath()%>/GeneralController?action=createBank">Create</a></th>
			</tr>
			<c:if test="${!empty banksList}">
				<c:forEach items="${banksList}" var="bank">
				<tr>
					<td>${bank.name}</td>
					<td><a href="<%=request.getContextPath()%>/GeneralController?action=viewBank&id=${bank.bankId}">View</a></td>
					<td><a href="<%=request.getContextPath()%>/GeneralController?action=editBank&id=${bank.bankId}">Edit</a></td>
					<td><a href="<%=request.getContextPath()%>/GeneralController?action=deleteBank&id=${bank.bankId}">Delete</a></td>
				</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty banksList}">
				<tr><td colspan="4"> list is empty </td></tr>
			</c:if>
		</table>
</body>
</html>