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
                    		<c:when test="${np.notePostPtDtcd == '01'}"><span class="badge rounded-pill bg-secondary">일상</span></c:when>
                    		<c:when test="${np.notePostPtDtcd == '02'}"><span class="badge rounded-pill bg-secondary">식단</span></c:when>
                    		<c:when test="${np.notePostPtDtcd == '03'}"><span class="badge rounded-pill bg-primary">운동</span></c:when>
                    	</c:choose>
                    	<span>|</span>
                        <span class="card-title">${np.notePostTit}</span>
                    </div>
                    <div class="col-3 text-end">
                    	<strong class="text-body">${np.sysModifier}</strong>
                    	<span>|</span>
                        <span class="text-body">${np.sysModifiedAt}</span>
                        <c:choose>
                        	<c:when test="${np.sysModifiedAt > np.sysCreatedAt}">
                        		<span>(수정됨)</span>
                        	</c:when>
                        </c:choose>
                    	
                    </div>
                </div>
            </div>
		    <div class="card-body">
		        <p class="card-text">
		            ${np.notePostCtt}
		        </p>
		    </div>
		</div>
            
	   	<div class="row mt-5 mb-5">
	   		<div class="col-3">
				<button type="button" class="btn btn-sm btn-secondary" id="btnBoardList" onclick="gotoURL('DIANP03');">전체글</button>
			</div>
			<div class="col-9 text-end">
				<c:choose>
				<c:when test="${aesKeyValidYn == '1'}">
				<button type="button" class="btn btn-sm btn-secondary" id="btnModify" onclick="gotoURL('DIANP05?notePostSno=${np.notePostSno}');">수정</button>
				<button type="button" class="btn btn-sm btn-secondary" id="btnRemove">삭제</button>
				<button type="button" class="btn btn-sm btn-primary" id="btnRegister" onclick="gotoURL('DIANP01');">글쓰기</button>
				</c:when>
				<c:otherwise>
				<button type="button" class="btn btn-sm btn-primary" id="btnRegister" onclick="gotoURL('DIANP01');">글쓰기</button>
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
				alertUtils.showConfirm('게시글을 삭제하시겠습니까?', function() {
			    	var requestMap = {
		    			notePostSno: '${np.notePostSno}'
			    	};
			    	
			    	ajax('DIANP06', requestMap, function(response) {
			    		if (response.header && response.header.status == '0000') {
			    			alertUtils.showAlert('삭제되었습니다', function() {
			    				gotoURL('DIANP03');
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