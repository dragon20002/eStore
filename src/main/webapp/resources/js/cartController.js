var cartApp = angular.module('cartApp', []);

cartApp.controller("cartController", function($scope, $http) { // 생성자, DI

	/* INIT */
	$scope.initCartId = function(cartId) {
		$scope.cartId = cartId;
		$scope.refreshCart();
	};

	/* Utils */
	$scope.calGrandTotal = function() {
		var grandTotal = 0;
		for (var i = 0; i < $scope.cart.cartItems.length; i++) {
			grandTotal += $scope.cart.cartItems[i].totalPrice;
		}
		return grandTotal;
	};
	
	$scope.setCsrfToken = function() {
		var csrfToken = $("meta[name='_csrf']").attr("content");
		var csrfHeader = $("meta[name='_csrf_header']").attr("content");
		$http.defaults.headers.common[csrfHeader] = csrfToken;
	};
	
	/* CRUDs */
	// CREATE
	
	// READ
	$scope.refreshCart = function() {
		// HTTP module
		// short
		$http.get('/eStore/api/cart/' + $scope.cartId).then(
				function successCallback(response) {
					$scope.cart = response.data;
				});
//		// long
//		$http({
//			method : 'GET',
//			url : '/eStore/api/cart/' + $scope.cartId
//		}).then(function successCallback() {
//			$scope.cart = response.data;
//		});
	};
	
	// UPDATE
	$scope.addToCart = function(productId) {
		$scope.setCsrfToken();
		
		$http.put('/eStore/api/cart/' + productId).then(
				function successCallback(response) {
					if (response.status === 200)
						alert("Product successfully added to the cart!");
					else if (response.status === 204)
						alert("Product is out of stock!");
				}, function errorCallback() {
					alert("Adding to the cart failed!")
				});
	};

	$scope.plusItemFromCart = function(productId) {
		$scope.setCsrfToken();
		
		$http.put('/eStore/api/cart/cart_items/' + productId + '/1').then(
				function successCallback() {
					$scope.refreshCart();
				}, function errorCallback(response) {
					console.log(response.data);
				});
	};

	$scope.minusItemFromCart = function(productId) {
		$scope.setCsrfToken();
		
		$http.put('/eStore/api/cart/cart_items/' + productId + '/-1').then(
				function successCallback() {
					$scope.refreshCart();
				}, function errorCallback(response) {
					console.log(response.data);
				});
	};

	// DELETE
	$scope.clearCart = function() {
		$scope.setCsrfToken();
		
		$http.delete('/eStore/api/cart/' + $scope.cartId).then(
				function successCallback() {
					$scope.refreshCart();
				}, function errorCallback(response) {
					console.log(response.data);
				});
	};
	
	$scope.removeFromCart = function(productId) {
		$scope.setCsrfToken();
		
		$http.delete('/eStore/api/cart/cart_items/' + productId).then(
				function successCallback() {
					$scope.refreshCart();
				}, function errorCallback(response) {
					console.log(response.data);
				});
	};

});
