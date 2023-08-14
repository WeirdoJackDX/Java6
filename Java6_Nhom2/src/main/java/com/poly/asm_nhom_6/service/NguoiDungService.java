package com.poly.asm_nhom_6.service;

import java.util.Map;

import com.poly.asm_nhom_6.model.NguoiDung;

public interface NguoiDungService {

	Map<Integer, String> getAllOptions();

	NguoiDung findByEmailLike(String email);

	NguoiDung findByTaiKhoanLike(String taiKhoan);

	void save(NguoiDung nguoiDung);

	NguoiDung findByMaNDLike(Integer maND);

	NguoiDung findByTaiKhoanAndMatKhauLike(String taiKhoan, String matKhau);

	NguoiDung findById(Integer maND);
}
