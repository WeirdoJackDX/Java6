package com.poly.asm_nhom_6.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.poly.asm_nhom_6.DAO.ThichSanPhamDAO;
import com.poly.asm_nhom_6.model.ReportLike;
import com.poly.asm_nhom_6.model.ThichSanPham;
import com.poly.asm_nhom_6.service.ThichSanPhamService;

@Service
public class ThichSanPhamServiceImpl implements ThichSanPhamService {

    @Autowired
    ThichSanPhamDAO dao;

    @Override
    public ThichSanPham findByMaNDAndMaSPLike(Integer maND, Integer maSP) {
        return dao.findByMaNDAndMaSPLike(maND, maSP);
    }

    @Override
    public void save(ThichSanPham thichSanPham) {
        dao.save(thichSanPham);
    }

    @Override
    public void delete(ThichSanPham thichSanPham) {
        dao.delete(thichSanPham);
    }

    @Override
    public ReportLike getAllSanPhamAndLikes_maSP(Integer maND, Integer maSP) {
        return dao.getAllSanPhamAndLikes_maSP(maND, maSP);
    }

    @Override
    public List<ReportLike> getAllSanPhamAndLikeTheoLoai(Integer maND, Integer maLoai, Integer soLuongNgauNhien) {
        return dao.getAllSanPhamAndLikeTheoLoai(maND, maLoai, soLuongNgauNhien);
    }

    @Override
    public List<ReportLike> getAllSanPhamAndLike(Integer maND, Integer soLuongNgauNhien) {
        return dao.getAllSanPhamAndLike(maND, soLuongNgauNhien);
    }

    @Override
    public Page<ReportLike> getAllSanPham(Integer maND, String keyWord, Pageable pageable) {
        return dao.getAllSanPham(maND, keyWord, pageable);
    }

}
