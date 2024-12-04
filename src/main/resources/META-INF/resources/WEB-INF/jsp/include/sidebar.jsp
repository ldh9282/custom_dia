<%@page import="com.custom.dia.cmmn.utils.StringUtils"%>
<%@page import="com.custom.dia.cmmn.security.utils.SecurityUtils"%>
<%@page import="org.slf4j.MDC"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<%

	String url = StringUtils.NVL(MDC.get("identifier"), "");
	String username = StringUtils.NVL(SecurityUtils.getUsername(), "");
	request.setAttribute("loginTime", StringUtils.NVL((String) session.getAttribute("loginTime"), ""));

%>


	<c:set var="url" value="<%= url %>" />
	<c:set var="username" value="<%= username %>" />
	
	<aside id="sidebar" class="sidebar">
		<div class="card info-card sidebar-card">
			<div class="card-body">
				<div class="card-title">
					<div class="h6 mb-4">${username}님 안녕하세요</div>
					<div class="h6">현재시각 <span class="h6 text-dark"><timer-element></timer-element></span></div>
					<div class="h6">접속시간 <span class="h6 text-dark" id="connectTime"></span></div>
				</div>
				<div onclick="logout();">
			        <i class="bi bi-box-arrow-right"></i>
			        <span class="sidebar-logout">로그아웃</span>
		        </div>
			</div>
		</div>
		<ul class="sidebar-nav" id="sidebar-nav">
			
			<li class="nav-heading">NP</li>
			<li class="nav-item">
				<a class="nav-link ${url == 'DIANP03' ? '' : 'collapsed'}" href="javascript:gotoURL('DIANP03');">
					<span>조회</span>
				</a>
			</li>
			<li class="nav-heading">LW</li>
			<li class="nav-item">
				<a class="nav-link ${url == 'DIALW03' ? '' : 'collapsed'}" href="javascript:gotoURL('DIALW03');">
					<span>조회</span>
				</a>
			</li>
		</ul>
	</aside>
	
	
	<script type="text/javascript">
		$(document).ready(function() {
			
			updateConnectTime();
			setInterval(updateConnectTime, 1000);
		    
		});
		
		const updateConnectTime = function() {
			let diffMillis = new Date().getTime() - new Date('${loginTime}').getTime();
			let displayTime = timerUtils.getDisplayTime(diffMillis);
			
			$('#connectTime').text(displayTime.hour + ':' + displayTime.min + ':' + displayTime.sec);
		};
	</script>