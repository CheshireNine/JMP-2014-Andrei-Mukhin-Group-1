<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="row">
	<form action="${ pageContext.request.contextPath }/employee?find=ByName&page=1" method="POST">
		<div class="col-lg-6">
			<div class="input-group">
				<input type="text" name="searchValue" value="" class="form-control" placeholder="Search for employee with last name..." />
				<span class="input-group-btn">
					<button class="btn btn-default" type="submit">Go!</button>
				</span>
			</div>
		</div>
	</form>
</div>

<br><br><a href="${ pageContext.request.contextPath }/employee?page=1" class="btn btn-default">Back</a>