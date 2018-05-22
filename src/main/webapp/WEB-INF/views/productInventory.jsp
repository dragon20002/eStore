<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="container-wrapper">
	<div class="container">
		<h2>Product Inventory</h2>
		<p>제품 재고 현황</p>
		<table class="table table-striped">
			<thead>
				<tr class="bg-secondary text-warning">
					<th>Thumbnail</th>
					<th>Name</th>
					<th>Category</th>
					<th>Price</th>
					<th>Manufacturer</th>
					<th>UnitInStock</th>
					<th>Description</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="product" items="${products }">
					<tr>
						<td><img alt="thumbnail" style="width: 100%"
						 	src="<c:url value="/resources/images/${product.imageFilename }" />"/></td>
						<td>${product.name }</td>
						<td>${product.category }</td>
						<td>${product.price }</td>
						<td>${product.manufacturer }</td>
						<td>${product.unitInStock }</td>
						<td>${product.description }</td>
						<td><a href="<c:url value="/admin/productInventory/deleteProduct/${product.id }" />"><i class="fas fa-times"></i></a></td>
						<td><a href="<c:url value="/admin/productInventory/updateProduct/${product.id }" />"><i class="fas fa-edit"></i></a></td>						
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<a href="<c:url value="/admin/productInventory/addProduct" />" class="btn btn-danger">상품 추가</a>
	</div>
</div>
