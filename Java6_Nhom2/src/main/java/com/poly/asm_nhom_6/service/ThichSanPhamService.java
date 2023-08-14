package com.poly.asm_nhom_6.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.poly.asm_nhom_6.model.ReportLike;
import com.poly.asm_nhom_6.model.ThichSanPham;

public interface ThichSanPhamService {

    ThichSanPham findByMaNDAndMaSPLike(Integer maND, Integer maSP);

    void save(ThichSanPham thichSanPham);

    void delete(ThichSanPham thichSanPham);

    ReportLike getAllSanPhamAndLikes_maSP(Integer maND, Integer maSP);

    List<ReportLike> getAllSanPhamAndLikeTheoLoai(Integer maND, Integer maLoai, Integer soLuongNgauNhien);

    List<ReportLike> getAllSanPhamAndLike(Integer maND, Integer soLuongNgauNhien);

    Page<ReportLike> getAllSanPham(Integer maND, String keyWord, Pageable pageable);

}
