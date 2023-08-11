package com.poly.asm_nhom_6.Restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.asm_nhom_6.DAO.LoaiBanhDAO;
import com.poly.asm_nhom_6.model.LoaiBanh;

@RestController
public class Admin {
	@Autowired
	LoaiBanhDAO loaiBanhDAO;
	
	@GetMapping("/rest/loaibanh")
	public List<LoaiBanh> getAll(Model model){
		List<LoaiBanh> list =  loaiBanhDAO.findAll();
		model.addAttribute("list", list);
		System.out.println(list);
		return list;
		
	}
}
