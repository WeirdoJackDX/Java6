package com.poly.asm_nhom_6.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poly.asm_nhom_6.model.LoaiBanh;

import groovyjarjarpicocli.CommandLine.Parameters;

import com.poly.asm_nhom_6.DAO.LoaiBanhDAO;

@Controller
public class AdminContrller {

	@Autowired
	LoaiBanhDAO loaiBanhdao;
	@GetMapping("/trangchu/index")
	public String indexAdmin() {
		return "admin/index";
	}
	
	//bill table loai san pham
	@RequestMapping("/trangchu/loaisanpham")
	public String loaiSp(Model model, @ModelAttribute("loai") LoaiBanh loai) {
		List<LoaiBanh> list = loaiBanhdao.findAll();
		model.addAttribute("loaibanh", list);
		return "admin/loaisanpham";
	}
	
	//Lấy id Loai san phẩm
	@RequestMapping("/trangchu/loaisanpham/edit/{id}")
	public String editLoai(Model model, @PathVariable("id") Integer id) {
		//System.out.println(id);
		LoaiBanh loai = loaiBanhdao.findById(id).get();
		model.addAttribute("loai", loai);
		List<LoaiBanh> list = loaiBanhdao.findAll();
		model.addAttribute("loaibanh", list);
		return "admin/loaisanpham";
	}
	
	//Thêm laoi sản phẩm
	@RequestMapping("/trangchu/loaisanpham/add/submit")
	public String Add(Model model, @ModelAttribute("loai") LoaiBanh loai,@RequestParam("ghiChu") String ghiChu) {
		if(loai.getTenLoaiBanh().equals("") || loai.getGhiChu().equals("")) {
			model.addAttribute("message", "Vui lòng nhập đầy đủ thông tin");
			//return "redirect:/trangchu/loaisanpham";
		}else {
			loai.setGhiChu(ghiChu);
			loaiBanhdao.save(loai);
			
		}
		System.out.println(loai.getGhiChu());
		List<LoaiBanh> list = loaiBanhdao.findAll();
		model.addAttribute("loaibanh", list);
		return "admin/loaisanpham";
	}
	
	//Xóa loại
	@RequestMapping("/trangchu/loaisanpham/delete/{id}")
	public String Delete(Model model,@PathVariable("id") Integer id) {
		LoaiBanh loai = loaiBanhdao.findById(id).get();
		loaiBanhdao.delete(loai);
		List<LoaiBanh> list = loaiBanhdao.findAll();
		model.addAttribute("loaibanh", list);
		return "redirect:/trangchu/loaisanpham";
	}
	
	@RequestMapping("/trangchu/loaisanpham/reset/submit")
	public String Reset(Model model, @ModelAttribute("loai") LoaiBanh loai) {
		loai.setSanPhams(null);
		loai.setGhiChu(null);
		return  "redirect:/trangchu/loaisanpham";
	}
	
}
