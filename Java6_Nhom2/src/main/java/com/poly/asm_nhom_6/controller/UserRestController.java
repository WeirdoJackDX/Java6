package com.poly.asm_nhom_6.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.asm_nhom_6.DAO.NguoiDungDAO;
import com.poly.asm_nhom_6.model.NguoiDung;

@RestController
@CrossOrigin("*")
public class UserRestController {
    @Autowired
    NguoiDungDAO nguoiDungDAO;

    @GetMapping("/rest/users")
    public List<NguoiDung> getUsers() {
        return nguoiDungDAO.findAll();
    }
}
