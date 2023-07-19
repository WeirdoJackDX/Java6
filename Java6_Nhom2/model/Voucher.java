package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "Voucher")
public class Voucher implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ma_voucher")
	public Integer maVoucher;

	@Column(name = "ten_voucher")
	public String tenVoucher;

	@Column(name = "tien_giam")
	Integer tienGiam;

	@Column(name = "mo_ta")
	public String moTa;

	@Column(name = "giam_toi_da")
	public Integer giamToiDa;

	@Column(name = "phan_tram_giam")
	public Integer phanTramGiam;

	@Column(name = "gia_tri_toi_thieu")
	public Integer giaTriToiThieu;

	@Column(name = "loai_voucher")
	public String loaiVoucher;

	@Temporal(TemporalType.DATE)
	@Column(name = "hsd")
	public Date hsd = new Date();

	@OneToMany(mappedBy = "voucher")
	List<HoaDon> hoaDons;

	@OneToMany(mappedBy = "voucher")
	List<YourVoucher> yourVouchers;

	
	public Voucher() {
		super();
	}


	public Voucher(Integer maVoucher, String tenVoucher, Integer tienGiam, String moTa, Integer giamToiDa,
			Integer phanTramGiam, Integer giaTriToiThieu, String loaiVoucher, Date hsd, List<HoaDon> hoaDons,
			List<YourVoucher> yourVouchers) {
		super();
		this.maVoucher = maVoucher;
		this.tenVoucher = tenVoucher;
		this.tienGiam = tienGiam;
		this.moTa = moTa;
		this.giamToiDa = giamToiDa;
		this.phanTramGiam = phanTramGiam;
		this.giaTriToiThieu = giaTriToiThieu;
		this.loaiVoucher = loaiVoucher;
		this.hsd = hsd;
		this.hoaDons = hoaDons;
		this.yourVouchers = yourVouchers;
	}


	public Integer getMaVoucher() {
		return maVoucher;
	}


	public void setMaVoucher(Integer maVoucher) {
		this.maVoucher = maVoucher;
	}


	public String getTenVoucher() {
		return tenVoucher;
	}


	public void setTenVoucher(String tenVoucher) {
		this.tenVoucher = tenVoucher;
	}


	public Integer getTienGiam() {
		return tienGiam;
	}


	public void setTienGiam(Integer tienGiam) {
		this.tienGiam = tienGiam;
	}


	public String getMoTa() {
		return moTa;
	}


	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}


	public Integer getGiamToiDa() {
		return giamToiDa;
	}


	public void setGiamToiDa(Integer giamToiDa) {
		this.giamToiDa = giamToiDa;
	}


	public Integer getPhanTramGiam() {
		return phanTramGiam;
	}


	public void setPhanTramGiam(Integer phanTramGiam) {
		this.phanTramGiam = phanTramGiam;
	}


	public Integer getGiaTriToiThieu() {
		return giaTriToiThieu;
	}


	public void setGiaTriToiThieu(Integer giaTriToiThieu) {
		this.giaTriToiThieu = giaTriToiThieu;
	}


	public String getLoaiVoucher() {
		return loaiVoucher;
	}


	public void setLoaiVoucher(String loaiVoucher) {
		this.loaiVoucher = loaiVoucher;
	}


	public Date getHsd() {
		return hsd;
	}


	public void setHsd(Date hsd) {
		this.hsd = hsd;
	}


	public List<HoaDon> getHoaDons() {
		return hoaDons;
	}


	public void setHoaDons(List<HoaDon> hoaDons) {
		this.hoaDons = hoaDons;
	}


	public List<YourVoucher> getYourVouchers() {
		return yourVouchers;
	}


	public void setYourVouchers(List<YourVoucher> yourVouchers) {
		this.yourVouchers = yourVouchers;
	}
	
	
}
