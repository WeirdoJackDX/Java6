package com.poly.asm_nhom_6.DAO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.asm_nhom_6.model.NhapKho;

public interface NhapKhoDAO extends JpaRepository<NhapKho, Integer> {
	@Query("select o from NhapKho o where o.ghiChu like ?1")
	Page<NhapKho> findbykeywordtenKHS(String ghiChu, Pageable pageable);
}
