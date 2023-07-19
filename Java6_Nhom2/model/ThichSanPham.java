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
@Table(name = "Thich_San_Pham")
public class ThichSanPham implements Serializable {
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
}
