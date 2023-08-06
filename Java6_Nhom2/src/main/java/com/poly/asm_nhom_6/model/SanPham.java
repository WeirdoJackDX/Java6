package com.poly.asm_nhom_6.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "San_Pham")
public class SanPham implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// @Autowired
    // SanPhamDAO dao;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_sp")
    Integer maSP;

    @NotBlank(message = "{Blank.tensanpham}")
    @Column(name = "ten_sp")
    String tenSP;

    @Positive(message = "{Blank.soluongsanpham}")
    @NotNull(message = "Khong duoc de trong so luong")
    @Column(name = "so_luong")
    Integer soLuong;// được hiểu là số lượng còn trong kho và kệ trưng bày

    String anh;

    @NotBlank(message = "{Blank.ghichusanpham}")
    @Column(name = "ghi_chu")
    String ghiChu;

    @Positive(message = "{Blank.giaban}")
    @NotNull(message = "khong duoc de trong gia ban")
    @Column(name = "gia_ban")
    Integer giaBan;

    @Positive(message = "{Blank.gianhap}")
    @NotNull(message = "Khong duoc de trong gia nhap")
    @Column(name = "gia_nhap")
    Integer giaNhap;

    @Column(name = "availability")
    Boolean isAvailable;

    @JsonIgnore
    @OneToMany(mappedBy = "sanPham")
    @JsonBackReference
    List<GioHangChiTiet> gioHangChiTiets;

    @JsonIgnore
    @OneToMany(mappedBy = "sanPham")
    @JsonBackReference
    List<DanhGia> danhGias;

    @JsonIgnore
    @OneToMany(mappedBy = "sanPham")
    @JsonBackReference
    List<ChiTietHoaDon> chiTietHoaDons;

    @JsonIgnore
    @OneToMany(mappedBy = "sanPham")
    @JsonBackReference
    List<ThichSanPham> thichSanPhams;

    @JsonIgnore
    @OneToMany(mappedBy = "sanPham")
    @JsonBackReference
    List<NhapKho> nhapKhos;

    @ManyToOne
    @JoinColumn(name = "ma_loai_banh")
    @JsonBackReference
    public LoaiBanh loaiBanh;
}
