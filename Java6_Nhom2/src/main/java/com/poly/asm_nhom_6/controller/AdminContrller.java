package com.poly.asm_nhom_6.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.poly.asm_nhom_6.DAO.ChiTietHoaDonDAO;
import com.poly.asm_nhom_6.DAO.HoaDonDAO;
import com.poly.asm_nhom_6.DAO.LoaiBanhDAO;
import com.poly.asm_nhom_6.DAO.NguoiDungDAO;
import com.poly.asm_nhom_6.DAO.SanPhamDAO;
import com.poly.asm_nhom_6.model.HoaDon;
import com.poly.asm_nhom_6.model.LoaiBanh;
import com.poly.asm_nhom_6.model.Report;
import com.poly.asm_nhom_6.model.SanPham;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class AdminContrller {

	@Autowired
	LoaiBanhDAO loaiBanhdao;
	@Autowired
	SanPhamDAO sanphamdao;
	@Autowired
	NguoiDungDAO nguoiDungDAO;
	@Autowired
	HoaDonDAO hoaDonDAO;
	@Autowired
	ChiTietHoaDonDAO chiTietHoaDonDAO;

	//////////////////////////////////////
	@GetMapping("/trangchu/index")
	public String indexAdmin(Model model) {
		model.addAttribute("tongSP", sanphamdao.findAll().size());
		model.addAttribute("tongKH", nguoiDungDAO.findAll().size());
		model.addAttribute("tongHD", hoaDonDAO.findAll().size());
		model.addAttribute("tongDT", chiTietHoaDonDAO.tongDoanhThu());
		List<Report> listHD = hoaDonDAO.getListSP();
		model.addAttribute("listHD",listHD);
		System.out.println(listHD);
		return "admin/index";
	}

	// ==============================LOẠI SAN
	// PHAM========================================//
	// bill table
	@RequestMapping("/trangchu/loaisanpham")
	public String loaiSp(Model model, @ModelAttribute("loai") LoaiBanh loai) {
		List<LoaiBanh> list = loaiBanhdao.findAll();
		model.addAttribute("loaibanh", list);
		return "admin/loaisanpham";
	}

	// Lấy id loại
	@RequestMapping("/trangchu/loaisanpham/edit/{id}")
	public String editLoai(Model model, @PathVariable("id") Integer id) {
		LoaiBanh loai = loaiBanhdao.findById(id).get();
		model.addAttribute("loai", loai);
		List<LoaiBanh> list = loaiBanhdao.findAll();
		model.addAttribute("loaibanh", list);
		return "admin/loaisanpham";
	}

	// Thêm loại
	@RequestMapping("/trangchu/loaisanpham/add/submit")
	public String Add(Model model, @ModelAttribute("loai") LoaiBanh loai, @RequestParam("ghiChu") String ghiChu) {
		if (loai.getTenLoaiBanh().equals("") || loai.getGhiChu().equals("")) {
			model.addAttribute("message", "Vui lòng nhập đầy đủ thông tin");
			// return "redirect:/trangchu/loaisanpham";
		} else {
			loai.setGhiChu(ghiChu);
			loaiBanhdao.save(loai);
			Reset(loai);

		}
		System.out.println(loai.getGhiChu());
		List<LoaiBanh> list = loaiBanhdao.findAll();
		model.addAttribute("loaibanh", list);
		return "admin/loaisanpham";
	}

	// update loại
	@RequestMapping("trangchu/loaisanpham/edit/submit")
	public String Update(@ModelAttribute("loai") LoaiBanh loai) {
		loaiBanhdao.save(loai);
		return "redirect:/trangchu/loaisanpham";

	}

	// Xóa loại
	@RequestMapping("/trangchu/loaisanpham/delete/{id}")
	public String Delete(Model model, @PathVariable("id") Integer id) {
		LoaiBanh loai = loaiBanhdao.findById(id).get();
		loaiBanhdao.delete(loai);
		List<LoaiBanh> list = loaiBanhdao.findAll();
		model.addAttribute("loaibanh", list);
		return "redirect:/trangchu/loaisanpham";
	}

	// Làm mới loại
	@RequestMapping("/trangchu/loaisanpham/reset/submit")
	public String Reset(@ModelAttribute("loai") LoaiBanh loai) {
		loai.setSanPhams(null);
		loai.setGhiChu(null);
		return "redirect:/trangchu/loaisanpham";
	}

	// =================================SẢN
	// PHẨM=====================================//
	// Phân trang
	@GetMapping("/trangchu/sanpham")
	public String SanPham(Model model, @ModelAttribute("sp") SanPham sp, HttpServletRequest request,
			RedirectAttributes redirect) {
		request.getSession().setAttribute("splist", null);
		return "redirect:/trangchu/sanpham/page/1";
	}

	@GetMapping("/trangchu/sanpham/page/{pageNumber}")
	public String showPage(Model model, HttpServletRequest request, @PathVariable int pageNumber) {

		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("splist");
		int pageSize = 8;
		List<SanPham> list = sanphamdao.findAll();
		if (pages == null) {
			pages = new PagedListHolder<>(list);
			pages.setPageSize(pageSize);
		} else {
			final int goToPage = pageNumber - 1;
			if (goToPage <= pages.getPageCount() && goToPage >= 0) {
				pages.setPage(goToPage);
			}
		}
		request.getSession().setAttribute("splist", pages);
		int current = pages.getPage() + 1;
		int begin = Math.max(1, current - list.size());
		int end = Math.max(begin + 5, pages.getPageCount());
		int totalPageCount = pages.getPageCount();
		String baseUrl = "/trangchu/sanpham/page/";

		model.addAttribute("begin", begin);
		model.addAttribute("end", end);
		model.addAttribute("current", current);
		model.addAttribute("total", totalPageCount);
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("sanpham", pages);

		return "admin/sanpham";
	}

	// Id sản phẩm
	@GetMapping("/trangchu/sanpham/edit/{id}")
	public String editSanpham(Model model, @PathVariable("id") Integer id) {
		List<LoaiBanh> loaibanh = loaiBanhdao.findAll();
		model.addAttribute("loaibanh", loaibanh);
		SanPham sp = sanphamdao.findById(id).get();
		model.addAttribute("sp", sp);
		return "admin/editSanPham";
	}

	// update sản phẩm
	@PostMapping("trangchu/sanpham/edit/submit")
	public String updateSp(Model model, @Valid @ModelAttribute("sp") SanPham sp, BindingResult result,
			@RequestParam("image") MultipartFile img, RedirectAttributes redirect) throws IOException {

		if (result.hasErrors()) {
			List<LoaiBanh> loaisp = loaiBanhdao.findAll();
			model.addAttribute("loaisp", loaisp);
			return "admin/editsanpham";
		} else {
			if (!img.isEmpty()) {
				String filename = img.getOriginalFilename();
				File file = new ClassPathResource("static/img/").getFile();
				Path path = Paths.get(file.getAbsolutePath() + File.separator + filename);
				Files.copy(img.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
				sp.setAnh("../img" + "/" + filename);
			} else {
				SanPham sanpham = sanphamdao.findById(sp.getMaSP()).get();
				sp.setAnh(sanpham.getAnh());
			}
		}
		sanphamdao.save(sp);

		return "redirect:/trangchu/sanpham";
	}

	// page thêm sản phẩm
	@GetMapping("trangchu/sanpham/add")
	public String themSanpham(Model model, @ModelAttribute("sp") SanPham sp) {
		List<LoaiBanh> loaisp = loaiBanhdao.findAll();
		model.addAttribute("loaisp", loaisp);
		return "admin/editsanpham";
	}

	// Thêm sản phẩm
	@PostMapping("trangchu/sanpham/add/submit")
	public String themSanphamSubmit(Model model, @Valid @ModelAttribute("sp") SanPham sp, BindingResult result,
			@RequestParam("image") MultipartFile img) throws Exception {
		if (result.hasErrors() || img.isEmpty()) {
			List<LoaiBanh> loaisp = loaiBanhdao.findAll();
			model.addAttribute("loaisp", loaisp);
			if (img.isEmpty()) {
				model.addAttribute("anh", "Ảnh không được trống");
			}
			return "admin/editsanpham";
		} else {
			String filename = img.getOriginalFilename();
			File file = new ClassPathResource("static/img/").getFile();
			Path path = Paths.get(file.getAbsolutePath() + File.separator + filename);
			Files.copy(img.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			sp.setAnh("../img" + "/" + filename);
			System.out.print(sp.getTenSP());
			sanphamdao.save(sp);
			return "redirect:/trangchu/sanpham";

		}
	}

	// Ẩn sản phẩm
	@RequestMapping("/trangchu/sanpham/delete/{id}")
	public String deleteSP(Model model, @PathVariable("id") Integer id) {
		SanPham sanpham = sanphamdao.findById(id).get();
		sanpham.setIsAvailable(false);
		sanphamdao.save(sanpham);
		return "redirect:/trangchu/sanpham";
	}

	// Hiện sản phẩm
	@RequestMapping("/trangchu/sanpham/return/{id}")
	public String returnSP(Model model, @PathVariable("id") Integer id) {
		SanPham sanpham = sanphamdao.findById(id).get();
		sanpham.setIsAvailable(true);
		sanphamdao.save(sanpham);
		return "redirect:/trangchu/sanpham";

	}
}
