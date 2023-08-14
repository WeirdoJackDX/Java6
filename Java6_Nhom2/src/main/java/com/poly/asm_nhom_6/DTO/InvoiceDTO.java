package com.poly.asm_nhom_6.DTO;

import com.poly.asm_nhom_6.model.HoaDon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDTO {
    HoaDon hoaDon;
    Double tongTien;
}
