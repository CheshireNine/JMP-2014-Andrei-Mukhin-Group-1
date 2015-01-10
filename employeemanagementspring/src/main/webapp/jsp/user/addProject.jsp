<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

	<form:form method="POST" action="${ pageContext.request.contextPath }/project" commandName="currentProject">
		<br><br><div class="col-md-4">Project name:</div>
		<div class="col-md-8"><form:input path="name" /></div>
		<br><br>
		<div class="btn-group btn-group-lg">
			<a href="${ pageContext.request.contextPath }/project?page=1" class="btn btn-default">Back</a>
			<input type="submit" value="Save" class="btn btn-default"/>
		</div>
	</form:form>