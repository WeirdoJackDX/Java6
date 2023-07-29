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


