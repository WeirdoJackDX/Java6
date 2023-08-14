package com.poly.asm_nhom_6.controller;

import java.util.List;

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
import com.poly.asm_nhom_6.model.LoaiBanh;

@CrossOrigin("*")
@RestController
public class LoaiSPRestController {
    @Autowired
    LoaiBanhDAO loaiBanhDAO;

    @GetMapping("/rest/loaiSp")
    public List<LoaiBanh> getAllLoaiBanh() {
        List<LoaiBanh> list = loaiBanhDAO.findAll();
        return list;
    }

    @GetMapping("/rest/loaiSp/{id}")
    public LoaiBanh getOne(@PathVariable("id") Integer id) {
        return loaiBanhDAO.findById(id).get();
    }

    @PutMapping("/rest/loaiSp/{id}")
    public LoaiBanh update(@PathVariable("id") Integer id, @RequestBody LoaiBanh loaiBanh) {
        return loaiBanhDAO.save(loaiBanh);
    }

    @PostMapping("rest/loaiSp")
    public LoaiBanh save(@RequestBody LoaiBanh loaiBanh) {
        return loaiBanhDAO.save(loaiBanh);
    }

    @DeleteMapping("/rest/delete/loai/{id}")
    public void delete(@PathVariable("id") Integer id) {
        loaiBanhDAO.deleteById(id);
    }
}
