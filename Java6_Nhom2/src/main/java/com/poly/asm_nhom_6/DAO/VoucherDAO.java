package com.poly.asm_nhom_6.DAO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.asm_nhom_6.model.Voucher;

public interface VoucherDAO extends JpaRepository<Voucher, Integer> {
	@Query("select o from Voucher o where o.tenVoucher like ?1")
	Page<Voucher> findbykeywordtenvoucher(String tenvoucher, Pageable pageable);
}
