<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="container-wrapper">
	<div class="container">
		<h2>All Products</h2>
		<p>싸게 싸게 사가세요</p>
		<table class="table table-striped">
			<thead>
				<tr class="bg-secondary text-warning">
					<th>Thumbnail</th>
					<th>Name</th>
					<th>Category</th>
					<th>Price</th>
					<th>Manufaturer</th>
					<th>UnitInStock</th>
					<th>Description</th>
					<th>Details</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="product" items="${products }">
					<tr>
						<td><img alt="thumbnail" style="width: 50%"
						 	src="<c:url value="/resources/images/${product.imageFilename }" />"/></td>
						<td>${product.name }</td>
						<td>${product.category }</td>
						<td>${product.price }</td>
						<td>${product.manufacturer }</td>
						<td>${product.unitInStock }</td>
						<td>${product.description }</td>
						<td align="center">
							<a href="<c:url value="/viewProduct/${product.id }" />">
								<i class="fas fa-info-circle"></i>
							</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
