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
	
	<p>current bank: ${userData.currentBank.name}</p>
	<h3>List of accounts</h3>
		<table>
			<tr>
				<th>Amount</th>
				<th>Owner</th>
				<th>Currency</th>
				<th colspan="3"><a href="<%=request.getContextPath()%>/GeneralController?action=addAccount">Create</a></th>
			</tr>
			<c:if test="${!empty accountsList}">
				<c:forEach items="${accountsList}" var="account">
				<tr>
					<td>${account.amount}</td>
					<td>${account.owner.firstName} ${account.owner.lastName}</td>
					<td>${account.currency.name} ${account.currency.rate} ${account.currency.precision}</td>
					<td><a href="<%=request.getContextPath()%>/GeneralController/account/view?id=${account.accountId}">View</a></td>
					<td><a href="<%=request.getContextPath()%>/GeneralController?action=editAccount&id=${account.accountId}">Edit</a></td>
					<td><a href="<%=request.getContextPath()%>/GeneralController/account/delete?id=${account.accountId}">Delete</a></td>
				</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty accountsList}">
				<tr><td colspan="4"> list is empty </td></tr>
			</c:if>
		</table>
</body>
</html>