package com.poly.asm_nhom_6.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.poly.asm_nhom_6.DAO.GioHangChiTietDAO;
import com.poly.asm_nhom_6.DAO.NguoiDungDAO;
import com.poly.asm_nhom_6.model.GioHangChiTiet;
import com.poly.asm_nhom_6.model.NguoiDung;

import jakarta.servlet.http.HttpSession;

@CrossOrigin("*")
@RestController
public class CartRestController {
    @Autowired
    HttpSession session;

    @Autowired
    NguoiDungDAO nguoiDungDAO;

    @Autowired
    GioHangChiTietDAO gioHangChiTietDAO;

    @GetMapping("/rest/cart")
    public Map<String, Object> getCart() {
        Map<String, Object> map = new HashMap<>();
        NguoiDung nguoiDung = (NguoiDung) session.getAttribute("nguoiDung");
        nguoiDung = nguoiDungDAO.findById(nguoiDung.getMaND()).get();
        map.put("user", nguoiDung);
        map.put("products", nguoiDung.getGioHangChiTiets());
        return map;
    }

    @GetMapping("/rest/cart/{id}")
    public GioHangChiTiet getOne(@PathVariable("id") Integer id) {
        return gioHangChiTietDAO.findById(id).get();
    }

    @PutMapping("/rest/cart/{id}")
    public GioHangChiTiet put(@PathVariable("id") Integer id, @RequestBody GioHangChiTiet ghct) {
        return gioHangChiTietDAO.save(ghct);
    }

    @DeleteMapping("/rest/cart/{id}")
    public void delete(@PathVariable("id") Integer id) {
        gioHangChiTietDAO.deleteById(id);
    }
}
