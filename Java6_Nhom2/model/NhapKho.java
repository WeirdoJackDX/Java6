package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
@Table(name = "Nhap_Kho")
public class NhapKho implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ma_nhap_kho")
	public Integer maNhapKho;

	@Column(name = "so_luong_nhap")
	public Integer soLuongNhap;

	@Column(name = "gia_nhap_tren_moi_cai")
	public Integer giaNhapTrenMoiCai;

	@Column(name = "ghi_chu")
	public String ghiChu;

	@Temporal(TemporalType.DATE)
	@Column(name = "ngay_nhap")
	public Date ngayNhap = new Date();

	@ManyToOne
	@JoinColumn(name = "ma_sp")
	public SanPham sanPham;

	@ManyToOne
	@JoinColumn(name = "ma_nha_cung_cap")
	public NhaCungCap nhaCungCap;
	
	
	public NhapKho() {
		super();
	}


	public NhapKho(Integer maNhapKho, Integer soLuongNhap, Integer giaNhapTrenMoiCai, String ghiChu, Date ngayNhap,
			SanPham sanPham, NhaCungCap nhaCungCap) {
		super();
		this.maNhapKho = maNhapKho;
		this.soLuongNhap = soLuongNhap;
		this.giaNhapTrenMoiCai = giaNhapTrenMoiCai;
		this.ghiChu = ghiChu;
		this.ngayNhap = ngayNhap;
		this.sanPham = sanPham;
		this.nhaCungCap = nhaCungCap;
	}


	public Integer getMaNhapKho() {
		return maNhapKho;
	}


	public void setMaNhapKho(Integer maNhapKho) {
		this.maNhapKho = maNhapKho;
	}


	public Integer getSoLuongNhap() {
		return soLuongNhap;
	}


	public void setSoLuongNhap(Integer soLuongNhap) {
		this.soLuongNhap = soLuongNhap;
	}


	public Integer getGiaNhapTrenMoiCai() {
		return giaNhapTrenMoiCai;
	}


	public void setGiaNhapTrenMoiCai(Integer giaNhapTrenMoiCai) {
		this.giaNhapTrenMoiCai = giaNhapTrenMoiCai;
	}


	public String getGhiChu() {
		return ghiChu;
	}


	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}


	public Date getNgayNhap() {
		return ngayNhap;
	}


	public void setNgayNhap(Date ngayNhap) {
		this.ngayNhap = ngayNhap;
	}


	public SanPham getSanPham() {
		return sanPham;
	}


	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}


	public NhaCungCap getNhaCungCap() {
		return nhaCungCap;
	}


	public void setNhaCungCap(NhaCungCap nhaCungCap) {
		this.nhaCungCap = nhaCungCap;
	}
	
	
}
