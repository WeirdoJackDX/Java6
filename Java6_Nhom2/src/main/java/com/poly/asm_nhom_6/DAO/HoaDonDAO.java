package com.poly.asm_nhom_6.DAO;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.asm_nhom_6.model.HoaDon;
import com.poly.asm_nhom_6.model.Report;

public interface HoaDonDAO extends JpaRepository<HoaDon, Integer> {
	@Query("SELECT o FROM HoaDon o WHERE o.maHoaDon LIKE ?1")
	Page<HoaDon> findByMaHoaDon1(Integer maHoaDon, Pageable pageable);

	@Query("SELECT hd FROM HoaDon hd INNER JOIN hd.nguoiDung nd WHERE nd.hoTen LIKE ?1")
	Page<HoaDon> findByHoTenContaining(String hoTen, Pageable pageable);

	@Query("Select p FROM HoaDon p ORDER BY p.maHoaDon DESC limit 5")
	public List<HoaDon> hoadonganday();

	@Query("SELECT new Report(p.maHoaDon, p.nguoiDung.maND, p.ngayTao)" + " FROM HoaDon p"
			+ " GROUP BY p.maHoaDon, p.nguoiDung.maND,p.ngayTao ORDER BY p.ngayTao DESC LIMIT 5")
	List<Report> getListSP();
	@Query("SELECT p FROM HoaDon p WHERE p.nguoiDung.maND = ?1 ORDER BY p.maHoaDon DESC")
	Page<HoaDon> findHoaDonByMaND(Integer maND, Pageable pageable);

	@Query("SELECT p FROM HoaDon p WHERE p.nguoiDung.maND LIKE ?1 ORDER BY p.maHoaDon DESC LIMIT 1")
	HoaDon getRecentReceipt(Integer id);
}
