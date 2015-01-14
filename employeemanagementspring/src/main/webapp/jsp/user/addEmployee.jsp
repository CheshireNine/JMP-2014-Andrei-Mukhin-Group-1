<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

	<form:form method="POST" action="${ pageContext.request.contextPath }/employee" commandName="currentEmployee">

		<br><br>
		<div class="col-md-12">
			<div class="col-md-4">First name:</div>
			<div class="col-md-4"><form:input path="firstName" class="form-control" /></div>

			<form:errors path="firstName" cssClass="col-md-8 alert alert-danger" />
		</div>

		<br><br>
		<div class="col-md-12">
			<div class="col-md-4">Last name:</div>
			<div class="col-md-4"><form:input path="lastName" class="form-control" /></div>

			<form:errors path="lastName" cssClass="col-md-8 alert alert-danger" />
		</div>

		<br><br>
		<div class="col-md-12">
			<div class="col-md-4">Status:</div>
			<div class="col-md-4"><form:select path="status" items="${statusList}" class="form-control" /></div>

			<form:errors path="status" cssClass="col-md-8 alert alert-danger" />
		</div>

		<br><br>
		<div class="col-md-12">
			<div class="col-md-4">City:</div>
			<div class="col-md-4"><form:input path="address.city" class="form-control" /></div>

			<form:errors path="address.city" cssClass="col-md-8 alert alert-danger" />
		</div>

		<br><br>
		<div class="col-md-12">
			<div class="col-md-4">Street:</div>
			<div class="col-md-4"><form:input path="address.street" class="form-control" /></div>

			<form:errors path="address.street" cssClass="col-md-8 alert alert-danger" />
		</div>

		<br><br>
		<div class="col-md-12 btn-group btn-group-lg">
			<a href="${ pageContext.request.contextPath }/employee?page=1" class="btn btn-default">Back</a>
			<input type="submit" value="Save" class="btn btn-default"/>
		</div>
	</form:form>
