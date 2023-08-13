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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
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
