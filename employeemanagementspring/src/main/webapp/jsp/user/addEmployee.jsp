<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

	<form:form method="POST" action="${ pageContext.request.contextPath }/employee" commandName="currentEmployee">
		<br><br><div class="col-md-4">First name:</div>
		<form:input path="firstName" />

		<br><br><div class="col-md-4">Last name:</div>
		<form:input path="lastName" />

		<br><br><div class="col-md-4">Status:</div>
		<form:select path="status" items="${statusList}"/>

		<br><br><div class="col-md-4">City:</div>
		<form:input path="address.city" />

		<br><br><div class="col-md-4">Street:</div>
		<form:input path="address.street" />

		<br><br>
		<div class="btn-group btn-group-lg">
			<a href="${ pageContext.request.contextPath }/employee?page=1" class="btn btn-default">Back</a>
			<input type="submit" value="Save" class="btn btn-default"/>
		</div>
	</form:form>
