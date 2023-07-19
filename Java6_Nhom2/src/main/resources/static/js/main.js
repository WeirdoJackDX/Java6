function dangXuat() {
	var url;
	$.ajax({
		url: '/user/logout', // Đường dẫn tới controller xử lý đăng nhập
		type: 'POST',
		success: function(response) {
			var currentURL = window.location.href;
			if (currentURL.includes("cart")) {
				window.location.href = response.redirectUrl

			} else {
				location.reload();
			}
		},
		error: function(xhr, status, error) {
			console.log("Lỗi AJAX: " + error);
		}
	})
}

function thongBao(loai, message) {
	swal("Message", message, loai, {
		button: true,
		button: "OK",
		timer: 10000,
	})
}

function likeSanPham(params) {
	var maSP = $('#maSP' + params);
	var luotThich = $('#luotThich' + params);
	$.ajax({
		url: '/user/like', // Đường dẫn đến API hoặc endpoint xử lý yêu cầu
		type: 'POST',
		data: {
			maSP: params
		},
		success: function(response) {
			if (response.success) {
				if (parseInt(response.message) === 0) {
					like(maSP, luotThich);
				} else {
					unlike(maSP, luotThich);
				}
			} else {
				test('Vui lòng đăng nhập để yêu thích sản phẩm');
			}
		},
		error: function(error) {
			console.log('Lỗi AJAX: ' + error.status);
		}
	});

}

function like(maSP, luotThich) {
	maSP.fadeOut(500, function() {
		maSP.removeClass('fa-regular');
		maSP.addClass('fa-solid');
		maSP.fadeIn(300);
	});
	soLuotThich = parseInt(luotThich.text());
	luotThich.text(soLuotThich + 1);
}

function unlike(maSP, luotThich) {
	maSP.fadeOut(500, function() {
		maSP.removeClass('fa-solid');
		maSP.addClass('fa-regular');
		maSP.fadeIn(300);
	});
	soLuotThich = parseInt(luotThich.text());
	luotThich.text(soLuotThich - 1);
}

document.addEventListener('DOMContentLoaded', function() {
	var input1 = document.getElementById('taiKhoanLogin');
	var input2 = document.getElementById('matKhauLogin');

	input1.addEventListener('input', myFunction);
	input2.addEventListener('input', myFunction);

	function myFunction() {
		var alertElement = $('#alertLogin');
		alertElement.hide();
	}
});

function test(message) {
	$('#loginModal').modal('show');

	var alertElement = document.getElementById('alertLogin');

	// Cập nhật nội dung của thẻ alert
	alertElement.textContent = message;

	// Hiện thẻ alert
	alertElement.style.display = 'block';
}

function openGioHang() {
	$.ajax({
		url: '/user/cart/open', // Đường dẫn tới controller xử lý đăng nhập
		type: 'POST',
		success: function(response) {
			if (response.success && parseInt(response.message) === 0) {
				// Đăng nhập thành công
				if (response.redirectUrl) {
					window.location.href = response.redirectUrl; // Chuyển hướng về trang home
				} else {
					console.log("Lỗi: Không có URL chuyển hướng");
				}
			} else {
				test('Vui lòng đăng nhập để mở giỏ hàng');
			}
		},
		error: function(xhr, status, error) {
			console.log("Lỗi AJAX: " + error);
		}
	});
}

function addToGioHang(maSP) {
	var maND = $('#maND').val();
	var soLuongSanPham = $('#soLuongSanPham').val();
	console.log(maND, soLuongSanPham);
	if (soLuongSanPham) {
		if (parseInt(soLuongSanPham) > 100) {
			soLuongSanPham = 99;
			$('#soLuongSanPham').val(99);
		} else {
			if (parseInt(soLuongSanPham) < 0) {
				soLuongSanPham = 1;
				$('#soLuongSanPham').val(1);
			}
		}
	} else {
		if (parseInt(soLuongSanPham) > 100) {
			soLuongSanPham = 99;
			$$('#soLuongSanPham').val(99);
		} else {
			if (parseInt(soLuongSanPham) < 0) {
				soLuongSanPham = 1;
				$('#soLuongSanPham').val(1);
			}
		}

		if (soLuongSanPham == undefined) {
			soLuongSanPham = 1;
		}
	}

	$.ajax({
		url: '/user/cart/add', // Đường dẫn tới controller xử lý đăng nhập
		type: 'POST',
		data: {
			maSP: maSP,
			maND: maND,
			soLuongSanPham: soLuongSanPham
		},
		success: function(response) {
			if (response.success) {
				if (parseInt(response.message) === 0) {
					thongBao('success', 'Thêm vào giỏ hàng thành công')
				} else {
					thongBao('error', response.message);
				}
				$('#amountSoLuongSanPham').text(response.val);
			} else {
				test('Vui lòng đăng nhập để thêm giỏ vào giỏ hàng');
			}
		},
		error: function(xhr, status, error) {
			console.log("Lỗi AJAX: " + error);
		}
	});
}
