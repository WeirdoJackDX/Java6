package com.poly.asm_nhom_6.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.asm_nhom_6.DAO.NhaCungCapDAO;
import com.poly.asm_nhom_6.model.NhaCungCap;


@Service
public class OptionServiceNhaCungCap {
	@Autowired
	public NhaCungCapDAO nhapcungcapdao;

	public Map<Integer, String> getAllOptions() {
		List<NhaCungCap> nhacungcaps = nhapcungcapdao.findAll();
		Map<Integer, String> nhacungcapmap = new HashMap<>();
		for (NhaCungCap nhacungcap : nhacungcaps) {
			nhacungcapmap.put(nhacungcap.getMaNhaCungCap(), nhacungcap.getTenNhaCungCap());
		}

		return nhacungcapmap;
	}

	public NhaCungCap getOptionByKey(String key) {
		List<NhaCungCap> nhacungcaps = nhapcungcapdao.findbykeywords(key);
		return nhacungcaps.isEmpty() ? null : nhacungcaps.get(0);
	}
}
