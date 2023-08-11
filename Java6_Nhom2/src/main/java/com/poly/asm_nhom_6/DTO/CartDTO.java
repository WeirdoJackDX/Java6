package com.poly.asm_nhom_6.DTO;

import com.poly.asm_nhom_6.model.GioHangChiTiet;

import lombok.Data;

@Data
public class CartDTO {
    GioHangChiTiet gioHangChiTiet;
    Boolean isChecked = false;
}
