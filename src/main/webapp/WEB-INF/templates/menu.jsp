<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
				
				<c:if test="${pageContext.request.userPrincipal.name != null }">
					<c:if test="${pageContext.request.userPrincipal.name == 'haruu' }">
						<li class="nav-item"><a class="nav-link text-warning" href="<c:url value="/admin/home" />">AdminPage</a></li>
					</c:if>

					<li class="nav-item"><a class="nav-link text-warning" href="javascript:document.getElementById('logout').submit()">Logout</a></li>
					<form id="logout" action="<c:url value="/logout" />" method="post">
						<input type="hidden" name="${_csrf.parameterName }"
							value="${_csrf.token }" />
					</form>
				</c:if>
				
				<c:if test="${pageContext.request.userPrincipal.name == null }">
					<li class="nav-item"><a class="nav-link text-warning" href="<c:url value="/admin/home" />">Login</a></li>
				</c:if>
			
			</ul>
			<form class="form-inline mt-2 mt-md-0">
				<input class="form-control mr-sm-2" type="text" placeholder="Search"
					aria-label="Search">
				<button class="btn btn-outline-warning my-2 my-sm-0" type="submit">Search</button>
			</form>
		</div>
	</nav>
</header>