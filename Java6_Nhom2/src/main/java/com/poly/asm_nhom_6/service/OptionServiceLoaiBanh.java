package com.poly.asm_nhom_6.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.asm_nhom_6.DAO.LoaiBanhDAO;
import com.poly.asm_nhom_6.model.LoaiBanh;


@Service
public class OptionServiceLoaiBanh {
	@Autowired
	public LoaiBanhDAO loaibanhdao;

	public Map<Integer, String> getAllOptions() {
		List<LoaiBanh> loaibanhs = loaibanhdao.findAll();
		Map<Integer, String> banhMap = new HashMap<>();
		for (LoaiBanh loaibanh : loaibanhs) {
			banhMap.put(loaibanh.getMaLoaiBanh(), loaibanh.getTenLoaiBanh());
		}

		return banhMap;
	}

	public LoaiBanh getOptionByKey(String key) {
		List<LoaiBanh> loaibanhs = loaibanhdao.findbykeywords(key);
		return loaibanhs.isEmpty() ? null : loaibanhs.get(0);
	}
}
