<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
	<title>게시글 목록 | DIARY</title>
	
	<jsp:include page="/WEB-INF/jsp/include/metaHeader.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/jsp/include/cssHeader.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/jsp/include/scriptHeader.jsp"></jsp:include>

</head>

<body>

	<!-- ======= Header ======= -->
	<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
	<!-- End Header -->
	
	<!-- ======= Sidebar ======= -->
	<jsp:include page="/WEB-INF/jsp/include/sidebar.jsp"></jsp:include>
	<!-- End Sidebar-->
	
	<main id="main" class="main">
    	<section class="section">
    		<div class="container mt-5">
			<div class="row">
				<div class="col-lg-12">
					<div class="card">
						<div class="card-body">
							<h5 class="card-title">게시글 조회</h5>
							<form id="defaultForm">
								<p>
								<input type="hidden" id="thePageNum" name="pageNum" value="${pagingCreator.pageNum}">
								<select id="theRowAmountPerPage" name="rowAmountPerPage">
									<option value="10" ${pagingCreator.rowAmountPerPage == '10' ? 'selected' : ''}>10개</option>
									<option value="20" ${pagingCreator.rowAmountPerPage == '20' ? 'selected' : ''}>20개</option>
									<option value="50" ${pagingCreator.rowAmountPerPage == '50' ? 'selected' : ''}>50개</option>
									<option value="100" ${pagingCreator.rowAmountPerPage == '100' ? 'selected' : ''}>100개</option>
								</select>
								</p>
								<input type="hidden" id="theNotePostSno" name="notePostSno" value="${requestMap.notePostSno}">
							</form>
							<form id="searchForm">
							<input type="hidden" id="pageNum" name="pageNum" value="1">
							<input type="hidden" id="rowAmountPerPage" name="rowAmountPerPage" value="${pagingCreator.rowAmountPerPage}">
							<div class="row">
								<div class="col-md-3">
									<div class="input-group mb-3">
									    <span class="input-group-text">No</span>
									    <input type="text" class="form-control" id="notePostSno" name="notePostSno" value="${requestMap.notePostSno}" autocomplete="off">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="text-end">
									    <button type="button" class="btn btn-sm btn-primary" id="btnKeywordSearch">검색</button>
									    <button type="button" class="btn btn-sm btn-secondary" id="btnKeywordReset">초기화</button>
									</div>
								</div>
							</div>
							</form> <!-- // END searchForm  -->
							<div>
								(총 ${pagingCreator.count}개, page ${pagingCreator.pageNum} of ${pagingCreator.lastPageNum})
							</div>
							<table class="table mt-3">
								<colgroup>
									<col style="width: 5%;"/>
									<col style="width: 15%;"/>
									<col style="width: auto;"/>
									<col style="width: 15%;"/>
									<col style="width: 15%;"/>
								</colgroup>
								<thead>
									<tr>
										<th scope="col" class="text-center"></th>
										<th scope="col" class="text-center">No</th>
										<th scope="col" class="text-center">제목</th>
										<th scope="col" class="text-center">작성자</th>
										<th scope="col" class="text-center">작성일</th>
									</tr>
								</thead>
		
								<tbody id="tbody">
									<c:choose>
										<c:when test="${pagingCreator.count == 0}">
											<tr>
							                    <td colspan="5" class="text-center">조회된 내용이 없습니다</td>
							                </tr>
										</c:when>
										<c:otherwise>
										<c:forEach var="np" varStatus="npStatus" items="${npList}">
											<tr>
												<td class="text-center" >
													<c:choose>
							                    		<c:when test="${np.notePostPtDtcd == '01'}"><span class="badge rounded-pill bg-secondary">일상</span></c:when>
							                    		<c:when test="${np.notePostPtDtcd == '02'}"><span class="badge rounded-pill bg-secondary">식단</span></c:when>
							                    		<c:when test="${np.notePostPtDtcd == '03'}"><span class="badge rounded-pill bg-primary">운동</span></c:when>
							                    	</c:choose>
												</td>
												<td class="text-center" >
													<c:out value="${np.notePostSno}" />
												</td>
												<td class="text-center">
													<a class="title-link" href="javascript:gotoURL('DIANP04?notePostSno=${np.notePostSno}');">
														<c:out value="${np.notePostTit}" />
													</a>
												</td>
												<td class="text-center" >
													<c:out value="${np.sysModifier}" />
												</td>
												<td class="text-center" >
													<c:out value="${np.sysCreatedAt.substring(0, 10) == today ? np.sysCreatedAt.substring(10, 16) : np.sysCreatedAt.substring(0, 10)}" />
												</td>
											</tr>
		
										</c:forEach>	
										
										</c:otherwise>
									</c:choose>
								</tbody>	
							</table>
						</div>
					</div>
		
		
		
				</div>
			</div>
			<!-- ======= pagingCreator ======= -->
			<jsp:include page="/WEB-INF/jsp/include/pagingCreator.jsp"></jsp:include>
			<!-- End pagingCreator-->
			<div class="row mt-5 mb-5">
		   		<div class="col-3">
					<button type="button" class="btn btn-sm btn-secondary" id="btnBoardList" onclick="gotoURL('DIANP03');">전체글</button>
				</div>
				<div class="col-9 text-end">
					<button type="button" class="btn btn-sm btn-primary" id="btnRegister" onclick="gotoURL('DIANP01');">글쓰기</button>
				</div>
			</div>
			</div>
		</section>

    </main><!-- End #main -->

   
	
	<script>
	

		$(document).ready(function() {
			

			$("#theRowAmountPerPage").change(function() {
				
				$("#thePageNum").val(1)
				$('#defaultForm').attr('method', 'POST')
				formSubmit($('#defaultForm'), 'DIANP03');
			})


			// 검색 이벤트
			$("#btnKeywordSearch").click(function() {
				$("#pageNum").val(1)
				$('#searchForm').attr('method', 'POST')
				formSubmit($('#searchForm'), 'DIANP03');
			})
			
			// 검색조건 초기화 이벤트
			$("#btnKeywordReset").click(function() {
				$("#notePostSno").val('');
			})
			
			

		});
		
		// 페이징 처리
		function goToPaging(pageNum) {
			$("#thePageNum").val(pageNum)
			$('#defaultForm').attr('method', 'POST')
			formSubmit($('#defaultForm'), 'DIANP03');
		}
		
	</script>


	<jsp:include page="/WEB-INF/jsp/include/scriptBody.jsp"></jsp:include>

	 <!-- ======= Footer ======= -->
    <jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>
	<!-- End Footer -->
	
</body>

</html>