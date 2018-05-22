<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<header>
	<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-danger">
		<a class="navbar-brand" href="<c:url value="/"/>">MW's E-Store</a>

		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="navbarCollapse" aria-controls="navbarCollapse"
			aria-expanded="true" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarCollapse">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item"><a class="nav-link text-warning"
					href="<c:url value="/products"/>">Products</a></li>

				<sec:authorize access="isAuthenticated()">
					<sec:authorize access="hasRole('ROLE_ADMIN')">
						<li class="nav-item"><a class="nav-link text-warning" href="<c:url value="/admin/home" />">AdminPage</a></li>
					</sec:authorize>

					<li class="nav-item"><a class="nav-link text-warning" href="javascript:document.getElementById('logout').submit()">Logout</a></li>
					<form id="logout" action="<c:url value="/logout" />" method="post">
						<input type="hidden" name="${_csrf.parameterName }"
							value="${_csrf.token }" />
					</form>

					<li class="nav-item"><a class="nav-link text-warning" href="<c:url value="/cart" />">Cart</a></li>
				</sec:authorize>
				
				<sec:authorize access="isAnonymous()">
					<li class="nav-item"><a class="nav-link text-warning" href="<c:url value="/admin/home" />">Login</a></li>
					<li class="nav-item"><a class="nav-link text-warning" href="<c:url value="/register" />">Register</a></li>
				</sec:authorize>

			</ul>
			<form class="form-inline mt-2 mt-md-0">
				<input class="form-control mr-sm-2" type="text" placeholder="Search"
					aria-label="Search">
				<button class="btn btn-outline-warning my-2 my-sm-0" type="submit">Search</button>
			</form>
		</div>
	</nav>
</header>