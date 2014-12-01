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
	<h3>List of currencies</h3>
	
		<table>
			<tr>
				<th colspan="1">Name</th>
				<th colspan="1">Bank Name</th>
				<th colspan="1">Rate</th>
				<th colspan="1">Precision</th>
				<th>Action</th>
			</tr>
			<c:if test="${!empty currenciesList}">
				<c:forEach items="${currenciesList}" var="currency">
				<tr>
					<td>${currency.name}</td>
					<c:if test="${empty currency.bank}">
						<td>No bank assigned</td>
					</c:if>
					<c:if test="${!empty currency.bank}">
						<td>${currency.bank.name}</td>
					</c:if>
					<td>${currency.rate}</td>
					<td>${currency.precision}</td>
					<c:if test="${!empty userData.currentAccount.currency}">
						<td><a href="${ pageContext.request.contextPath }/GeneralController?service=currency&action=performSelectTarget&currencyId=${currency.currencyId}" class="action">Select</a></td>
					</c:if>
					<c:if test="${empty userData.currentAccount.currency}">
						<td><a href="${ pageContext.request.contextPath }/GeneralController?service=currency&action=performSelect&currencyId=${currency.currencyId}" class="action">Select</a></td>
					</c:if>
				</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty banksList}">
				<tr><td colspan="4"> list is empty </td></tr>
			</c:if>
		</table>
</body>
</html>