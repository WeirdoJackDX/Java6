package com.poly.asm_nhom_6.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.poly.asm_nhom_6.DAO.LoaiBanhDAO;
import com.poly.asm_nhom_6.DAO.SanPhamDAO;
import com.poly.asm_nhom_6.model.SanPham;

@CrossOrigin("*")
@RestController
public class SanPhamRestController {
    @Autowired
    SanPhamDAO sanPhamDAO;
    @Autowired
    LoaiBanhDAO loaiBanhDAO;

    @GetMapping("/rest/sanpham")
    public Map<String, Object> getALL() {
        Map<String, Object> map = new HashMap<>();
        map.put("sanpham", sanPhamDAO.findAll());
        map.put("loai", loaiBanhDAO.findAll());
        return map;
    }

    @GetMapping("/rest/sanpham/{id}")
    public SanPham getOne(@PathVariable("id") Integer id) {
        return sanPhamDAO.findById(id).get();
    }

    @PutMapping("/rest/sanpham/{id}")
    public SanPham update(@PathVariable("id") Integer id, @RequestBody SanPham sanpham) {
        return sanPhamDAO.save(sanpham);
    }

    @PostMapping("/rest/sanpham/save")
    public SanPham save(@RequestBody SanPham sanPham) {
        System.out.println(sanPham);
        return sanPhamDAO.save(sanPham);
    }

    @DeleteMapping("/rest/delete/{id}")
    public void delete(@PathVariable("id") Integer id) {
        sanPhamDAO.deleteById(id);
    }
}
