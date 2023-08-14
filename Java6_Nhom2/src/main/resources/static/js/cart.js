const app = angular.module('app', []);
app.controller('cart-ctrl', function ($scope, $http) {
	$http.get("/rest/cart").then(response => {
		$scope.db = response.data;
		$scope.total = $scope.getTotal($scope.db);
		$scope.check = false;
	})

	$scope.index_of = function (id) {
		return $scope.db.products.findIndex(a => a.gioHangChiTiet.maGioHang == id);
	}

	$scope.update = function (id, quantity, method) {
		var index = $scope.index_of(id);
		var item = angular.copy($scope.db.products[index].gioHangChiTiet);
		var url = `/rest/cart/${item.maGioHang}`
		if (method == 0) {
			if (item.soLuong <= 1) {
				notice('error', "Số lượng ít nhất là 1");
			} else if (item.sanPham.soLuong == 0) {
				notice('error', "Số lượng trong kho còn " + item.sanPham.soLuong);
			} else {
				item.soLuong = quantity - 1;
			}
		} else if (method == 1) {
			if (item.soLuong >= item.sanPham.soLuong) {
				notice('error', "Số lượng trong kho còn " + item.sanPham.soLuong);
			} else {
				item.soLuong = quantity + 1;
			}
		}
		$http.put(url, item).then(response => {
			$scope.db.products[index].gioHangChiTiet.soLuong = item.soLuong;
			$scope.total = $scope.getTotal($scope.db);
		}).catch(error => {
			console.log("Error update", error)
		})
	}

	$scope.delete = function (id) {
		var index = $scope.index_of(id);
		var item = angular.copy($scope.db.products[index].gioHangChiTiet);
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
			if (product.isChecked === true) {
				total += (product.gioHangChiTiet.sanPham.giaBan * product.gioHangChiTiet.soLuong);
			}
		}
		return total;
	}

	$scope.updateCheck = function (id) {
		var index = $scope.index_of(id);
		if ($scope.db.products[index].isChecked === false) {
			$scope.db.products[index].isChecked = false
			$scope.check = false
		} else {
			$scope.db.products[index].isChecked = true
			var boolean = true;
			for (var i = 0; i < $scope.db.products.length; i++) {
				if ($scope.db.products[i].isChecked === false) {
					if ($scope.db.products[i].gioHangChiTiet.sanPham.soLuong >= $scope.db.products[i].gioHangChiTiet.soLuong) {
						boolean = false;
					}
				}
			}
			if (boolean === true) {
				$scope.check = true
			}
		}
		$scope.total = $scope.getTotal($scope.db);
		console.log($scope.db.products)
	}

	$scope.checkBox = function () {
		if ($scope.check === true) {
			$scope.checkAll()
		} else {
			$scope.uncheckAll()
		}
	}

	$scope.checkAll = function () {
		for (var i = 0; i < $scope.db.products.length; i++) {
			if ($scope.db.products[i].gioHangChiTiet.sanPham.soLuong >= $scope.db.products[i].gioHangChiTiet.soLuong
			) {
				$scope.db.products[i].isChecked = true
			}
		}
		$scope.check = true
		$scope.total = $scope.getTotal($scope.db);
		console.log($scope.db.products)
	}

	$scope.uncheckAll = function () {
		var boolean = true;
		for (var i = 0; i < $scope.db.products.length; i++) {
			if ($scope.db.products[i].isChecked === false) {
				if ($scope.db.products[i].gioHangChiTiet.sanPham.soLuong > $scope.db.products[i].gioHangChiTiet.soLuong && $scope.db.products[i].gioHangChiTiet.sanPham.soLuong > 0) {
					boolean = false;
				}
			}
		}
		if (boolean === true) {
			for (var i = 0; i < $scope.db.products.length; i++) {
				$scope.db.products[i].isChecked = false;
			}
		}
		$scope.check = false
		$scope.total = $scope.getTotal($scope.db);
	}

	$scope.toPayMent = function () {
		var url = `/getDTO`
		var item = angular.copy($scope.db.products)
		$http.post(url, item)
	}
});

function notice(type, message) {
	swal("Message", message, type, {
		button: true,
		button: "OK",
		timer: 10000,
	})
}


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

