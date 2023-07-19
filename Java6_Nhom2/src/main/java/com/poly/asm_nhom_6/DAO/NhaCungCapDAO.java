package com.poly.asm_nhom_6.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.asm_nhom_6.model.NhaCungCap;

public interface NhaCungCapDAO extends JpaRepository<NhaCungCap, Integer> {
	@Query("select o from NhaCungCap o where o.maNhaCungCap like ?1")
	List<NhaCungCap> findbykeywords(String key);
}
