package com.poly.asm_nhom_6.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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
@Table(name = "Gio_Hang_Chi_Tiet")
public class GioHangChiTiet implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_gio_hang")
    public Integer maGioHang;

    @NotNull(message = "Vui lòng nhập số lượng")
    @Min(value = 1, message = "Số lượng phải lớn hơn 0")
    @Column(name = "so_luong")
    Integer soLuong;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "Không chọn trước ngày hiện tại")
    @Temporal(TemporalType.DATE)
    @Column(name = "ngay_them")
    Date ngayThem = new Date();

    @ManyToOne
    @JoinColumn(name = "ma_nd")
    NguoiDung nguoiDung;

    @ManyToOne
    @JoinColumn(name = "ma_sp")
    SanPham sanPham;
}
