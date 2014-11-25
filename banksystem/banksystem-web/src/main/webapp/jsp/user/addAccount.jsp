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
<form action="<%=request.getContextPath()%>/GeneralController?action=saveAccount">
<p>Amount:</p>
<input type="text" value="${userData.currentAccount.amount}"/>
<input type="submit" value="Save"/>
</form>
</body>
</html>