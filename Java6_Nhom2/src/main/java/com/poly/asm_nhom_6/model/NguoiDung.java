package com.poly.asm_nhom_6.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
@Table(name = "Nguoi_Dung")
public class NguoiDung implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "ma_nd")
    Integer maND;

    @Column(name = "tai_khoan")
    String taiKhoan;

    @Column(name = "mat_khau")
    String matKhau;

    @Column(name = "ho_ten")
    String hoTen;

    @Column(name = "dia_chi")
    String diaChi;

    String sdt;
    String email;

    @Column(name = "gioi_tinh")
    Boolean gioiTinh;

    @Column(name = "vai_tro")
    Integer vaiTro;

    @JsonIgnore
    @OneToMany(mappedBy = "nguoiDung")
    List<DanhGia> danhGias;

    @JsonIgnore
    @OneToMany(mappedBy = "nguoiDung")
    List<HoaDon> hoaDons;

    @JsonIgnore
    @OneToMany(mappedBy = "nguoiDung")
    List<YourVoucher> yourVouchers;

    @JsonIgnore
    @OneToMany(mappedBy = "nguoiDung")
    List<ThichSanPham> thichSanPhams;

    @JsonIgnore
    @OneToMany(mappedBy = "nguoiDung")
    List<GioHangChiTiet> gioHangChiTiets;
}
