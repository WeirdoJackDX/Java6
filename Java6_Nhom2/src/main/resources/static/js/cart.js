function updateCartMinus(maGH) {
	var soLuong = $('#' + maGH + '_soLuong')
	var giaBan = $('#' + maGH + '_giaBan')
	var tongTien = $('#' + maGH + '_tongTien')
	$.ajax({
		url: '/user/cart/updateMinus',
		type: 'POST',
		data: {
			maGH: maGH
		},
		success: function(tienBanh) {
			if (parseInt(soLuong.val()) > 1) {
				soLuong.val(parseInt(soLuong.val()) - 1);
				tongTien.text(parseInt(giaBan.text() * soLuong.val()));
				$("#tienBanh").text(tienBanh + 'VND')
			}
		}
	});
}

function updateCartPlus(maGH) {
	var soLuong = $('#' + maGH + '_soLuong')
	var giaBan = $('#' + maGH + '_giaBan')
	var tongTien = $('#' + maGH + '_tongTien')
	$.ajax({
		url: '/user/cart/updatePlus',
		type: 'POST',
		data: {
			maGH: maGH
		},
		success: function(tienBanh) {
			soLuong.val(parseInt(soLuong.val()) + 1);
			tongTien.text(parseInt(giaBan.text() * soLuong.val()));
			$("#tienBanh").text(tienBanh + 'VND')
		}
	});
}

function removeCart(maGH) {
	var obj = $('#cart_' + maGH)
	$.ajax({
		url: '/user/cart/remove',
		type: 'POST',
		data: {
			maGH: maGH
		},
		success: function(response) {
			obj.remove();
			$("#tienBanh").text(response.tienBanh + 'VND');
			$('#amountSoLuongSanPham').text(response.val);
		}
	});
}


