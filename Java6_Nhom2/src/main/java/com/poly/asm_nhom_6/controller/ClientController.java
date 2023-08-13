package com.poly.asm_nhom_6.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.asm_nhom_6.DAO.NguoiDungDAO;
import com.poly.asm_nhom_6.model.NguoiDung;

import javax.validation.Valid;

@Controller
public class ClientController {

	@Autowired
	NguoiDungDAO nguoiDungDAO;

	@GetMapping("/staff/client")
	public String index(NguoiDung item, Model model, @RequestParam("p") Optional<Integer> p,
			@RequestParam("field") Optional<String> field, @RequestParam("vaitro") Optional<Integer> vaitro) {
		Integer vaitro1 = vaitro.orElse(0);

		// List<NguoiDung> items = nguoiDungDAO.findAllByVaiTro(0);

		item = new NguoiDung();
		model.addAttribute("item", item);
		Pageable Pageable = PageRequest.of(p.orElse(0), 5, Sort.by(Direction.DESC, field.orElse("maND")).ascending());
		Page<NguoiDung> items = nguoiDungDAO.findAllByVaiTro(vaitro1, Pageable);
		var numberOfPages = items.getTotalPages();
		model.addAttribute("currIndex", p.orElse(0));
		model.addAttribute("numberOfPages", numberOfPages);
		model.addAttribute("items", items);
		return "staff/client";
	}

	@GetMapping("/staff/client/page")
	public String pagesp(NguoiDung item, Model model, @RequestParam("p") Optional<Integer> p,
			@RequestParam("field") Optional<String> field, @RequestParam("vaitro") Optional<Integer> vaitro) {
		return this.index(item, model, p, field, vaitro);
	}

	@RequestMapping("/staff/listclient")
	public List<NguoiDung> list() {
		List<NguoiDung> listcategory = nguoiDungDAO.findAll();
		return listcategory;
	}

	@RequestMapping("/staff/edit/{nd}")
	public String edit(Model model, @PathVariable("nd") Integer nd, @RequestParam("p") Optional<Integer> p,
			@RequestParam("field") Optional<String> field, @RequestParam("vaitro") Optional<Integer> vaitro) {
		NguoiDung item = nguoiDungDAO.findById(nd).get();
		Integer vaitro1 = vaitro.orElse(0);

		model.addAttribute("item", item);
		Pageable Pageable = PageRequest.of(p.orElse(0), 5, Sort.by(Direction.DESC, field.orElse("maND")).ascending());
		Page<NguoiDung> items = nguoiDungDAO.findAllByVaiTro(vaitro1, Pageable);
		var numberOfPages = items.getTotalPages();
		model.addAttribute("currIndex", p.orElse(0));
		model.addAttribute("numberOfPages", numberOfPages);
		model.addAttribute("items", items);
		return "staff/client";
	}

	@PostMapping("/staff/create")
	public String create(@Valid @ModelAttribute("item") NguoiDung item, Model model, BindingResult result) {
		item.setVaiTro(0);
		nguoiDungDAO.save(item);
		return "redirect:/staff/client";
	}

	@PostMapping("/staff/update")
	public String update(@Valid @ModelAttribute("item") NguoiDung item, Model model) {
		nguoiDungDAO.save(item);
		return "redirect:/staff/edit/" + item.getMaND();
	}

	@RequestMapping("/staff/delete/{nd}")
	public String create(@PathVariable("nd") Integer nd) {
		nguoiDungDAO.deleteById(nd);
		return "redirect:/staff/client";
	}

	@ModelAttribute("genders")
	public Map<Boolean, String> getGenders() {
		Map<Boolean, String> map = new HashMap<>();
		map.put(true, "Nam");
		map.put(false, "Nữ");
		return map;

	}

	@PostMapping("/staff/clear")
	public String clear(@ModelAttribute("item") NguoiDung item) {
		item.setMaND(1);
		item.setTaiKhoan(null);
		item.setHoTen(null);
		item.setDiaChi(null);
		item.setSdt(null);
		item.setEmail(null);
		item.setGioiTinh(null);
		item.setVaiTro(1);
		return "redirect:/staff/client";
	}

	@RequestMapping("/staff/client/search")
	public String search(Model model, @RequestParam("p") Optional<Integer> p,
			@RequestParam("hoTen") Optional<String> ht, @ModelAttribute("item") NguoiDung item) {
		String hotens = ht.orElse("");
		Pageable pageable = PageRequest.of(p.orElse(0), 5);
		Page<NguoiDung> items = nguoiDungDAO.findByMoTa1("%" + hotens + "%", pageable);
		var numberOfPages = (int) items.getTotalPages();
		model.addAttribute("numberOfPages", numberOfPages);
		model.addAttribute("currIndex", p.orElse(0));
		NguoiDung dg = new NguoiDung();
		model.addAttribute("nguoiDung", dg);
		model.addAttribute("items", items);
		return "/staff/client";
	}

}