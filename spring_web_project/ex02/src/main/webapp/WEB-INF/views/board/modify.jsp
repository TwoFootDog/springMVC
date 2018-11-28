<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="../includes/header.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Board Register</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">Board Red Page</div>
				<div class="panel-body">
					<form role="form" action="/board/modify" method="post">
						<div class="form-group">
							<label>Bno</label> <input class="form-control" name='bno'
								value='<c:out value="${board.bno}" />' readonly="readonly">
						</div>
						<div class="form-group">
							<label>Title</label> <input class="form-control" name='title'
								value='<c:out value="${board.title}" />' >
						</div>
						<div class="form-group">
							<label>Text Area</label>
							<textarea class="form-control" rows="3" name='content'><c:out value="${board.content}" /></textarea>
						</div>
						<div class="form-group">
							<label>Writer</label> <input class="form-control" name='writer'
								value='<c:out value="${board.writer}" />' readonly="readonly">
						</div>
						<div class="form-group">
							<label>regDate</label> <input class="form-control" name='registDate'
								value='<fmt:formatDate pattern = "yyyy/MM/dd" value="${board.registDate}" />' readonly="readonly">
						</div>
						<div class="form-group">
							<label>update Date</label> <input class="form-control" name='updateDate'
								value='<fmt:formatDate pattern = "yyyy/MM/dd" value="${board.updateDate}" />' readonly="readonly">
						</div>
						<button type="submit" data-oper='modify' class="btn btn-default">Modify</button>
						<button type="remove" data-oper='remove' class="btn btn-danger">Remove</button>
						<button type="submit" date-oper='list' class="btn btn-info">List</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="../includes/footer.jsp"%>
</body>
</html>