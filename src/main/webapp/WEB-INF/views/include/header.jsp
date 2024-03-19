<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="header" class="clearfix">
	<h1>
		<a href="">MySite-${pageContext.request.contextPath}</a>
	</h1>

	<ul>
		<c:if test="${empty sessionScope.authUser}">
			<!-- 로그인 전 -->
			<li><a href="${pageContext.request.contextPath}/user/loginform">로그인</a></li>
			<li><a href="${pageContext.request.contextPath}/user/joinform">회원가입</a></li>
		</c:if>

		<c:if test="${ !(empty sessionScope.authUser) }">
			<!-- 로그인 후 -->
			<li>${sessionScope.authUser.name} 님 안녕하세용 ~</li>
			<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
			<li><a href="${pageContext.request.contextPath}/user/modifyform">회원정보수정</a></li>

		</c:if>
	</ul>
</div>
<!-- //header -->
<div id="nav">
	<ul class="clearfix">
		<li><a href="">입사지원서</a></li>
		<li><a href="${pageContext.request.contextPath}/board/list">게시판</a></li>
		<li><a href="${pageContext.request.contextPath}/gallery/list">갤러리</a></li>
		<li><a href="">방명록</a></li>
	</ul>
</div>