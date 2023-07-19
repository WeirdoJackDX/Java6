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
@Table(name = "Danh_Gia")
public class DanhGia implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_danh_gia")
    Integer maDanhGia;

    @Column(name = "sao_danh_gia")
    Integer saoDanhGia;

    @Column(name = "mo_ta")
    String moTa;

    @Temporal(TemporalType.DATE)
    @Column(name = "ngay_danh_gia")
    Date ngayDanhGia = new Date();

    @ManyToOne
    @JoinColumn(name = "ma_nd")
    NguoiDung nguoiDung;

    @ManyToOne
    @JoinColumn(name = "ma_sp")
    SanPham sanPham;
}
