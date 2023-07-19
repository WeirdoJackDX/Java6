package com.poly.asm_nhom_6.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.asm_nhom_6.DAO.DanhGiaDAO;
import com.poly.asm_nhom_6.model.DanhGia;


@Service
public class DanhGiaService {
	@Autowired
	public DanhGiaDAO danhgiadao;

	public Map<Integer, Integer> getAllOptions() {
		List<DanhGia> danhgias = danhgiadao.findAll();
		Map<Integer, Integer> danhgiamap = new HashMap<>();
		for (DanhGia danhgia : danhgias) {
			danhgiamap.put(danhgia.getMaDanhGia(), danhgia.getSaoDanhGia());
		}
          return danhgiamap;   
	}

	
}
