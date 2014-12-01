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
<p>Amount:</p>
<p>${userData.currentAccount.amount}</p>
<p>Owner:</p>
<c:if test="${!empty userData.currentAccount.owner}">
	<p>${userData.currentAccount.owner.firstName} ${userData.currentAccount.owner.lastName}"</p>
</c:if>
<c:if test="${empty userData.currentAccount.owner}">
	<p>no owner</p>
</c:if>
<c:if test="${!empty userData.currentAccount.currency}">
	<p>${userData.currentAccount.currency.name}</p>
	<p>${userData.currentAccount.currency.rate}</p>
	<p>${userData.currentAccount.currency.precision}</p>
	<c:if test="${empty userData.targetCurrency}">
		<a href="${ pageContext.request.contextPath }/GeneralController?service=currency&action=select" class="action">Select currency for exchange</a>
	</c:if>
	<c:if test="${not empty userData.targetCurrency}">
		<a href="${ pageContext.request.contextPath }/GeneralController?service=account&action=performExchange" class="action">Perform exchange</a>
	</c:if>
</c:if>

<c:if test="${empty userData.currentAccount.currency}">
	<p>no currency</p>
</c:if>
<a href="${ pageContext.request.contextPath }/GeneralController?service=account&action=viewAll" class="action">Back</a>
</body>
</html>