<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

	<h3>List of units</h3>
	
		<table class="table table-striped">
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th colspan="3"><a href="${ pageContext.request.contextPath }/GeneralController?action=addUnit" class="action">Create</a></th>
			</tr>
			<c:if test="${!empty unitList}">
				<c:forEach items="${unitList}" var="unit">
				<tr>
					<td>${unit.unitId}</td>
					<td>${unit.name}</td>
					<td>
						<form:form action="${ pageContext.request.contextPath }/employee/unit/select" method="POST">
							<input type="hidden" name="unitId" value="${unit.unitId}" />
							<c:if test="${ not empty currentEmployee.unit && (unit.unitId == currentEmployee.unit.unitId)}">
								Added
							</c:if>
							<c:if test="${ empty currentEmployee.unit || (unit.unitId != currentEmployee.unit.unitId)}">
								<input type="submit" value="Add" class="btn btn-default" />
							</c:if>
						</form:form>
					</td>
				</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty unitList}">
				<tr><td colspan="4"> list is empty </td></tr>
			</c:if>
		</table>

	<nav>
		<ul class="pagination">
			<c:forEach var="i" begin="1" end="${pagesCount}">
				<li class="${page == i - 1 ? 'disabled' : 'enabled' }">
					<a href="${ pageContext.request.contextPath }/employee/unit/select?page=${i}" class="action">${i}</a>
				</li>
			</c:forEach>
		</ul>
	</nav>
	<form:form action="${ pageContext.request.contextPath }/employee/${currentEmployee.employeeId}" method="POST">
		<input type="submit" value="Back" class="btn btn-info"/>
	</form:form>