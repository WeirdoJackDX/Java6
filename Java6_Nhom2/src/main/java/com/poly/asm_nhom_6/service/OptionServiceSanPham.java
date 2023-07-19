package com.poly.asm_nhom_6.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.asm_nhom_6.DAO.SanPhamDAO;
import com.poly.asm_nhom_6.model.SanPham;

@Service
public class OptionServiceSanPham {
	@Autowired
	public SanPhamDAO sanphamdao;


	public Map<Integer, String> getAllOptions() {
		List<SanPham> sanphams = sanphamdao.findAll();
		Map<Integer, String> sanphammap = new HashMap<>();
		for (SanPham sanpham : sanphams) {
			sanphammap.put(sanpham.getMaSP(), sanpham.getTenSP());
		}
		return sanphammap;
	}

	public SanPham getOptionByKey(String key) {
		List<SanPham> sanphams = sanphamdao.findbykeywords(key);
		return sanphams.isEmpty() ? null : sanphams.get(0);
	}
}
