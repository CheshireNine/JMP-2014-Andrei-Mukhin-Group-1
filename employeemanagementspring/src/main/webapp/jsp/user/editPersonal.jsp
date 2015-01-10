<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

	<br><br><div class="col-md-4">Employee:</div>
	<input type="text" value="${ currentEmployee.firstName } ${ currentEmployee.lastName }" disabled="disabled" />
	<form:form method="POST" action="${ pageContext.request.contextPath }/employee/personal" commandName="currentEmployee">

		<br><br><div class="col-md-4">Phone number:</div>
		<form:input path="personalInfo.phoneNumber" />

		<br><br><div class="col-md-4">Email:</div>
		<form:input path="personalInfo.email" />

		<br><br><div class="col-md-12">Education:</div>
		<div class="col-md-12">
			<form:textarea path="personalInfo.education" rows="20" cols="65" />
		</div>
		<br><br><div class="col-md-12"><input type="submit" value="Accept" class="btn btn-default" /></div>

	</form:form>