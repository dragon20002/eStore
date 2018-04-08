<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="container-wrapper">

	<div class="container">
		<h2>Administrator Page</h2>
		<p class="lead">Product DB를 관리할 수 있습니다</p>
	</div>

	<div class="container">
		<h2><a href="<c:url value='/admin/productInventory' />">Product Inventory</a></h2>
		<p class="lead">Product DB 목록을 확인하고 편집할 수 있습니다</p>
	</div>

</div>