package com.poly.asm_nhom_6.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

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
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
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
@Table(name = "Danh_Gia")
public class DanhGia implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ma_danh_gia")
	Integer maDanhGia;

	@Column(name = "sao_danh_gia")
	Integer saoDanhGia;

	@NotEmpty(message = "Vui lòng nhập mô tả")
	@Column(name = "mo_ta")
	public String moTa;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Past(message = "Không chọn trước ngày hiện tại")
	@Temporal(TemporalType.DATE)
	@Column(name = "ngay_danh_gia")
	public Date ngayDanhGia = new Date();

	@ManyToOne
	@JoinColumn(name = "ma_nd")
	public NguoiDung nguoiDung;

	@ManyToOne
	@JoinColumn(name = "ma_sp")

	public SanPham sanPham;

}
