package com.poly.asm_nhom_6.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.asm_nhom_6.DAO.GioHangChiTietDAO;
import com.poly.asm_nhom_6.model.GioHangChiTiet;
import com.poly.asm_nhom_6.service.GioHangChiTietService;

@Service
public class GioHangChiTietServiceImpl implements GioHangChiTietService {

    @Autowired
    GioHangChiTietDAO dao;

    @Override
    public Long countCartById(Integer maND) {
        return dao.countCartById(maND);
    }

    @Override
    public GioHangChiTiet findByMaNDAndMaGioHang(Integer maND, Integer maSP) {
        return dao.findByMaNDAndMaGioHang(maND, maSP);
    }

    @Override
    public void save(GioHangChiTiet gioHangChiTiet) {
        dao.save(gioHangChiTiet);
    }

    @Override
    public GioHangChiTiet findById(Integer maGH) {
        return dao.findById(maGH).get();
    }

    @Override
    public Long totalPrice(Integer maND) {
        return dao.totalPrice(maND);
    }

    @Override
    public void delete(GioHangChiTiet ghct) {
        dao.delete(ghct);
    }

}
