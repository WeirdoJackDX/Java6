package com.poly.asm_nhom_6.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.asm_nhom_6.model.ChiTietHoaDon;

public interface ChiTietHoaDonDAO extends JpaRepository<ChiTietHoaDon, Integer> {
    @Query("SELECT SUM(p.giaBan * p.soLuong) FROM ChiTietHoaDon p")
    public Float tongDoanhThu();
}
