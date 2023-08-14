package com.poly.asm_nhom_6.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.poly.asm_nhom_6.model.SanPham;

public interface SanPhamService {
    Page<SanPham> findbykeywordtensps(String keywordtensptim, Pageable pageable);

    SanPham findByMaSPLike(Integer maSP);

    SanPham findById(Integer maSP);

    void save(SanPham sp);
}
