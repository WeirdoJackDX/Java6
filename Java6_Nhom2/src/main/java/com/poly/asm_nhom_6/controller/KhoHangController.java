package com.poly.asm_nhom_6.controller;


import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.asm_nhom_6.DAO.NhapKhoDAO;
import com.poly.asm_nhom_6.model.NhapKho;
import com.poly.asm_nhom_6.model.SanPham;
import com.poly.asm_nhom_6.service.OptionServiceNhaCungCap;
import com.poly.asm_nhom_6.service.OptionServiceSanPham;

import jakarta.validation.Valid;

@Controller	
public class KhoHangController {
	@Autowired
	NhapKhoDAO nkdao; // làm việc với bảng kho hàng
    @Autowired
    public OptionServiceNhaCungCap NCCoptionService;
    @Autowired
    public OptionServiceSanPham SPoptionService;

    
//	@RequestMapping("/admin/NhapKho/index")
//	public String index(Model model, @RequestParam("p") Optional<Integer> p) {
//		Map<Integer, String> NCCoptions = NCCoptionService.getAllOptions();
//		Map<Integer, String> SPoptions = SPoptionService.getAllOptions();
//		var numberOfRecords = nkdao.count();
//		var numberOfPages = (int) Math.ceil(numberOfRecords / 5.0);
//		model.addAttribute("numberOfPages", numberOfPages);
//		Pageable sort = PageRequest.of(p.orElse(0), 5, Sort.by("maNhapKho").ascending());
//		model.addAttribute("currIndex", p.orElse(0));
//		NhapKho kh = new NhapKho();
//		var items = nkdao.findAll(sort);
//		model.addAttribute("items", items);
//		model.addAttribute("NhapKhothem", kh);
//		model.addAttribute("NCCoptions", NCCoptions);
//		model.addAttribute("SPoptions", SPoptions);
//		return "admin/NhapKho";
//	}
//
//	@RequestMapping("/admin/NhapKho/edit/{maNhapKho}")
//	public String edit(@ModelAttribute("NhapKhothem") NhapKho item, Model model, @PathVariable("maNhapKho") Integer manhapkho, @RequestParam("p") Optional<Integer> p) {
//    Map<Integer, String> NCCoptions = NCCoptionService.getAllOptions();
//    Map<Integer, String> SPoptions = SPoptionService.getAllOptions();
//    var numberOfRecords = nkdao.count();
//    var numberOfPages = (int) Math.ceil(numberOfRecords / 5.0);
//    model.addAttribute("numberOfPages", numberOfPages);
//    Pageable sort = PageRequest.of(p.orElse(0), 5, Sort.by("maNhapKho").ascending());
//    model.addAttribute("currIndex", p.orElse(0));
//	NhapKho nhapkhothem = nkdao.findById(manhapkho).get();
//	var items = nkdao.findAll(sort);	
//	model.addAttribute("items", items);
//	model.addAttribute("NhapKhothem", nhapkhothem);
//    model.addAttribute("NCCoptions", NCCoptions);
//    model.addAttribute("SPoptions", SPoptions);
//	return "admin/NhapKho";
//	}
//
//	@RequestMapping("/admin/NhapKho/create")
//	public String create(@Valid @ModelAttribute("NhapKhothem") NhapKho item, BindingResult bindingResult, Model model) {
//        Map<Integer, String> NCCoptions = NCCoptionService.getAllOptions();
//        Map<Integer, String> SPoptions = SPoptionService.getAllOptions();
//        if(bindingResult.hasErrors()) {
//	        var items = nkdao.findAll(PageRequest.of(0, 5, Sort.by("maNhapKho").ascending()));
//	        model.addAttribute("items", items);
//	        model.addAttribute("NCCoptions", NCCoptions);
//	        model.addAttribute("SPoptions", SPoptions);
//	        model.addAttribute("numberOfPages", items.getTotalPages());
//	        model.addAttribute("currIndex", 0);
//	        return "/admin/NhapKho";
//	    }
//		System.out.println(item.getMaNhapKho());
//		nkdao.save(item);
//		return "redirect:/admin/NhapKho/index";
//	}
//	@RequestMapping("/admin/NhapKho/update")
//	public String update(@ModelAttribute("NhapKho") NhapKho item, Model model)  {
//	    Map<Integer, String> NCCoptions = NCCoptionService.getAllOptions();
//	    Map<Integer, String> SPoptions = SPoptionService.getAllOptions();
//		List<NhapKho> items = nkdao.findAll();
//		nkdao.save(item);
//		model.addAttribute("items", items);
//		model.addAttribute("NhapKhothem", item);
//	    model.addAttribute("NCCoptions", NCCoptions);
//	    model.addAttribute("SPoptions", SPoptions);
//		return "redirect:/admin/NhapKho/index";
//	}
//
//	@RequestMapping("/admin/NhapKho/delete/{maNhaCungCap}")
//	public String create(@PathVariable("maNhaCungCap") Integer maNhaCungCap) {
//		nkdao.deleteById(maNhaCungCap);
//		return "redirect:/admin/NhapKho";
//	}
//
//	@GetMapping("/admin/NhapKho/page")
//	public String paginate(@ModelAttribute("NhapKhothem") SanPham item,Model model,@RequestParam("p") Optional<Integer> p){
//	        return this.index(model, p);
//	    }
}
