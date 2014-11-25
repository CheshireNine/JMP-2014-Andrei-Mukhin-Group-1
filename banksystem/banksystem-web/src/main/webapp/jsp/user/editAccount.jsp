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
<p>Amount:</p>
<input type="text" value="${userData.currentAccount.amount}"/>
<p>Owner:</p>
<c:if test="${!empty userData.currentAccount.owner}">
	<input type="text" value="${userData.currentAccount.firstName} ${userData.currentAccount.lastName}" disabled="disabled"/>
</c:if>
<c:if test="${empty userData.currentAccount.owner}">
	<p>no owner</p>
</c:if>
<a href="<%=request.getContextPath()%>/GeneralController?action=selectPerson">Select owner</a>
<c:if test="${!empty userData.currentAccount.currency}">
	<input type="text" value="${userData.currentAccount.currency.name}" disabled="disabled"/>
	<input type="text" value="${userData.currentAccount.currency.rate}" disabled="disabled"/>
	<input type="text" value="${userData.currentAccount.currency.precision}" disabled="disabled"/>
</c:if>

<c:if test="${empty userData.currentAccount.currency}">
	<p>no currency</p>
</c:if>
<a href="<%=request.getContextPath()%>/GeneralController?action=selectCurrency">Select currency</a>
</body>
</html>