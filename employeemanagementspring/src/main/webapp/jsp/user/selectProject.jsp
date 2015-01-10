<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h3>List of projects</h3>
	
		<table class="table table-striped">
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Action</th>
			</tr>
			<c:if test="${!empty projectList}">
				<c:forEach items="${projectList}" var="project">
				<tr>
					<td>${project.projectId}</td>
					<td>${project.name}</td>
					<td>
						<form:form action="${ pageContext.request.contextPath }/employee/project/select" method="POST">
							<input type="hidden" name="projectId" value="${project.projectId}" />
							<c:set var="isAdded" value="false"/>
							<c:forEach items="${addedProjectList}" var="addedProject">
								<c:if test="${project.projectId == addedProject.projectId}">
									<c:set var="isAdded" value="true"/>
								</c:if>
							</c:forEach>
							<c:if test="${isAdded}">
								Added
							</c:if>
							<c:if test="${!isAdded}">
								<input type="submit" value="Add" class="btn btn-default" />
							</c:if>
						</form:form>
					</td>
				</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty projectList}">
				<tr><td colspan="3"> list is empty </td></tr>
			</c:if>
		</table>

	<nav>
		<ul class="pagination">
			<c:forEach var="i" begin="1" end="${pagesCount}">
				<li class="${page == i - 1 ? 'disabled' : 'enabled' }">
					<a href="${ pageContext.request.contextPath }/employee/project/select?page=${i}" class="action">${i}</a>
				</li>
			</c:forEach>
		</ul>
	</nav>

	<form:form action="${ pageContext.request.contextPath }/employee/${currentEmployee.employeeId}" method="POST">
		<input type="submit" value="Back" class="btn btn-info"/>
	</form:form>