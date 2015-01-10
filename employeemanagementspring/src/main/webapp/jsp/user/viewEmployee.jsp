<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<br><br><div class="col-md-4">
		<c:out value="First name:"/>
	</div>
	<div class="col-md-8">
		<c:out value="${ currentEmployee.firstName }"/>
	</div>

	<br><br><div class="col-md-4"><c:out value="Last name:"/></div>
	<div class="col-md-8"><c:out value="${ currentEmployee.lastName }"/></div>
		
	<br><br><div class="col-md-4"><c:out value="Status:"/></div>
	<div class="col-md-8"><c:out value="${  currentEmployee.status.status }"/></div>

	<br><br><div class="col-md-4"><c:out value="City:"/></div>
	<div class="col-md-8"><c:out value="${ currentEmployee.address != null ? currentEmployee.address.city : '' }"/></div>

	<br><br><div class="col-md-4"><c:out value="Street:"/></div>
	<div class="col-md-8"><c:out value="${ currentEmployee.address != null ? currentEmployee.address.street : '' }"/></div>

	<c:if test="${not empty currentEmployee.projects}">
		<br><br><div class="col-md-4"><c:out value="Projects:"/></div>
		<div class="col-md-8">
			<c:forEach items="${currentEmployee.projects}" var="project">
				<br><br><input type="text" value="${ project.name }" disabled="disabled"/>
			</c:forEach>
		</div>
	</c:if>
	
	<c:if test="${empty currentEmployee.projects}">
		<br><br><div class="col-md-12"><c:out value="No projects"/></div>
	</c:if>

	<c:if test="${empty currentEmployee.unit}">
		<br><br><div class="col-md-12"><c:out value="No unit"/></div>
	</c:if>
	<c:if test="${not empty currentEmployee.unit}">
		<br><br><div class="col-md-4"><c:out value="Unit:"/></div>
		<div class="col-md-8"><input type="text" value="${ currentEmployee.unit.name }" disabled="disabled"/></div>
	</c:if>
	<br><br><a href="${ pageContext.request.contextPath }/employee?page=1" class="btn btn-default">Back</a>