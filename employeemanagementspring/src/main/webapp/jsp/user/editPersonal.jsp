<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

	<br><br>
	<div class="col-md-12">
		<div class="col-md-4">Employee:</div>
		<div class="col-md-4">
			<c:out value="${ currentEmployee.firstName } ${ currentEmployee.lastName }"/>
		</div>
	</div>
	<form:form method="POST" action="${ pageContext.request.contextPath }/employee/personal" commandName="currentEmployee">

		<br><br>
		<div class="col-md-12">
			<div class="col-md-4">Phone number:</div>
			<div class="col-md-4">
				<form:input path="personalInfo.phoneNumber" cssClass="form-control"/>
			</div>
		</div>

		<br><br>
		<div class="col-md-12">
			<div class="col-md-4">Email:</div>
			<div class="col-md-4"><form:input path="personalInfo.email" cssClass="form-control" /></div>

			<form:errors path="personalInfo.email" cssClass="col-md-8 alert alert-danger" />
		</div>

		<br><br>
		<div class="col-md-12">
			<div class="col-md-8">
				<div class="form-group">
					<label for="comment">Education:</label>
					<form:textarea path="personalInfo.education" class="form-control" />
				</div>
			</div>
		</div>

		<br><br><div class="col-md-12"><input type="submit" value="Accept" class="btn btn-default" /></div>

	</form:form>