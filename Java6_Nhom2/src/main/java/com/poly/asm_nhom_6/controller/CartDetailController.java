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
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.asm_nhom_6.DAO.GioHangChiTietDAO;
import com.poly.asm_nhom_6.model.GioHangChiTiet;
import com.poly.asm_nhom_6.service.NguoiDungService;
import com.poly.asm_nhom_6.service.OptionServiceSanPham;
import com.poly.asm_nhom_6.service.SessionService;

import javax.mail.internet.ParseException;

@Controller
public class CartDetailController {
	@Autowired
	GioHangChiTietDAO dao;
	@Autowired
	SessionService sessionService;

	@Autowired
	OptionServiceSanPham SPoptionService;
	@Autowired
	NguoiDungService NDoptionService;

	@RequestMapping("/staff/cart-detail")
	public String index(Model model, @RequestParam("p") Optional<Integer> p) {

		Map<Integer, String> SPoptions = SPoptionService.getAllOptions();
		Map<Integer, String> NDoptions = NDoptionService.getAllOptions();
		var numberOfRecords = dao.count();
		var numberOfPages = (int) Math.ceil(numberOfRecords / 5.0);
		model.addAttribute("numberOfPages", numberOfPages);
		Pageable sort = PageRequest.of(p.orElse(0), 5, Sort.by("maGioHang").ascending());
		model.addAttribute("currIndex", p.orElse(0));

		GioHangChiTiet gh = new GioHangChiTiet();
		var items = dao.findAll(sort);
		model.addAttribute("items", items);
		model.addAttribute("gioHang", gh);
		model.addAttribute("SPoptions", SPoptions);
		model.addAttribute("NDoptions", NDoptions);
		return "/staff/cart-detail";
	}

	@RequestMapping("/staff/cart-detail/edit/{maGioHang}")
	public String edit(@ModelAttribute("gioHang") GioHangChiTiet item, Model model,
			@PathVariable("maGioHang") Integer magh, @RequestParam("p") Optional<Integer> p) {
		Map<Integer, String> SPoptions = SPoptionService.getAllOptions();
		Map<Integer, String> NDoptions = NDoptionService.getAllOptions();
		// Phân trang *
		var numberOfRecords = dao.count();
		var numberOfPages = (int) Math.ceil(numberOfRecords / 5.0);
		model.addAttribute("numberOfPages", numberOfPages);
		Pageable sort = PageRequest.of(p.orElse(0), 5, Sort.by("maGioHang").ascending());
		model.addAttribute("currIndex", p.orElse(0));
		// *
		GioHangChiTiet gioHang = dao.findById(magh).get();
		var items = dao.findAll(sort);
		model.addAttribute("items", items);
		model.addAttribute("gioHang", gioHang);
		model.addAttribute("SPoptions", SPoptions);
		model.addAttribute("NDoptions", NDoptions);
		return "staff/cart-detail";
	}

	@PostMapping("/create")
	public String create(@Validated @ModelAttribute("gioHang") GioHangChiTiet item, BindingResult rs, Model model,
			@RequestParam("p") Optional<Integer> p) throws ParseException {
		if (rs.hasErrors()) {
			Map<Integer, String> SPoptions = SPoptionService.getAllOptions();
			Map<Integer, String> NDoptions = NDoptionService.getAllOptions();
			var numberOfRecords = dao.count();
			var numberOfPages = (int) Math.ceil(numberOfRecords / 5.0);
			model.addAttribute("numberOfPages", numberOfPages);
			Pageable sort = PageRequest.of(p.orElse(0), 5, Sort.by("maGioHang").ascending());
			model.addAttribute("currIndex", p.orElse(0));
			var items = dao.findAll(sort);
			model.addAttribute("items", items);
			model.addAttribute("SPoptions", SPoptions);
			model.addAttribute("NDoptions", NDoptions);
			return "/staff/cart-detail";
		}
		dao.save(item);
		return "redirect:/staff/cart-detail";
	}

	@RequestMapping("/update")
	public String update(@ModelAttribute("gioHang") GioHangChiTiet item, Model model) {
		Map<Integer, String> SPoptions = SPoptionService.getAllOptions();
		Map<Integer, String> NDoptions = NDoptionService.getAllOptions();
		List<GioHangChiTiet> items = dao.findAll();
		dao.save(item);
		model.addAttribute("items", items);
		model.addAttribute("gioHang", item);
		model.addAttribute("SPoptions", SPoptions);
		model.addAttribute("NDoptions", NDoptions);
		return "redirect:/staff/cart-detail";
	}

	@RequestMapping("/staff/cart-detail/delete/{maGioHang}")
	public String create(@PathVariable("maGioHang") Integer magh) {
		dao.deleteById(magh);
		return "redirect:/staff/cart-detail";
	}

	@GetMapping("/cart-detail/page")
	public String paginate(@ModelAttribute("gioHang") GioHangChiTiet item, Model model,
			@RequestParam("p") Optional<Integer> p) {
		return this.index(model, p);
	}

	@RequestMapping("/staff/cart-detail/search")
	public String search1(Model model, @RequestParam("p") Optional<Integer> p,
			@RequestParam("hoTen") Optional<String> ht) {
		String hotens = ht.orElse("");
		Pageable pageable = PageRequest.of(p.orElse(0), 5);
		Page<GioHangChiTiet> items = dao.findByHoTen("%" + hotens + "%", pageable);
		var numberOfPages = (int) items.getTotalPages();
		model.addAttribute("numberOfPages", numberOfPages);
		GioHangChiTiet gh = new GioHangChiTiet();
		model.addAttribute("currIndex", p.orElse(0));
		model.addAttribute("gioHang", gh);
		model.addAttribute("items", items);
		return "/staff/cart-detail";
	}
}
