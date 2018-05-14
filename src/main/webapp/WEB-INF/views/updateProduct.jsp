<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="container-wrapper">
	<div class="container">
		<h2>Update Product</h2>
		<p class="lead">제품 정보를 편집합니다</p>
		
		<sf:form action="${ pageContext.request.contextPath }/admin/productInventory/update?${_csrf.parameterName}=${_csrf.token}"
			method="post" modelAttribute="product"
			enctype="multipart/form-data">

			<sf:hidden path="id" />

			<div class="form-group">
				<label for="name">Name</label>
				<sf:input path="name" id="name" class="form-control" />
			</div>
			
			<div class="form-group">
				<label for="category">Category</label>
				<sf:radiobutton path="category" id="category" value="컴퓨터" />컴퓨터
				<sf:radiobutton path="category" id="category" value="가전" />가전
				<sf:radiobutton path="category" id="category" value="잡화" />잡화
			</div>
		
			<div class="form-group">
				<label for="description">Description</label>
				<sf:textarea path="description" id="description" class="form-control" />
			</div>
		
			<div class="form-group">
				<label for="price">Price</label>
				<sf:input path="price" id="price" class="form-control" />
			</div>
		
			<div class="form-group">
				<label for="unitInStock">UnitInStock</label>
				<sf:input path="unitInStock" id="unitInStock" class="form-control" />
			</div>
		
			<div class="form-group">
				<label for="manufacturer">Manufacturer</label>
				<sf:input path="manufacturer" id="manufacturer" class="form-control" />
			</div>

			<div class="form-group">
				<label for="productImage">Upload Picture</label>
				<sf:input type="file" path="productImage" id="productImage" class="form-control" />
			</div>
			
			<input type="submit" value="수정" class="btn btn-danger">
			<a href="<c:url value="/admin/productInventory" />" class="btn btn-default">취소</a>

		</sf:form>

	</div>
</div>
