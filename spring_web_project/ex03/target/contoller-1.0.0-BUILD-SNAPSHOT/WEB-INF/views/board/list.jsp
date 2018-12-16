<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">
	$(document).ready(
			function() {
				var result = '<c:out value="${result}" />';
				checkModal(result);

				function checkModal(result) {
					if (result == '') {
						return;
					}
					if (parseInt(result) > 0) {
						$(".modal-body").html(
								"게시글" + parseInf(result) + " 번이 등록되었습니다.");
					}
					$("#myModal").modal('show');
				}
			});
</script>

<%@ include file="../includes/header.jsp"%>


<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Tables</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Board List Page</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<table width="100%"
					class="table table-striped table-bordered table-hover"
					id="dataTables-example">
					<thread>
					<tr>
						<th>#번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>수정일</th>
					</thread>

					<c:forEach items="${list }" var="board">
						<tr>
							<td><c:out value="${board.bno }" /></td>
							<td><a href="/board/get?bno=<c:out value="${board.bno }" />">
									<c:out value="${board.title }" />
							</a></td>
							<td><c:out value="${board.writer }" /></td>
							<td><fmt:formatDate pattern="yyyy-MM-dd"
									value="${board.registDate }" /></td>
							<td><fmt:formatDate pattern="yyyy-MM-dd"
									value="${board.updateDate }" /></td>
						</tr>
					</c:forEach>
				</table>
				
				<div class='pull-right'>
					<ul class="pagination">
						<c:if test="${pageMaker.prev}">
							<li class="paginate_button previous"><a href="${pageMaker.startPage - 1 }">Previous</a>
						</c:if>
						
						<c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage }">
							<li class="paginate_button ${pageMaker.cri.pageNum == num ? "active":"" }"><a href="${num}">${num}</a></li>
						</c:forEach>
						<c:if test="${pageMaker.next}">
							<li class="paginate_button next"><a href="${pageMaker.endPage + 1 }">Next</a></li>
						</c:if>
					</ul>
				
				
				</div>
				<!-- modal 추가 -->
				<div class="modal" id="myModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="myModalLabel">Modal title</h4>
							</div>
							<div class="modal-body">처리가 완료되었습니다.</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
								<button type="button" class="btn btn-primary">save
									changes</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- /.row -->

<%@ include file="../includes/footer.jsp"%>
