<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<c:if test="${pageContext.request.userPrincipal.name != null }">
	<a href="javascript:document.getElementById('logout').submit()"
		style="display: block; text-align: right">로그아웃</a>
</c:if>
<form id="logout" action="<c:url value="/logout" />" method="post">
	<input type="hidden" name="${_csrf.parameterName }"
		value="${_csrf.token }" />
</form>

<div class="jumbotron">
	<form action="<c:url value="/login" />" method="post"
		name="login">
		<input type="hidden" name="${_csrf.parameterName }"
			value="${_csrf.token }" />

		<div class="row">
			<div class="col-md-1">ID</div>
			<div class="col-md-3">
				<input size="16" maxLength="6" type="text" id="username" name="username"
					onFocus="this.value=''" tabindex="1">
			</div>
		</div>

		<div class="row">
			<div class="col-md-1">PW</div>
			<div class="col-md-3">
				<input size="16" maxLength="16" type="password" id="password"
					name="password" onFocus="this.select()" tabindex="2">
			</div>
		</div>

		<div class="row">
			<c:if test="${not empty errorMsg }">
				${errorMsg }
			</c:if>
		</div>
		
		<div class="row">
			<c:if test="${not empty logoutMsg }">
				${logoutMsg }
			</c:if>
		</div>

		<div class="row">
			<div class="col-md-12">
				<input class="btn btn-outline-warning" type="submit" id="login"
					name="login" value="로그인" tabindex="3" />
			</div>
		</div>

	</form>
</div>
