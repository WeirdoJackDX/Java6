package com.poly.asm_nhom_6.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.poly.asm_nhom_6.DAO.SanPhamDAO;
import com.poly.asm_nhom_6.model.SanPham;
import com.poly.asm_nhom_6.service.SanPhamService;

@Service
public class SanPhamServiceImpl implements SanPhamService {
    @Autowired
    SanPhamDAO sanPhamDAO;

    @Override
    public Page<SanPham> findbykeywordtensps(String keywordtensptim, Pageable pageable) {
        return sanPhamDAO.findbykeywordtensps(keywordtensptim, pageable);
    }

    @Override
    public SanPham findByMaSPLike(Integer maSP) {
        return sanPhamDAO.findByMaSPLike(maSP);
    }

    @Override
    public SanPham findById(Integer maSP) {
        return sanPhamDAO.findById(maSP).get();
    }

    @Override
    public void save(SanPham sp) {
        sanPhamDAO.save(sp);
    }

}
