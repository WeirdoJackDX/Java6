const app = angular.module('app', []);
app.controller('cart-ctrl', function ($scope, $http) {
	$http.get("/rest/cart").then(response => {
		$scope.db = response.data;
		$scope.total = $scope.getTotal($scope.db);
		console.log($scope.db.products);
	})

	$scope.index_of = function (id) {
		return $scope.db.products.findIndex(a => a.maGioHang == id);
	}

	$scope.update = function (id, quantity, method) {
		var index = $scope.index_of(id);
		var item = angular.copy($scope.db.products[index]);
		var url = `/rest/cart/${item.maGioHang}`
		if (method == 0 && item.soLuong > 1) {
			item.soLuong = quantity - 1;
		} else if (method == 1 && item.soLuong < item.sanPham.soLuong) {
			item.soLuong = quantity + 1;
		}
		$http.put(url, item).then(response => {
			$scope.db.products[index] = response.data;
			$scope.total = $scope.getTotal($scope.db);
		}).catch(error => {
			console.log("Error update", error)
		})
	}

	$scope.delete = function (id) {
		var index = $scope.index_of(id);
		var item = angular.copy($scope.db.products[index]);
		var url = `/rest/cart/${item.maGioHang}`
		$http.delete(url).then(response => {
			$scope.db.products.splice(index, 1);
			$scope.total = $scope.getTotal($scope.db);
		}).catch(error => {
			console.log("Error delete", error)
		});
	}

	$scope.getTotal = function (db) {
		var total = 0;
		for (var i = 0; i < db.products.length; i++) {
			var product = db.products[i];
			total += (product.sanPham.giaBan * product.soLuong);
		}
		return total;
	}
});



function updateCartMinus(maGH) {
	var soLuong = $('#' + maGH + '_soLuong')
	var giaBan = $('#' + maGH + '_giaBan')
	var tongTien = $('#' + maGH + '_tongTien')
	var soLuong2 = $('#' + maGH + '_checkout_soLuong')
	var tongTien2 = $('#' + maGH + '_checkout_tongTien')
	$.ajax({
		url: '/user/cart/updateMinus',
		type: 'POST',
		data: {
			maGH: maGH
		},
		success: function (tienBanh) {
			if (parseInt(soLuong.val()) > 1) {
				soLuong.val(parseInt(soLuong.val()) - 1);
				tongTien.text((parseInt(giaBan.text() * soLuong.val())) + 'VND');
				soLuong2.text(parseInt(soLuong2.text()) - 1);
				tongTien2.text(parseInt(giaBan.text() * soLuong.val()) + 'VND');
				$("#tienBanh").text(tienBanh + 'VND')
				$("#tienBanh_checkout").text(tienBanh + 'VND')
			}
		}
	});
}

function updateCartPlus(maGH) {
	var soLuong = $('#' + maGH + '_soLuong')
	var giaBan = $('#' + maGH + '_giaBan')
	var tongTien = $('#' + maGH + '_tongTien')
	var soLuong2 = $('#' + maGH + '_checkout_soLuong')
	var tongTien2 = $('#' + maGH + '_checkout_tongTien')
	$.ajax({
		url: '/user/cart/updatePlus',
		type: 'POST',
		data: {
			maGH: maGH
		},
		success: function (tienBanh) {
			soLuong.val(parseInt(soLuong.val()) + 1);
			tongTien.text(parseInt((giaBan.text() * soLuong.val())) + 'VND');
			soLuong2.text(parseInt(soLuong2.text()) + 1);
			tongTien2.text(parseInt((giaBan.text() * soLuong.val())) + 'VND');
			$("#tienBanh").text(tienBanh + 'VND')
			$("#tienBanh_checkout").text(tienBanh + 'VND')
		}
	});
}

function removeCart(maGH) {
	var obj = $('#cart_' + maGH)
	var obj2 = $('#checkout_' + maGH)
	$.ajax({
		url: '/user/cart/remove',
		type: 'POST',
		data: {
			maGH: maGH
		},
		success: function (response) {
			obj.remove();
			obj2.remove();
			$("#tienBanh").text(response.tienBanh + 'VND');
			$("#tienBanh_checkout").text(response.tienBanh + 'VND')
			$('#amountSoLuongSanPham').text(response.val);
		}
	});
}

// Get the modal
var modal = document.getElementById("myModal");

// Get the button that opens the modal
var btn = document.getElementById("myBtn");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks the button, open the modal 
btn.onclick = function () {
	modal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function () {
	modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function (event) {
	if (event.target == modal) {
		modal.style.display = "none";
	}
}

