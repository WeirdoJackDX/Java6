package com.poly.asm_nhom_6.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Voucher")
public class Voucher implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ma_voucher")

	public Integer maVoucher;

	@NotBlank(message = "Khong duoc de trong")
	@Column(name = "ten_voucher")
	String tenVoucher;

	@Positive(message = "khong duoc nhap so am cho tien giam")
	@NotNull(message = "khong duoc de trong gia")
	@Column(name = "tien_giam")
	Integer tienGiam;

	@NotBlank(message = "Khong duoc de trong mo ta")
	@Column(name = "mo_ta")
	String moTa;

	@Positive(message = "khong duoc nhap so am cho gia giam toi da")
	@NotNull(message = "khong duoc de trong")
	@Column(name = "giam_toi_da")
	Integer giamToiDa;

	@Positive(message = "khong duoc nhap so am cho phan tram giam")
	@NotNull(message = "khong duoc de trong")
	@Column(name = "phan_tram_giam")
	Integer phanTramGiam;

	@Positive(message = "khong duoc nhap so am cho gia tri toi thieu")
	@NotNull(message = "khong duoc de trong")
	@Column(name = "gia_tri_toi_thieu")
	Integer giaTriToiThieu;

	@NotBlank(message = "Khong duoc de trong loai voucher")
	@Column(name = "loai_voucher")
	String loaiVoucher;

}
