<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="container-wrapper">
	<div class="container">
		<h2>Register User</h2>
		<p class="lead">회원가입에 필요한 내용을 입력해주세요</p>

		<sf:form action="${ pageContext.request.contextPath }/register"
			method="post" modelAttribute="user">

			<h3>기본 정보</h3>

			<div class="form-group">
				<label for="username">ID</label>
				<span style="color: #ff0000">${usernameMsg}</span>
				<sf:input path="username" id="username" class="form-control" />
				<sf:errors path="username" cssStyle="color: #ff0000" />
			</div>

			<div class="form-group">
				<label for="password">PW</label>
				<sf:input type="password" path="password" id="password" class="form-control" />
				<sf:errors path="password" cssStyle="color: #ff0000" />
			</div>

			<div class="form-group">
				<label for="email">E-mail</label>
				<span style="color: #ff0000">${emailMsg}</span>
				<sf:input path="email" id="email" class="form-control" />
				<sf:errors path="email" cssStyle="color: #ff0000" />
			</div>

			<h3>배송 정보</h3>

			<div class="form-group">
				<label for="address">주소</label>
				<sf:input path="address.address" id="address" class="form-control" />
			</div>

			<div class="form-group">
				<label for="country">국가</label>
				<sf:input path="address.country" id="country" class="form-control" />
			</div>

			<div class="form-group">
				<label for="zipCode">우편번호</label>
				<sf:input path="address.zipCode" id="zipCode" class="form-control" />
			</div>

			<input type="submit" value="등록" class="btn btn-danger">
			<a href="<c:url value="/" />"
				class="btn btn-default">취소</a>

		</sf:form>

	</div>
</div>
