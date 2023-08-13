package com.poly.asm_nhom_6.model;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "Thich_San_Pham")
public class ThichSanPham implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ma_thich_san_pham")
	Integer maThichSanPham;

	@Temporal(TemporalType.DATE)
	@Column(name = "ngay_thich")
	Date ngayThich = new Date();

	@ManyToOne
	@JoinColumn(name = "ma_nd")
	NguoiDung nguoiDung;

	@ManyToOne
	@JoinColumn(name = "ma_sp")
	SanPham sanPham;

	public ThichSanPham(NguoiDung nguoiDung, SanPham sanPham) {
		this.ngayThich = new Date();
		this.nguoiDung = nguoiDung;
		this.sanPham = sanPham;
	}

}
