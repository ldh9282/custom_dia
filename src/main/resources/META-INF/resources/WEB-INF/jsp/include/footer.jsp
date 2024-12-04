<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
	<footer id="footer" class="footer">
		<section>
	    	<div class="container">
		      	<div class="text-center my-3">
		        	<span>&copy; <span id="currentYear">2024</span>. ldh9282 All rights reserved</span>
		      	</div>
		      
		      	<div class="text-center my-3">
					<span class="text-muted">추가 요구사항 및 버그 제보:</span>
					<a href="mailto:ldh9282@naver.com" class="text-dark ms-1">ldh9282@naver.com</a>
			  	</div>
			  	<a href="javascript:scrollToTop();" id="topBtn">&uarr;</a>
			  	<a href="javascript:scrollToBottom();" id="downBtn">&darr;</a>
	    	</div>
		</section>
	</footer>
	<script>
		const currentYear = new Date().getFullYear();
	 	document.getElementById("currentYear").textContent = currentYear;
	 	
	 	window.onscroll = function() {
	 		
		   	var threshold = 200;
		   	
		   	var topButton = document.getElementById("topBtn");
		   	
	   	
	     	if (window.scrollY > threshold) {
	       		topButton.classList.add("show");
    		} else {
    			topButton.classList.remove("show");
    		}
	     	
		   	var downButton = document.getElementById("downBtn");
	     	
		    var scrollTop = document.documentElement.scrollTop;
		    
		    var scrollHeight = document.documentElement.scrollHeight;
		    
		    var clientHeight = document.documentElement.clientHeight;
            
            if (scrollTop + clientHeight < scrollHeight - threshold) {
	       		downButton.classList.add("show");
            } else {
    			downButton.classList.remove("show");
            }
	     	
	 	};
		
		function scrollToTop() {
		    window.scrollTo({ top: 0, behavior: 'smooth' });
		}

        function scrollToBottom() {
        	window.scrollTo({ top: document.documentElement.scrollHeight, behavior: 'smooth' });
        }
	</script>