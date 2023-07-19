package com.poly.asm_nhom_6.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.asm_nhom_6.model.NguoiDung;

public interface NguoiDungDAO extends JpaRepository<NguoiDung, Integer> {

	@Query("SELECT o FROM NguoiDung o Where o.taiKhoan Like  ?1")
	NguoiDung findByTaiKhoanAdmin(String taikhoan);

	@Query("SELECT o FROM NguoiDung o Where o.taiKhoan Like  ?1 AND o.matKhau Like ?2")
	List<NguoiDung> findForAuthenticateAdmin(String TaiKhoan, String matkhau);

	Page<NguoiDung> findAllByVaiTro(Integer vaiTro, Pageable pageable);

	@Query("SELECT o FROM NguoiDung o Where o.taiKhoan Like  ?1 ")
	List<NguoiDung> findForAuthenticateAdminMoi(String TaiKhoan);

	NguoiDung findByTaiKhoanAndMatKhauLike(String taiKhoan, String matKhau);

	@Query("SELECT o FROM NguoiDung o Where o.vaiTro = 0")
	List<NguoiDung> findMaNguoiDung();

	NguoiDung findByMaNDLike(Integer maND);

	NguoiDung findByEmailLike(String email);

	NguoiDung findByTaiKhoanLike(String taiKhoan);

	@Query("SELECT o FROM NguoiDung o Where o.vaiTro = 0 ")
	List<NguoiDung> findTenNguoiDung();

	@Query("SELECT o FROM NguoiDung o WHERE o.hoTen LIKE ?1")
	Page<NguoiDung> findByMoTa1(String hoTen, Pageable pageable);

}
