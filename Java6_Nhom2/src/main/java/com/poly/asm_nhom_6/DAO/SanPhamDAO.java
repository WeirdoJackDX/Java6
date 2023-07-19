package com.poly.asm_nhom_6.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.asm_nhom_6.model.ReportLike;
import com.poly.asm_nhom_6.model.ReportSP;
import com.poly.asm_nhom_6.model.SanPham;

public interface SanPhamDAO extends JpaRepository<SanPham, Integer> {
	@Query("select o from SanPham o where o.maSP like ?1")
	List<SanPham> findbykeywords(String keywords);

	@Query("select o from SanPham o where o.tenSP like ?1")
	Page<SanPham> findbykeywordtensps(String keywordtensptim, Pageable pageable);

	@Query("SELECT new com.poly.asm_nhom_6.model.ReportSP(o.loaiBanh, sum(o.soLuong), count(o))" + " FROM SanPham o "
			+ " GROUP BY o.loaiBanh" + " ORDER BY sum(o.giaBan) DESC")
	List<ReportSP> reportSanPhamByLoaiBanhs();

	@Query("SELECT new com.poly.asm_nhom_6.model.ReportLike(p, SIZE(p.thichSanPhams)) " +
			"FROM SanPham p ORDER BY RAND() LIMIT ?1")
	List<ReportLike> getAllSanPhamAndLike(Integer radom);

	SanPham findByMaSPLike(Integer maSP);
}
