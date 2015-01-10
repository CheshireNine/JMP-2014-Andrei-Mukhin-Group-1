<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page isELIgnored="false" %>

	<h3>List of units</h3>
	
		<table class="table table-striped">
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th colspan="2"><a href="${ pageContext.request.contextPath }/unit/add" class="btn btn-info stretch">Create</a></th>
				<th><a href="${ pageContext.request.contextPath }/jsp/user/findUnit.jsp" class="btn btn-info stretch">Find</a></th>
			</tr>
			<c:if test="${!empty unitList}">
				<c:forEach items="${unitList}" var="unit">
				<tr>
					<td>${unit.unitId}</td>
					<td>${unit.name}</td>
					<td><a href="${ pageContext.request.contextPath }/GeneralController?action=viewUnit&id=${unit.unitId}" class="btn btn-info stretch">View</a></td>
					<td>
						<form:form action="${ pageContext.request.contextPath }/unit/${unit.unitId}" method="POST">
							<input type="submit" value="Edit" class="btn btn-info stretch"/>
						</form:form>
					</td>
					<td>
						<form:form action="${ pageContext.request.contextPath }/unit/${unit.unitId}" method="DELETE">
							<input type="submit" value="Delete"
								onclick="return confirm('Are you sure you want to delete unit ${unit.name}?')"
								class="btn btn-danger stretch" />
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
					<a href="${ pageContext.request.contextPath }/unit?page=${i}" class="action">${i}</a>
				</li>
			</c:forEach>
		</ul>
	</nav>

	<a href="${ pageContext.request.contextPath }/" class="btn btn-default">Back</a>