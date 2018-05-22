<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script src="<c:url value="/resources/js/controller.js" />"></script>

<div class="container-wrapper jumbotron" ng-app="cartApp">

	<div class="row" style="max-width: 1500px">
		<div class="col-md-6" align="center">
			<img src="<c:url value="/resources/images/${product.imageFilename }" />" alt="product image"
				style="width: 50%; min-width: 400px; max-width: 500px;" />
		</div>

		<div class="col-md-6" style="padding-left: 50px; padding-top: 10px;">
			<h5>${product.category } ></h5>
			<h2 style="padding: 10px; padding-left: 20px;">${product.name}</h2>

			<b>Description</b>
			<p class="bg-light" style="padding: 5px; padding-left: 20px; font-size: 1.2em;">${product.description}</p>

			<table style="width: 100%;">
				<tr>
					<th>Manufacturer</th>
					<td class="bg-light" align="center">${product.manufacturer }</td>
				</tr>
				<tr>
					<th>Stock</th>
					<td class="bg-light" align="center">${product.unitInStock } EA</td>
				</tr>
			</table>
			<h3 style="padding: 20px">￦ ${product.price }</h3>
			
			<br>
		</div>

	</div>

	<div align="center" style="max-width: 1500px" ng-controller="cartCtrl">
	
			<c:if test="${pageContext.request.userPrincipal.name != null }">
				<p>
					<button class="btn btn-danger" ng-click="addToCart('${product.id}')">
						<i class="fa fa-shopping-cart"></i>
						Order
					</button>

					<a href="<c:url value="/products" />" class="btn btn-warning">Back</a>
					
					<a href="<c:url value="/cart" />" class="btn btn-info">
						<i class="fa fa-eye"></i>
						View Cart
					</a>
				</p>
			</c:if>
			
	</div>
</div>