package com.poly.asm_nhom_6.DAO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.asm_nhom_6.model.HoaDon;

public interface HoaDonDAO extends JpaRepository<HoaDon, Integer> {
	@Query("SELECT o FROM HoaDon o WHERE o.maHoaDon LIKE ?1")
	Page<HoaDon> findByMaHoaDon1(Integer maHoaDon, Pageable pageable);

	@Query("SELECT hd FROM HoaDon hd INNER JOIN hd.nguoiDung nd WHERE nd.hoTen LIKE ?1")
	Page<HoaDon> findByHoTenContaining(String hoTen, Pageable pageable);

	@Query("SELECT p FROM HoaDon p WHERE p.nguoiDung.maND LIKE ?1 ORDER BY p.maHoaDon DESC LIMIT 1")
	HoaDon getRecentReceipt(Integer id);
}
