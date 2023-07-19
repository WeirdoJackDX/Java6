package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "Hoa_Don")
public class HoaDon implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ma_hoa_don")
	Integer maHoaDon;

	@Column(name = "tien_giam")
	Integer tienGiam;

	@Temporal(TemporalType.DATE)
	@Column(name = "ngay_tao")
	Date ngayTao = new Date();

	@ManyToOne
	@JoinColumn(name = "ma_voucher")
	Voucher voucher;

	@ManyToOne
	@JoinColumn(name = "ma_nd")
	NguoiDung nguoiDung;

	@OneToMany(mappedBy = "hoaDon")
	List<ChiTietHoaDon> chiTietHoaDons;
}
