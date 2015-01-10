<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

	<form:form method="POST" action="${ pageContext.request.contextPath }/unit" commandName="currentUnit">
		<br><br><div class="col-md-4">Unit name:</div>
		<div class="col-md-8"><form:input path="name" /></div>

		<br><br>
		<div class="btn-group btn-group-lg">
			<a href="${ pageContext.request.contextPath }/unit?page=1" class="btn btn-default">Back</a>
			<input type="submit" value="Save" class="btn btn-default"/>
		</div>
	</form:form>