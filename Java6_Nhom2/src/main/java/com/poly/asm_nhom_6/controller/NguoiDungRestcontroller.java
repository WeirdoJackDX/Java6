package com.poly.asm_nhom_6.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.poly.asm_nhom_6.DAO.NguoiDungDAO;
import com.poly.asm_nhom_6.model.NguoiDung;

@CrossOrigin("*")
@RestController
public class NguoiDungRestcontroller {
    @Autowired
    NguoiDungDAO nguoiDungDAO;

    @GetMapping("/rest/nguoidung")
    public List<NguoiDung> getAllNguoidung() {
        List<NguoiDung> list = nguoiDungDAO.findTenNguoiDung();
        return list;
    }

    @PutMapping("/rest/nguoidung")
    public NguoiDung update(@RequestBody NguoiDung nguoiDung) {
        NguoiDung nd = nguoiDungDAO.findById(nguoiDung.getMaND()).get();
        if (nguoiDung.getIsBanned() == false) {
            nguoiDung.setIsBanned(true);
        } else {
            nguoiDung.setIsBanned(false);
        }
        return nguoiDungDAO.save(nguoiDung);
    }
}
