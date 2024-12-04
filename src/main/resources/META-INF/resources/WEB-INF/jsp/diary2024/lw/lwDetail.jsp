<%@page import="org.slf4j.MDC"%>
<%@page import="com.custom.dia.cmmn.utils.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<title><%= StringUtils.NVL(MDC.get("identifier"), "") %> | DIARY</title>
	
	<jsp:include page="/WEB-INF/jsp/include/metaHeader.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/jsp/include/cssHeader.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/jsp/include/scriptHeader.jsp"></jsp:include>

</head>

<body>
	<%
		request.setAttribute("aesKeyValidYn", StringUtils.NVL((String) session.getAttribute("aesKeyValidYn"), ""));
	%>

	<!-- ======= Header ======= -->
	<jsp:include page="/WEB-INF/jsp/include/header.jsp"></jsp:include>
	<!-- End Header -->
	
	<!-- ======= Sidebar ======= -->
	<jsp:include page="/WEB-INF/jsp/include/sidebar.jsp"></jsp:include>
	<!-- End Sidebar-->
	
	<main id="main" class="main">
        <div class="card">
			<div class="card-header">
                <div class="row">
                    <div class="col-9">
                    	<c:choose>
                    		<c:when test="${lw.lgwkNotePostPtDtcd == '01'}"><span class="badge rounded-pill bg-secondary">연재</span></c:when>
                    	</c:choose>
                    	<span>|</span>
                        <span class="card-title">${lw.lgwkNotePostTit}</span>
                    </div>
                    <div class="col-3 text-end">
                    	<strong class="text-body">${lw.sysModifier}</strong>
                    	<span>|</span>
                        <span class="text-body">${lw.sysModifiedAt}</span>
                        <c:choose>
                        	<c:when test="${lw.sysModifiedAt > lw.sysCreatedAt}">
                        		<span>(수정됨)</span>
                        	</c:when>
                        </c:choose>
                    	
                    </div>
                </div>
            </div>
		    <div class="card-body">
		        <p class="card-text">
		            ${lw.lgwkNotePostDesc}
		        </p>
		    </div>
		</div>
		
	   	<div class="row mt-5 mb-5">
	   		<div class="col-3">
				<button type="button" class="btn btn-sm btn-secondary" id="btnBoardList" onclick="gotoURL('DIALW03');">전체글</button>
			</div>
			<div class="col-9 text-end">
				<c:choose>
				<c:when test="${aesKeyValidYn == '1'}">
				<button type="button" class="btn btn-sm btn-secondary" id="btnModify" onclick="gotoURL('DIALW05?lgwkNotePostSno=${lw.lgwkNotePostSno}');">수정</button>
				<button type="button" class="btn btn-sm btn-secondary" id="btnRemove">삭제</button>
				<button type="button" class="btn btn-sm btn-primary" id="btnRegister" onclick="gotoURL('DIALW01');">글쓰기</button>
				</c:when>
				<c:otherwise>
				<button type="button" class="btn btn-sm btn-primary" id="btnRegister" onclick="gotoURL('DIALW01');">글쓰기</button>
				</c:otherwise>
				</c:choose>
			</div>
		</div>
		
		<div class="row">
				<div class="col-lg-12">
					<div class="card">
						<div class="card-body">
							<h5 class="card-title">[${lw.lgwkNotePostTit}] 장편게시글 상세조회</h5>
							<form id="defaultForm">
							<p>
								<input type="hidden" id="thePageNum" name="pageNum" value="${pagingCreator.pageNum}">
								<select id="theRowAmountPerPage" name="rowAmountPerPage">
									<option value="10" ${pagingCreator.rowAmountPerPage == '10' ? 'selected' : ''}>10개</option>
									<option value="20" ${pagingCreator.rowAmountPerPage == '20' ? 'selected' : ''}>20개</option>
									<option value="50" ${pagingCreator.rowAmountPerPage == '50' ? 'selected' : ''}>50개</option>
									<option value="100" ${pagingCreator.rowAmountPerPage == '100' ? 'selected' : ''}>100개</option>
								</select>
								<select id="theOrder" name="order">
									<option value="01" ${requestMap.order == '01' or requestMap.order eq null ? 'selected' : ''}>가장 최신순</option>
									<option value="02" ${requestMap.order == '02' ? 'selected' : ''}>가장 오래된순</option>
								</select>
								<input type="hidden" id="theLgwkNotePostSno" name="lgwkNotePostSno" value="${lw.lgwkNotePostSno}">
							</p>
							</form>
							<div class="row">
								<div class="col-md-12">
									<div class="text-end">
									    <button type="button" class="btn btn-sm btn-primary" id="btnWrite" onclick="gotoURL('DIALW08?lgwkNotePostSno=${lw.lgwkNotePostSno}');">
									    <c:choose>
				                    		<c:when test="${lw.lgwkNotePostPtDtcd == '01'}">연재</c:when>
				                    	</c:choose>
									    </button>
									</div>
								</div>
							</div>
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
										<c:forEach var="lwDtl" varStatus="lwStatus" items="${lwDtlList}">
											<tr>
												<td class="text-center" >
													<c:choose>
							                    		<c:when test="${lwDtl.lgwkNotePostDtlPtDtcd == '01'}"><span class="badge rounded-pill bg-secondary">회차</span></c:when>
							                    	</c:choose>
												</td>
												<td class="text-center" >
													<c:out value="${lwDtl.lgwkNotePostDtlSno}" />
												</td>
												<td class="text-center">
													<a class="title-link" href="javascript:gotoURL('DIALW10?lgwkNotePostSno=${lwDtl.lgwkNotePostSno}&lgwkNotePostDtlSno=${lwDtl.lgwkNotePostDtlSno}');">
														<c:out value="${lwDtl.lgwkNotePostDtlTit}" />
													</a>
												</td>
												<td class="text-center" >
													<c:out value="${lwDtl.sysModifier}" />
												</td>
												<td class="text-center" >
													<c:out value="${lwDtl.sysCreatedAt.substring(0, 10) == today ? lwDtl.sysCreatedAt.substring(10, 16) : lw.sysCreatedAt.substring(0, 10)}" />
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
		
            
	</main><!-- End #main -->
	
	<!-- ======= Footer ======= -->
	<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>
	
	
	<script>
	
		$(document).ready(function () {
			$('#btnRemove').click(function() {
				alertUtils.showConfirm('게시글을 삭제하시겠습니까?', function() {
			    	var requestMap = {
		    			lgwkNotePostSno: '${lw.lgwkNotePostSno}'
			    	};
			    	
			    	ajax('DIALW06', requestMap, function(response) {
			    		if (response.header && response.header.status == '0000') {
			    			alertUtils.showAlert('삭제되었습니다', function() {
			    				gotoURL('DIALW03');
			    			});
			    		} else {
			    			alertUtils.showAlert(response.header.errorMsg);
			    		}
			    	});
		    		
		    	});
			});
			
			$("#theRowAmountPerPage").change(function() {
				
				$("#thePageNum").val(1);
				$('#defaultForm').attr('method', 'POST');
				formSubmit($('#defaultForm'), 'DIALW04');
			});
			
			$("#theOrder").change(function() {
				$("#thePageNum").val(1);
				$('#defaultForm').attr('method', 'POST');
				formSubmit($('#defaultForm'), 'DIALW04');
			});
			
		});
	
	
	
	</script>


	<jsp:include page="/WEB-INF/jsp/include/scriptBody.jsp"></jsp:include>

</body>

</html>