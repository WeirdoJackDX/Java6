package com.poly.asm_nhom_6.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.asm_nhom_6.model.LoaiBanh;

public interface LoaiBanhDAO extends JpaRepository<LoaiBanh, Integer> {
	@Query("select o from LoaiBanh o where o.maLoaiBanh like ?1")
	List<LoaiBanh> findbykeywords(String key);

	Page<LoaiBanh> findAll(Pageable pageable);
	
	@Query("SELECT o FROM LoaiBanh o WHERE o.tenLoaiBanh LIKE ?1")
	Page<LoaiBanh> findByMoTa1(String tenLoaiBanh, Pageable pageable);
}
