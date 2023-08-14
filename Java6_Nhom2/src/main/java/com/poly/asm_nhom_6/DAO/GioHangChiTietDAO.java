package com.poly.asm_nhom_6.DAO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.asm_nhom_6.model.GioHangChiTiet;

public interface GioHangChiTietDAO extends JpaRepository<GioHangChiTiet, Integer> {
    @Query("SELECT o FROM GioHangChiTiet o WHERE o.maGioHang LIKE ?1")
    Page<GioHangChiTiet> findByMaGioHang1(Integer maGioHang, Pageable pageable);

    @Query("SELECT COUNT(o) FROM GioHangChiTiet o WHERE o.nguoiDung.maND = ?1")
    Long countCartById(Integer maND);

    @Query("SELECT gh FROM GioHangChiTiet gh INNER JOIN gh.nguoiDung nd WHERE nd.hoTen LIKE ?1")
    Page<GioHangChiTiet> findByHoTen(String hoTen, Pageable pageable);

    @Query("SELECT o FROM GioHangChiTiet o WHERE o.nguoiDung.maND = ?1 and o.sanPham.maSP = ?2")
    GioHangChiTiet findByMaNDAndMaGioHang(Integer maND, Integer maSP);

    @Query("SELECT SUM(o.sanPham.giaBan*o.soLuong) FROM GioHangChiTiet o WHERE o.nguoiDung.maND = ?1")
    Long totalPrice(Integer maND);
}
