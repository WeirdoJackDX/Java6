package com.example.demo.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "Nha_Cung_Cap")
public class NhaCungCap implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ma_nha_cung_cap")
	public Integer maNhaCungCap;

	@Column(name = "ten_nha_cung_cap")
	public String tenNhaCungCap;

	@Column(name = "ghi_chu")
	public String ghiChu;

	@OneToMany(mappedBy = "nhaCungCap")
	List<NhapKho> nhapKhos;

	
	public NhaCungCap() {
		super();
	}


	public NhaCungCap(Integer maNhaCungCap, String tenNhaCungCap, String ghiChu, List<NhapKho> nhapKhos) {
		super();
		this.maNhaCungCap = maNhaCungCap;
		this.tenNhaCungCap = tenNhaCungCap;
		this.ghiChu = ghiChu;
		this.nhapKhos = nhapKhos;
	}


	public Integer getMaNhaCungCap() {
		return maNhaCungCap;
	}


	public void setMaNhaCungCap(Integer maNhaCungCap) {
		this.maNhaCungCap = maNhaCungCap;
	}


	public String getTenNhaCungCap() {
		return tenNhaCungCap;
	}


	public void setTenNhaCungCap(String tenNhaCungCap) {
		this.tenNhaCungCap = tenNhaCungCap;
	}


	public String getGhiChu() {
		return ghiChu;
	}


	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}


	public List<NhapKho> getNhapKhos() {
		return nhapKhos;
	}


	public void setNhapKhos(List<NhapKho> nhapKhos) {
		this.nhapKhos = nhapKhos;
	}
	
	
	
}
