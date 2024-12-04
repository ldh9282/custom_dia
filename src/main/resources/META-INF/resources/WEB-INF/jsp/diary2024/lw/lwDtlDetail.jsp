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
                    		<c:when test="${lwDtl.lgwkNotePostDtlPtDtcd == '01'}"><span class="badge rounded-pill bg-secondary">회차</span></c:when>
                    	</c:choose>
                    	<span>|</span>
                        <span class="card-title">${lwDtl.lgwkNotePostDtlTit}</span>
                    </div>
                    <div class="col-3 text-end">
                    	<strong class="text-body">${lwDtl.sysModifier}</strong>
                    	<span>|</span>
                        <span class="text-body">${lwDtl.sysModifiedAt}</span>
                        <c:choose>
                        	<c:when test="${lwDtl.sysModifiedAt > lwDtl.sysCreatedAt}">
                        		<span>(수정됨)</span>
                        	</c:when>
                        </c:choose>
                    	
                    </div>
                </div>
            </div>
		    <div class="card-body">
		        <p class="card-text">
		            ${lwDtl.lgwkNotePostDtlCtt}
		        </p>
		    </div>
		</div>
		
	   	<div class="row mt-5 mb-5">
	   		<div class="col-3">
				<button type="button" class="btn btn-sm btn-secondary" id="btnBoardList" onclick="gotoURL('DIALW04?lgwkNotePostSno=${lwDtl.lgwkNotePostSno}');">전체글</button>
			</div>
			<div class="col-9 text-end">
				<c:choose>
				<c:when test="${aesKeyValidYn == '1'}">
				<button type="button" class="btn btn-sm btn-secondary" id="btnModify" onclick="gotoURL('DIALW11?lgwkNotePostSno=${lwDtl.lgwkNotePostSno}&lgwkNotePostDtlSno=${lwDtl.lgwkNotePostDtlSno}');">수정</button>
				<button type="button" class="btn btn-sm btn-secondary" id="btnRemove">삭제</button>
				<button type="button" class="btn btn-sm btn-primary" id="btnRegister" onclick="gotoURL('DIALW08?lgwkNotePostSno=${lwDtl.lgwkNotePostSno}');">
				<c:choose>
                <c:when test="${lwDtl.lgwkNotePostPtDtcd == '01'}">연재</c:when>
                </c:choose>
				</button>
				</c:when>
				<c:otherwise>
				<button type="button" class="btn btn-sm btn-primary" id="btnRegister" onclick="gotoURL('DIALW08?lgwkNotePostSno=${lwDtl.lgwkNotePostSno}');">
				<c:choose>
                <c:when test="${lwDtl.lgwkNotePostPtDtcd == '01'}">연재</c:when>
                </c:choose>
				</button>
				</c:otherwise>
				</c:choose>
			</div>
		</div>
		
		
            
	</main><!-- End #main -->
	
	<!-- ======= Footer ======= -->
	<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>
	
	
	<script>
	
		$(document).ready(function () {
			$('#btnRemove').click(function() {
				alertUtils.showConfirm('회차를 삭제하시겠습니까?', function() {
			    	var requestMap = {
		    			lgwkNotePostSno: '${lwDtl.lgwkNotePostSno}'
		    			, lgwkNotePostDtlSno: '${lwDtl.lgwkNotePostDtlSno}'
			    	};
			    	
			    	ajax('DIALW12', requestMap, function(response) {
			    		if (response.header && response.header.status == '0000') {
			    			alertUtils.showAlert('삭제되었습니다', function() {
			    				gotoURL('DIALW04?lgwkNotePostSno=${lwDtl.lgwkNotePostSno}');
			    			});
			    		} else {
			    			alertUtils.showAlert(response.header.errorMsg);
			    		}
			    	});
		    		
		    	});
			});
		
		});
	
	
	
	</script>


	<jsp:include page="/WEB-INF/jsp/include/scriptBody.jsp"></jsp:include>

</body>

</html>