package com.poly.asm_nhom_6.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.asm_nhom_6.DAO.HoaDonDAO;
import com.poly.asm_nhom_6.model.HoaDon;
import com.poly.asm_nhom_6.service.NguoiDungService;
import com.poly.asm_nhom_6.service.SessionService;
import com.poly.asm_nhom_6.service.VoucherService;

@Controller
public class OrderController {
	@Autowired
	HoaDonDAO dao;

	@Autowired
	NguoiDungService NDoptionService;

	@Autowired
	VoucherService VCoptionService;

	@Autowired
	SessionService sessionService;

	@RequestMapping("/staff/order/index")
	public String index(Model model, @RequestParam("p") Optional<Integer> p) {

		Map<Integer, String> VCoptions = VCoptionService.getAllOptions();
		Map<Integer, String> NDoptions = NDoptionService.getAllOptions();
		var numberOfRecords = dao.count();
		var numberOfPages = (int) Math.ceil(numberOfRecords / 5.0);
		model.addAttribute("numberOfPages", numberOfPages);
		Pageable sort = PageRequest.of(p.orElse(0), 5, Sort.by("maHoaDon").ascending());
		model.addAttribute("currIndex", p.orElse(0));

		HoaDon hd = new HoaDon();
		var items = dao.findAll(sort);
		model.addAttribute("items", items);
		model.addAttribute("hoaDon", hd);
		model.addAttribute("VCoptions", VCoptions);
		model.addAttribute("NDoptions", NDoptions);
		return "staff/order";
	}

	@RequestMapping("/staff/order/edit/{maHoaDon}")
	public String edit(@ModelAttribute("hoaDon") HoaDon item, Model model, @PathVariable("maHoaDon") Integer mahd,
			@RequestParam("p") Optional<Integer> p) {
		Map<Integer, String> VCoptions = VCoptionService.getAllOptions();
		Map<Integer, String> NDoptions = NDoptionService.getAllOptions();
		// Phân trang *
		var numberOfRecords = dao.count();
		var numberOfPages = (int) Math.ceil(numberOfRecords / 5.0);
		model.addAttribute("numberOfPages", numberOfPages);
		Pageable sort = PageRequest.of(p.orElse(0), 5, Sort.by("maHoaDon").ascending());
		model.addAttribute("currIndex", p.orElse(0));
		// *
		HoaDon hoaDon = dao.findById(mahd).get();
		var items = dao.findAll(sort);
		model.addAttribute("items", items);
		model.addAttribute("hoaDon", hoaDon);
		model.addAttribute("VCoptions", VCoptions);
		model.addAttribute("NDoptions", NDoptions);
		return "staff/order";
	}

	@RequestMapping("/createoder")
	public String create(@ModelAttribute("hoaDon") HoaDon item) {
		dao.save(item);
		return "redirect:/staff/order";
	}

	@RequestMapping("/updateorder")
	public String update(@ModelAttribute("hoaDon") HoaDon item, Model model) {
		Map<Integer, String> VCoptions = VCoptionService.getAllOptions();
		Map<Integer, String> NDoptions = NDoptionService.getAllOptions();
		List<HoaDon> items = dao.findAll();
		dao.save(item);
		model.addAttribute("items", items);
		model.addAttribute("hoaDon", item);
		model.addAttribute("VCoptions", VCoptions);
		model.addAttribute("NDoptions", NDoptions);
		return "redirect:/staff/order";
	}

	@RequestMapping("/staff/order/delete/{maHoaDon}")
	public String create(@PathVariable("maHoaDon") Integer mahd) {
		dao.deleteById(mahd);
		return "redirect:/staff/order";
	}

	@GetMapping("/order/page")
	public String paginate(@ModelAttribute("hoaDon") HoaDon item, Model model, @RequestParam("p") Optional<Integer> p) {
		return this.index(model, p);
	}

	@RequestMapping("/staff/order/search")
	public String search(Model model, @RequestParam("p") Optional<Integer> p,
			@RequestParam("hoTen") Optional<String> ht) {
		String hotens = ht.orElse("");
		Pageable pageable = PageRequest.of(p.orElse(0), 5);
		Page<HoaDon> items = dao.findByHoTenContaining("%" + hotens + "%", pageable);
		var numberOfPages = (int) items.getTotalPages();
		model.addAttribute("numberOfPages", numberOfPages);
		model.addAttribute("currIndex", p.orElse(0));
		HoaDon hd = new HoaDon();
		model.addAttribute("hoaDon", hd);
		model.addAttribute("items", items);
		return "/staff/order";
	}
}
