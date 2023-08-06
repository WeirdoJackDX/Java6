package com.poly.asm_nhom_6.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.asm_nhom_6.model.ReportLike;
import com.poly.asm_nhom_6.model.ThichSanPham;

public interface ThichSanPhamDAO extends JpaRepository<ThichSanPham, Integer> {
        @Query("SELECT o FROM ThichSanPham o WHERE o.nguoiDung.maND like ?1 AND o.sanPham.maSP like ?2")
        ThichSanPham findByMaNDAndMaSPLike(Integer maND, Integer maSP);

        @Query("SELECT new com.poly.asm_nhom_6.model.ReportLike(o, COUNT(p), SUM(CASE WHEN p.nguoiDung.maND = ?1 THEN 1 ELSE 0 END))"
                        + "FROM SanPham o LEFT JOIN ThichSanPham p ON o = p.sanPham WHERE o.soLuong <> 0 AND o.isAvailable = TRUE GROUP BY o ORDER BY RAND() LIMIT ?2")
        List<ReportLike> getAllSanPhamAndLike(Integer maND, Integer soLuongNgauNhien);

        @Query("SELECT new com.poly.asm_nhom_6.model.ReportLike(o, COUNT(p), SUM(CASE WHEN p.nguoiDung.maND = ?1 THEN 1 ELSE 0 END))"
                        + "FROM SanPham o LEFT JOIN ThichSanPham p ON o = p.sanPham WHERE o.soLuong <> 0 AND o.loaiBanh.maLoaiBanh like ?2 GROUP BY o ORDER BY RAND() LIMIT ?3")
        List<ReportLike> getAllSanPhamAndLikeTheoLoai(Integer maND, Integer maLoai, Integer soLuongNgauNhien);

        @Query("SELECT new com.poly.asm_nhom_6.model.ReportLike(o, COUNT(p), SUM(CASE WHEN p.nguoiDung.maND = ?1 THEN 1 ELSE 0 END)) "
                        + "FROM SanPham o LEFT JOIN ThichSanPham p ON o = p.sanPham "
                        + "WHERE o.maSP = ?2 GROUP BY o ")
        // + "HAVING SUM(CASE WHEN p.nguoiDung.maND = ?1 THEN 1 ELSE 0 END) > 0")
        ReportLike getAllSanPhamAndLikes_maSP(Integer maND, Integer maSP);
        // ReportLike getAllSanPhamAndLikes_maSP(Integer maND, Integer maSP);

        @Query("SELECT new com.poly.asm_nhom_6.model.ReportLike(o, COUNT(p), SUM(CASE  WHEN p.nguoiDung.maND = :maND THEN 1 ELSE 0 END))"
                        + "FROM SanPham o LEFT JOIN ThichSanPham p ON o = p.sanPham WHERE o.tenSP LIKE %:keyWord% AND o.soLuong <> 0 AND o.isAvailable = TRUE GROUP BY o")
        Page<ReportLike> getAllSanPham(Integer maND, String keyWord, Pageable pageable);

        @Query("SELECT new com.poly.asm_nhom_6.model.ReportLike(o, COUNT(p), SUM(CASE WHEN p.nguoiDung.maND = ?1 THEN 1 ELSE 0 END))"
                        + "FROM SanPham o LEFT JOIN ThichSanPham p ON o = p.sanPham WHERE o.soLuong <> 0 AND o.isAvailable = TRUE GROUP BY o")
        Page<ReportLike> getAllSanPhamNe(Integer maND, Pageable pageable);
}
