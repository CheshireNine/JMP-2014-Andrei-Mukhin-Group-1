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
	
	<p>current bank: ${userData.currentBank.name}</p>
	<a href="${ pageContext.request.contextPath }/GeneralController?service=account&action=viewAll" class="action">View Accounts</a>
	<a href="${ pageContext.request.contextPath }/GeneralController?service=currency&action=fetch&bankId=${userData.currentBank.bankId}" class="action">View Currencies</a>
	<a href="${ pageContext.request.contextPath }/GeneralController?service=person&action=viewAll" class="action">View Persons</a>
	<a href="${ pageContext.request.contextPath }/GeneralController?service=bank&action=viewAll" class="action">Back</a>
</body>
</html>