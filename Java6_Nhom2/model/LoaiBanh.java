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
@Table(name = "Loai_Banh")
public class LoaiBanh implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ma_loai_banh")
	public Integer maLoaiBanh;

	@Column(name = "ten_loai_banh")
	public String tenLoaiBanh;

	@Column(name = "ghi_chu")
	public String ghiChu;

	@OneToMany(mappedBy = "loaiBanh")
	List<SanPham> sanPhams;
	
	public LoaiBanh() {
		super();
	}

	public LoaiBanh(Integer maLoaiBanh, String tenLoaiBanh, String ghiChu, List<SanPham> sanPhams) {
		super();
		this.maLoaiBanh = maLoaiBanh;
		this.tenLoaiBanh = tenLoaiBanh;
		this.ghiChu = ghiChu;
		this.sanPhams = sanPhams;
	}

	public Integer getMaLoaiBanh() {
		return maLoaiBanh;
	}

	public void setMaLoaiBanh(Integer maLoaiBanh) {
		this.maLoaiBanh = maLoaiBanh;
	}

	public String getTenLoaiBanh() {
		return tenLoaiBanh;
	}

	public void setTenLoaiBanh(String tenLoaiBanh) {
		this.tenLoaiBanh = tenLoaiBanh;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public List<SanPham> getSanPhams() {
		return sanPhams;
	}

	public void setSanPhams(List<SanPham> sanPhams) {
		this.sanPhams = sanPhams;
	}
	
	
}
