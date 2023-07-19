package com.poly.asm_nhom_6.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.asm_nhom_6.DAO.VoucherDAO;
import com.poly.asm_nhom_6.model.Voucher;

@Service
public class VoucherService {
	@Autowired
	public VoucherDAO voucherdao;

	public Map<Integer, String> getAllOptions() {
		List<Voucher> vouchers = voucherdao.findAll();
		Map<Integer, String> vouchermap = new HashMap<>();
		vouchermap.put(0, "Không áp dụng voucher");
		for (Voucher voucher : vouchers) {
			vouchermap.put(voucher.getMaVoucher(), voucher.getTenVoucher());
		}
		return vouchermap;
	}

}
