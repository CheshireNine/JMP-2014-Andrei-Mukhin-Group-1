<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

	<form:form method="POST" action="${ pageContext.request.contextPath }/employee" commandName="currentEmployee">

		<br><br><div class="col-md-4">First name:</div>
		<div class="col-md-8"><form:input path="firstName"/></div>

		<br><br><div class="col-md-4">Last name:</div>
		<div class="col-md-8"><form:input path="lastName"/></div>

		<br><br><div class="col-md-4">Status:</div>
		<div class="col-md-8"><form:select path="status" items="${statusList}"/></div>

		<br><br><div class="col-md-4">City:</div>
		<div class="col-md-8"><form:input path="address.city"/></div>

		<br><br><div class="col-md-4">Street:</div>
		<div class="col-md-8"><form:input path="address.street"/></div>

		<div class="col-md-12 section-end"><a href="${ pageContext.request.contextPath }/employee/personal" class="btn btn-default">Edit personal info</a></div>
		<c:if test="${not empty currentEmployee.projects}">
			<br><br><div class="col-md-4">Projects:</div>
			<div class="col-md-8">
				<c:forEach items="${currentEmployee.projects}" var="project">
					<br><input type="text" value="${ project.name }" disabled="disabled"/>
				</c:forEach>
			</div>
		</c:if>

		<div class="col-md-12 section-end">
			<a href="${ pageContext.request.contextPath }/employee/project/select?page=1"
				class="btn btn-default">Add project</a>
		</div>

		<c:if test="${empty currentEmployee.unit}">
			<div class="col-md-12 section-end">
				<a href="${ pageContext.request.contextPath }/employee/unit/select?page=1"
					class="btn btn-default">Select unit</a>
			</div>
		</c:if>

		<c:if test="${not empty currentEmployee.unit}">
			<br><br><div class="col-md-4">Unit:</div>
			<div class="col-md-8"><input type="text" value="${ currentEmployee.unit.name }" disabled="disabled"/></div>
			<div class="col-md-12 section-end"><a href="${ pageContext.request.contextPath }/employee/unit/select?page=1" class="btn btn-default">Change unit</a></div>
		</c:if>

		<br><br>
		<div class="btn-group btn-group-lg">
			<a href="${ pageContext.request.contextPath }/employee?page=1" class="btn btn-default">Back</a>
			<input type="submit" value="Save" class="btn btn-default"/>
		</div>
		
	</form:form>