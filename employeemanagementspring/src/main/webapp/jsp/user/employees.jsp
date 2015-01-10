<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<h3>List of employees</h3>
	
		<table class="table table-striped">
			<tr>
				<th>Id</th>
				<th colspan="2">Name</th>
				<th>Status</th>
				<th>City</th>
				<th>Street</th>
				<th>Unit</th>
				<th colspan="2"><a href="${ pageContext.request.contextPath }/employee/add" class="btn btn-info stretch">Create</a></th>
				<th><a href="${ pageContext.request.contextPath }/employee/find" class="btn btn-info stretch">Find</a></th>
			</tr>
			<c:if test="${!empty employeeList}">
				<c:forEach items="${employeeList}" var="employee">
				<tr>
					<td>${employee.employeeId}</td>
					<td>${employee.firstName}</td>
					<td>${employee.lastName}</td>
					<td>${employee.status.status}</td>
					<td>${employee.address.street}</td>
					<td>${employee.address.city}</td>
					<c:if test="${!empty employee.unit}">
						<td>${employee.unit.name}</td>
					</c:if>
					<c:if test="${empty employee.unit}">
						<td>no unit assigned</td>
					</c:if>
					<td><a href="${ pageContext.request.contextPath }/employee/${employee.employeeId}" class="btn btn-info stretch">View</a></td>
					<td>
						<form:form action="${ pageContext.request.contextPath }/employee/${employee.employeeId}" method="POST">
							<input type="submit" value="Edit" class="btn btn-info stretch"/>
						</form:form>
					</td>
					<td>
						<form:form action="${ pageContext.request.contextPath }/employee/${employee.employeeId}" method="DELETE">
							<input type="submit" value="Delete"
								onclick="return confirm('Are you sure you want to delete employee ${employee.firstName} ${employee.lastName}?')"
								class="btn btn-danger stretch" />
						</form:form>
					</td>
				</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty employeeList}">
				<tr><td colspan="4"> list is empty </td></tr>
			</c:if>
		</table>
	<nav>
		<ul class="pagination">
			<c:forEach var="i" begin="1" end="${pagesCount}">
				<li class="${page == i - 1 ? 'disabled' : 'enabled' }">
					<a href="${ pageContext.request.contextPath }/employee?page=${i}" class="action">${i}</a>
				</li>
			</c:forEach>
		</ul>
	</nav>
	<a href="${ pageContext.request.contextPath }/" class="btn btn-default">Back</a>