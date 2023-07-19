package com.example.demo.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_sp")
    public Integer maSP;

    @Column(name = "ten_sp")
    public String tenSP;

    @Column(name = "so_luong")
    public Integer soLuong;// được hiểu là số lượng còn trong kho và kệ trưng bày

    public String anh;

    @Column(name = "ghi_chu")
    public String ghiChu;

    @Column(name = "gia_ban")
    public Integer giaBan;

    @Column(name = "gia_nhap")
    public Integer giaNhap;

    @OneToMany(mappedBy = "sanPham")
    List<GioHangChiTiet> gioHangChiTiets;

    @OneToMany(mappedBy = "sanPham")
    List<DanhGia> danhGias;

    @OneToMany(mappedBy = "sanPham")
    List<ChiTietHoaDon> chiTietHoaDons;

    @OneToMany(mappedBy = "sanPham")
    List<ThichSanPham> thichSanPhams;

    @OneToMany(mappedBy = "sanPham")
    List<NhapKho> nhapKhos;

    @ManyToOne
    @JoinColumn(name = "ma_loai_banh")
    public LoaiBanh loaiBanh;

    
	public SanPham() {
		super();
	}


	public SanPham(Integer maSP, String tenSP, Integer soLuong, String anh, String ghiChu, Integer giaBan,
			Integer giaNhap, List<GioHangChiTiet> gioHangChiTiets, List<DanhGia> danhGias,
			List<ChiTietHoaDon> chiTietHoaDons, List<ThichSanPham> thichSanPhams, List<NhapKho> nhapKhos,
			LoaiBanh loaiBanh) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.soLuong = soLuong;
		this.anh = anh;
		this.ghiChu = ghiChu;
		this.giaBan = giaBan;
		this.giaNhap = giaNhap;
		this.gioHangChiTiets = gioHangChiTiets;
		this.danhGias = danhGias;
		this.chiTietHoaDons = chiTietHoaDons;
		this.thichSanPhams = thichSanPhams;
		this.nhapKhos = nhapKhos;
		this.loaiBanh = loaiBanh;
	}


	public Integer getMaSP() {
		return maSP;
	}


	public void setMaSP(Integer maSP) {
		this.maSP = maSP;
	}


	public String getTenSP() {
		return tenSP;
	}


	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}


	public Integer getSoLuong() {
		return soLuong;
	}


	public void setSoLuong(Integer soLuong) {
		this.soLuong = soLuong;
	}


	public String getAnh() {
		return anh;
	}


	public void setAnh(String anh) {
		this.anh = anh;
	}


	public String getGhiChu() {
		return ghiChu;
	}


	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}


	public Integer getGiaBan() {
		return giaBan;
	}


	public void setGiaBan(Integer giaBan) {
		this.giaBan = giaBan;
	}


	public Integer getGiaNhap() {
		return giaNhap;
	}


	public void setGiaNhap(Integer giaNhap) {
		this.giaNhap = giaNhap;
	}


	public List<GioHangChiTiet> getGioHangChiTiets() {
		return gioHangChiTiets;
	}


	public void setGioHangChiTiets(List<GioHangChiTiet> gioHangChiTiets) {
		this.gioHangChiTiets = gioHangChiTiets;
	}


	public List<DanhGia> getDanhGias() {
		return danhGias;
	}


	public void setDanhGias(List<DanhGia> danhGias) {
		this.danhGias = danhGias;
	}


	public List<ChiTietHoaDon> getChiTietHoaDons() {
		return chiTietHoaDons;
	}


	public void setChiTietHoaDons(List<ChiTietHoaDon> chiTietHoaDons) {
		this.chiTietHoaDons = chiTietHoaDons;
	}


	public List<ThichSanPham> getThichSanPhams() {
		return thichSanPhams;
	}


	public void setThichSanPhams(List<ThichSanPham> thichSanPhams) {
		this.thichSanPhams = thichSanPhams;
	}


	public List<NhapKho> getNhapKhos() {
		return nhapKhos;
	}


	public void setNhapKhos(List<NhapKho> nhapKhos) {
		this.nhapKhos = nhapKhos;
	}


	public LoaiBanh getLoaiBanh() {
		return loaiBanh;
	}


	public void setLoaiBanh(LoaiBanh loaiBanh) {
		this.loaiBanh = loaiBanh;
	}

    
}
