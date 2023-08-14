package com.poly.asm_nhom_6.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.asm_nhom_6.DAO.HoaDonDAO;
import com.poly.asm_nhom_6.DTO.InvoiceDTO;
import com.poly.asm_nhom_6.model.ChiTietHoaDon;
import com.poly.asm_nhom_6.model.HoaDon;

@RestController
@CrossOrigin("*")
public class InvoiceRestController {
    @Autowired
    HoaDonDAO hoaDonDAO;

    @GetMapping(value = "/rest/invoice")
    public List<InvoiceDTO> findAll() {
        List<InvoiceDTO> invoices = new ArrayList<>();
        for (HoaDon hd : hoaDonDAO.findAll()) {
            Double amount = 0.0;
            for (ChiTietHoaDon cthd : hd.getChiTietHoaDons()) {
                amount += cthd.getGiaBan() * cthd.getSoLuong();
            }
            InvoiceDTO item = new InvoiceDTO(hd, amount);
            invoices.add(item);
        }
        return invoices;
    }

}
