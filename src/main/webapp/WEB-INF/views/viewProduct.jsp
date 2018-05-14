<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="container-wrapper jumbotron">

	<div class="row" style="max-width: 1500px">
		<div class="col-md-6" align="center">
			<c:set var="imageFilename"
				value="/resources/images/${product.id}.jpg" />
			<img src="<c:url value="${imageFilename}"/>" alt="product image"
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
			
		</div>

	</div>

	<div align="center" style="max-width: 1500px">
		<a href="<c:url value="#" />" class="btn btn-danger">Order</a>
		<a href="<c:url value="/products" />" class="btn btn-warning">Back</a>
	</div>
</div>