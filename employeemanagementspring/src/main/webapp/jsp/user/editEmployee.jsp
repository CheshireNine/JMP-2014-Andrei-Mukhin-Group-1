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

		<div class="col-md-12 section-end"><a href="${ pageContext.request.contextPath }/employee/personal" class="btn btn-default">Edit personal info</a></div>
		<c:if test="${not empty currentEmployee.projects}">
			<br><br>
			<div class="col-md-12">
				<div class="col-md-4">Projects:</div>
				<div class="col-md-4">
					<c:forEach items="${currentEmployee.projects}" var="project">
						<br><c:out value="${ project.name }"/>
					</c:forEach>
				</div>
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
			<br><br>
			<div class="col-md-12">
				<div class="col-md-4">Unit:</div>
				<div class="col-md-4"><c:out value="${ currentEmployee.unit.name }" /></div>
			</div>
			<div class="col-md-12 section-end"><a href="${ pageContext.request.contextPath }/employee/unit/select?page=1" class="btn btn-default">Change unit</a></div>
		</c:if>

		<br><br>
		<div class="col-md-12 btn-group btn-group-lg">
			<a href="${ pageContext.request.contextPath }/employee?page=1" class="btn btn-default">Back</a>
			<input type="submit" value="Save" class="btn btn-default"/>
		</div>
		
	</form:form>