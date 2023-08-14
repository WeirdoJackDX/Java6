package com.poly.asm_nhom_6.service;

import com.poly.asm_nhom_6.model.GioHangChiTiet;

public interface GioHangChiTietService {

    Long countCartById(Integer maND);

    GioHangChiTiet findByMaNDAndMaGioHang(Integer maND, Integer maSP);

    void save(GioHangChiTiet gioHangChiTiet);

    GioHangChiTiet findById(Integer maGH);

    Long totalPrice(Integer maND);

    void delete(GioHangChiTiet ghct);

}
