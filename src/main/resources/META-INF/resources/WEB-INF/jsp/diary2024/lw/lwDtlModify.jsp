<%@page import="com.custom.dia.cmmn.utils.StringUtils"%>
<%@page import="org.slf4j.MDC"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<title><%= StringUtils.NVL(MDC.get("identifier"), "") %> | DIARY</title>
	
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
        <div class="mt-5 mb-3">
	        <div class="form-check form-check-inline col-sm-2">
	            <input class="form-check-input" type="radio" name="lgwkNotePostDtlPtDtcd" id="lgwkNotePostDtlPtDtcd01" value="01" ${lwDtl.lgwkNotePostDtlPtDtcd == '01' ? 'checked' : ''}>
	            <label class="form-check-label" for="lgwkNotePostDtlPtDtcd01">회차</label>
	        </div>
        </div>
            
        <!-- 제목 입력 -->
        <div class="mb-3">
            <input type="text" class="form-control" id="lgwkNotePostDtlTit" name="lgwkNotePostDtlTit" placeholder="제목을 입력해주세요" value="${lwDtl.lgwkNotePostDtlTit}" autocomplete="off">
        </div>
		<div id="editor"></div>
		
	   	<div class="row mt-5 mb-5">
			<div class="text-end">
				<button type="button" class="btn btn-sm btn-primary" id="btnModify">수정</button>
				<button type="button" class="btn btn-sm btn-secondary mx-3" id="btnCancle">취소</button>
			</div>
		</div>
	</main><!-- End #main -->
	
	<!-- ======= Footer ======= -->
	<jsp:include page="/WEB-INF/jsp/include/footer.jsp"></jsp:include>
	
	
	<script>
	
		$(document).ready(function () {
		
			editor = new toastui.Editor({
	            el: document.querySelector('#editor'),
	            toolbarItems: [
	                ['heading', 'bold', 'italic', 'strike'],
	                ['ul', 'ol'],
	            ],
	            width: '500px',
	            height: '500px',
	            initialEditType: 'wysiwyg', // 최초로 보여줄 에디터 타입 (markdown || wysiwyg)
	            initialValue: '${fn:replace(lwDtl.lgwkNotePostDtlCtt, "'", "\\'")}',
	            previewStyle: 'tab', // 마크다운 프리뷰 스타일 (tab || vertical)
	            plugins: []
	        });
			
			$('#btnModify').click(function() {
				
				if (!$('input[name=lgwkNotePostDtlPtDtcd]:checked').val()) {
					alertUtils.showAlert("글유형을 선택해주세요.");
	    			return;
	    		} else if (!$('#lgwkNotePostDtlTit').val()) {
	    			alertUtils.showAlert("제목을 기입해주세요.");
	    			return;
	    		} else if (!$('<div>').html(editor.getHTML()).text()) {
	    			alertUtils.showAlert("본문 내용을 기입해주세요.");
	    			return;
	    		}
		    	
		    	alertUtils.showConfirm('게시글을 수정하시겠습니까?', function() {
			    	var requestMap = {
		    			lgwkNotePostSno: '${lwDtl.lgwkNotePostSno}'
		    			, lgwkNotePostDtlSno: '${lwDtl.lgwkNotePostDtlSno}'
		    			, lgwkNotePostDtlPtDtcd: $('input[name=lgwkNotePostDtlPtDtcd]:checked').val()
		    			, lgwkNotePostDtlStatDtcd: '01'
		    			, lgwkNotePostDtlTit: $('#lgwkNotePostDtlTit').val()
		    			, lgwkNotePostDtlCtt: editor.getHTML()
			    	};
			    	
			    	ajax('DIALW13', requestMap, function(response) {
			    		if (response.header && response.header.status == '0000') {
			    			alertUtils.showAlert('수정되었습니다', function() {
			    				gotoURL('DIALW10?lgwkNotePostSno=${lwDtl.lgwkNotePostSno}&lgwkNotePostDtlSno=${lwDtl.lgwkNotePostDtlSno}');
			    			});
			    		} else {
			    			alertUtils.showAlert(response.header.errorMsg);
			    		}
			    	});
		    		
		    	});
			});
			
			$('#btnCancle').click(function() {
				alertUtils.showConfirm('저장하지 않고 수정을 취소하시겠습니까?', function() {
					gotoURL('DIALW10?lgwkNotePostSno=${lwDtl.lgwkNotePostSno}&lgwkNotePostDtlSno=${lwDtl.lgwkNotePostDtlSno}');
				});
			});
		});
	
	
	
	</script>


	<jsp:include page="/WEB-INF/jsp/include/scriptBody.jsp"></jsp:include>

</body>

</html>