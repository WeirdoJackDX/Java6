package com.poly.asm_nhom_6.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.asm_nhom_6.model.DanhGia;
import com.poly.asm_nhom_6.model.ReportStar;

public interface DanhGiaDAO extends JpaRepository<DanhGia, Integer> {
	@Query("SELECT o FROM DanhGia o WHERE o.moTa LIKE ?1")
	Page<DanhGia> findByMoTa1(String moTa, Pageable pageable);

	@Query("SELECT dg FROM DanhGia dg INNER JOIN dg.nguoiDung nd WHERE nd.hoTen LIKE %?1%")
	Page<DanhGia> findByHoTen(String hoTen, Pageable pageable);

	// @Query("SELECT o FROM DanhGia o where o.moTa like %:moTa% or
	// o.nguoiDung.tenND like %:moTa% or o.sanPham.tenSP like %moTa%")
	// Page<DanhGia> findByKeyWord(String hoTen, Pageable pageable);

	@Query("SELECT new com.poly.asm_nhom_6.model.ReportStar(o.sanPham.tenSP, sum(o.saoDanhGia), count(o.nguoiDung.maND))"
			+ " FROM DanhGia o "
			+ " GROUP BY o.sanPham.tenSP")
	List<ReportStar> getInventoryBySanPham();
}
