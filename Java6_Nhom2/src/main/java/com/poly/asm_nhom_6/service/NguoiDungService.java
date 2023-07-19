package com.poly.asm_nhom_6.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.asm_nhom_6.DAO.NguoiDungDAO;
import com.poly.asm_nhom_6.model.NguoiDung;

@Service
public class NguoiDungService {
	@Autowired
	public NguoiDungDAO nguoidungdao;

	public Map<Integer, String> getAllOptions() {
		List<NguoiDung> nguoidungs = nguoidungdao.findTenNguoiDung();
		Map<Integer, String> nguoidungmap = new HashMap<>();
		for (NguoiDung nguoidung : nguoidungs) {
			nguoidungmap.put(nguoidung.getMaND(), nguoidung.getHoTen());
		}
		return nguoidungmap;
	}
}
