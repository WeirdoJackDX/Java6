package com.poly.asm_nhom_6.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.asm_nhom_6.DAO.NguoiDungDAO;
import com.poly.asm_nhom_6.model.NguoiDung;
import com.poly.asm_nhom_6.service.NguoiDungService;

@Service
public class NguoiDungServiceImpl implements NguoiDungService {

    @Autowired
    NguoiDungDAO dao;

    @Override
    public Map<Integer, String> getAllOptions() {
        List<NguoiDung> nguoidungs = dao.findTenNguoiDung();
        Map<Integer, String> nguoidungmap = new HashMap<>();
        for (NguoiDung nguoidung : nguoidungs) {
            nguoidungmap.put(nguoidung.getMaND(), nguoidung.getHoTen());
        }
        return nguoidungmap;
    }

    @Override
    public NguoiDung findByEmailLike(String email) {
        return dao.findByEmailLike(email);
    }

    @Override
    public NguoiDung findByTaiKhoanLike(String taiKhoan) {
        return dao.findByEmailLike(taiKhoan);
    }

    @Override
    public void save(NguoiDung nguoiDung) {
        dao.save(nguoiDung);
    }

    @Override
    public NguoiDung findByMaNDLike(Integer maND) {
        return dao.findByMaNDLike(maND);
    }

    @Override
    public NguoiDung findByTaiKhoanAndMatKhauLike(String taiKhoan, String matKhau) {
        return dao.findByTaiKhoanAndMatKhauLike(taiKhoan, matKhau);
    }

    @Override
    public NguoiDung findById(Integer maND) {
        return dao.findById(maND).get();
    }

}
