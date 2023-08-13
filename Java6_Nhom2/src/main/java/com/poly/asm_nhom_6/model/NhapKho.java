package com.poly.asm_nhom_6.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
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
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ma_nhap_kho")
	Integer maNhapKho;

	@NotNull(message = "khong duoc de trong so luong nhap")
	@Positive(message = "Khong duoc nhap so am trong so luong")
	@Column(name = "so_luong_nhap")
	Integer soLuongNhap;

	@NotNull(message = "khong duoc de trong gia nhap")
	@Positive(message = "Khong duoc nhap so am trong gia nhap moi cai")
	@Column(name = "gia_nhap_tren_moi_cai")
	Integer giaNhapTrenMoiCai;

	@NotBlank(message = "{Blank.ghiChuNhapKho}")
	@Column(name = "ghi_chu")
	String ghiChu;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name = "ngay_nhap")
	public Date ngayNhap = new Date();

	@ManyToOne
	@JoinColumn(name = "ma_sp")
	public SanPham sanPham;

	@ManyToOne
	@JoinColumn(name = "ma_nha_cung_cap")
	public NhaCungCap nhaCungCap;
	
	
		
	
}
