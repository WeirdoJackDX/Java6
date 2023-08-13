package com.poly.asm_nhom_6.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.asm_nhom_6.DAO.NguoiDungDAO;
import com.poly.asm_nhom_6.model.NguoiDung;

@CrossOrigin("*")
@RestController
public class NguoiDungRestcontroller {
    @Autowired
    NguoiDungDAO nguoiDungDAO;

    @GetMapping("/rest/nguoidung")
    public List<NguoiDung> getAllNguoidung(){
        List<NguoiDung> list = nguoiDungDAO.findAll();
        return list;
    }

}
