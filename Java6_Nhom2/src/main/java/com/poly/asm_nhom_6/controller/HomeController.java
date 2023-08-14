package com.poly.asm_nhom_6.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poly.asm_nhom_6.model.ChiTietHoaDon;
import com.poly.asm_nhom_6.model.GioHangChiTiet;
import com.poly.asm_nhom_6.model.HoaDon;
import com.poly.asm_nhom_6.model.NguoiDung;
import com.poly.asm_nhom_6.model.ReportLike;
import com.poly.asm_nhom_6.model.SanPham;
import com.poly.asm_nhom_6.model.ThichSanPham;
import com.poly.asm_nhom_6.service.ChiTietHoaDonService;
import com.poly.asm_nhom_6.service.GioHangChiTietService;
import com.poly.asm_nhom_6.service.HoaDonService;
import com.poly.asm_nhom_6.service.MailerServiceImpl;
import com.poly.asm_nhom_6.service.NguoiDungService;
import com.poly.asm_nhom_6.service.SanPhamService;
import com.poly.asm_nhom_6.service.ThichSanPhamService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	Boolean dangKy = false;

	Boolean dangXuat = false;

	Boolean dangNhap = false;

	@Autowired
	HttpSession session;

	@Autowired
	SanPhamService sanPhamService;

	@Autowired
	ThichSanPhamService thichSanPhamService;

	@Autowired
	NguoiDungService nguoiDungService;

	@Autowired
	GioHangChiTietService gioHangChiTietService;

	@Autowired
	HoaDonService hoaDonService;

	@Autowired
	ChiTietHoaDonService chiTietHoaDonService;

	@Autowired
	MailerServiceImpl mailer;

	public void demo(String email, String subject, String body) {
		mailer.queue(email, subject, body);
	}

	@GetMapping("/user/search")
	public String search2(Model model) {
		session.setAttribute("search", "");
		Pageable pageable = PageRequest.of(0, 12);
		Page<ReportLike> page = filt(pageable);
		model.addAttribute("page", page);
		header(model);
		return "user/shop";
	}

	@RequestMapping("/user/search")
	public String search(Model model, @RequestParam("keyWords") String keyWord) {
		session.setAttribute("search", keyWord);
		Pageable pageable = PageRequest.of(0, 12);
		Page<ReportLike> page = filt(pageable);
		model.addAttribute("page", page);
		header(model);
		return "user/shop";
	}

	public Page<ReportLike> filt(Pageable pageable) {
		String keyWord = (String) session.getAttribute("search");
		NguoiDung nguoiDung = (NguoiDung) session.getAttribute("nguoiDung");
		Page<ReportLike> page = thichSanPhamService.getAllSanPham(nguoiDung == null ? 0 : nguoiDung.getMaND(),
				keyWord == null ? "" : keyWord, pageable);
		return page;
	}

	@RequestMapping("/user/shop/search")
	public String searhShop(Model model, @RequestParam("tuKhoa") String tuKhoa) {
		header(model);
		Pageable pageable = PageRequest.of(0, 12);
		Page<ReportLike> page = filt(pageable);
		model.addAttribute("page", page);
		return "user/shop";
	}

	@RequestMapping("/user/shop/page")
	public String pageShop(Model model, @RequestParam("p") Optional<Integer> p) {
		header(model);
		Pageable pageable = PageRequest.of(p.orElse(0), 12);
		Page<ReportLike> page = filt(pageable);
		model.addAttribute("page", page);
		return "user/shop";
	}

	@RequestMapping("/user/signup/check")
	public String check(@ModelAttribute("nguoiDung2") NguoiDung nguoiDung, @RequestParam("matKhau2") String matKhau2,
			@RequestParam("matKhau3") String matKhau3, Model model) {
		header(model);
		NguoiDung ND = new NguoiDung();
		if (!matKhau2.equals(matKhau3)) {
			model.addAttribute("message", "Xác nhận mật khẩu không chính xác!");
		} else {
			nguoiDung.setMatKhau(matKhau2);
			ND = nguoiDungService.findByEmailLike(nguoiDung.getEmail());
			if (ND != null) {
				model.addAttribute("message", "Email đã được sử dụng!");
			} else {
				ND = nguoiDungService.findByTaiKhoanLike(nguoiDung.getTaiKhoan());
				if (ND != null) {
					model.addAttribute("message", "Tài khoản đã được đăng kí!");
				} else {
					demo(nguoiDung.getEmail(), "", "");
					nguoiDung.setVaiTro(0);
					nguoiDung.setIsBanned(false);
					nguoiDungService.save(nguoiDung);
					this.dangKy = true;
					return "redirect:/user/home/index";
				}
			}
		}
		return "user/signup";
	}

	public void header(Model model) {
		NguoiDung nguoiDung = (NguoiDung) session.getAttribute("nguoiDung");
		if (nguoiDung != null && nguoiDung.getVaiTro() == 1) {
			model.addAttribute("test", 0);
			session.removeAttribute("nguoiDung");
			dangNhap = false;
			nguoiDung = new NguoiDung();
		}

		String tuKhoa = (String) session.getAttribute("keyWord");

		model.addAttribute("keyWords", tuKhoa);

		if (dangXuat) {
			model.addAttribute("dangXuat", dangXuat);
			dangXuat = false;
		}

		if (dangNhap) {
			model.addAttribute("dangNhap", dangNhap);
			dangNhap = false;
		}

		if (this.dangKy) {
			model.addAttribute("dangKy", true);
			this.dangKy = false;
		}

		try {
			if (nguoiDung != null && nguoiDung.getMaND() != null) {
				Long soLuongSanPham = gioHangChiTietService.countCartById(nguoiDung.getMaND());
				model.addAttribute("test", soLuongSanPham);
				model.addAttribute("nguoiDung", nguoiDung);
			} else {
				model.addAttribute("test", 0);
				model.addAttribute("nguoiDung", new NguoiDung());
			}
		} catch (Exception e) {
			model.addAttribute("test", 0);
			model.addAttribute("nguoiDung", new NguoiDung());
		}
	}

	@ResponseBody
	@PostMapping("/user/cart/add")
	public Map<String, Object> cartAdd(Model model, @RequestParam("maSP") Integer maSP,
			@RequestParam("maND") Integer maND, @RequestParam("soLuongSanPham") Integer soLuongSanPham) {
		Map<String, Object> response = new HashMap<>();
		NguoiDung nguoiDung = nguoiDungService.findByMaNDLike(maND);
		SanPham sanPham = sanPhamService.findByMaSPLike(maSP);
		GioHangChiTiet gioHangChiTiet = gioHangChiTietService.findByMaNDAndMaGioHang(maND, maSP);
		if (sanPham.getSoLuong() < soLuongSanPham) {
			response.put("message", "Số lượng bánh không đủ để cung cấp!");
		} else {
			response.put("message", "0");
			if (gioHangChiTiet == null) {
				gioHangChiTietService.save(new GioHangChiTiet(maND, soLuongSanPham, new Date(), nguoiDung, sanPham));
			} else {
				if (gioHangChiTiet.getSoLuong() + soLuongSanPham > sanPham.getSoLuong()) {
					response.put("message",
							"Số lượng bánh không đủ cung cấp!\n Giỏ hàng của bạn đã vượt qua số lượng của cửa hàng chúng tôi!");
				} else {
					Integer soLuongMoi = gioHangChiTiet.getSoLuong() + soLuongSanPham;
					gioHangChiTiet.setSoLuong(soLuongMoi);
					gioHangChiTietService.save(gioHangChiTiet);
				}

			}
		}
		response.put("success", true);
		Long amount = gioHangChiTietService.countCartById(maND);
		response.put("val", amount);
		return response;
	}

	@ResponseBody
	@PostMapping("/user/cart/updateMinus")
	public Long cartUpdateMinus(Model model, @RequestParam("maGH") Integer maGH) {
		NguoiDung nguoiDung = (NguoiDung) session.getAttribute("nguoiDung");
		GioHangChiTiet ghct = gioHangChiTietService.findById(maGH);
		if (ghct.getSoLuong() > 1) {
			ghct.setSoLuong(ghct.getSoLuong() - 1);
			gioHangChiTietService.save(ghct);
		}
		Long tienBanh = gioHangChiTietService.totalPrice(nguoiDung.getMaND());
		return tienBanh;
	}

	@ResponseBody
	@PostMapping("/user/cart/updatePlus")
	public Long cartUpdatePlus(Model model, @RequestParam("maGH") Integer maGH) {
		NguoiDung nguoiDung = (NguoiDung) session.getAttribute("nguoiDung");
		GioHangChiTiet ghct = gioHangChiTietService.findById(maGH);
		ghct.setSoLuong(ghct.getSoLuong() + 1);
		gioHangChiTietService.save(ghct);
		Long tienBanh = gioHangChiTietService.totalPrice(nguoiDung.getMaND());
		return tienBanh;
	}

	@ResponseBody
	@PostMapping("/user/cart/remove")
	public Map<String, Object> remove(@RequestParam("maGH") Integer maGH) {
		Map<String, Object> response = new HashMap<>();
		NguoiDung nguoiDung = (NguoiDung) session.getAttribute("nguoiDung");
		GioHangChiTiet ghct = gioHangChiTietService.findById(maGH);
		gioHangChiTietService.delete(ghct);
		Long tienBanh = gioHangChiTietService.totalPrice(nguoiDung.getMaND());
		Long amount = gioHangChiTietService.countCartById(nguoiDung.getMaND());
		response.put("tienBanh", tienBanh);
		response.put("val", amount);
		return response;
	}

	@ResponseBody
	@PostMapping("/user/logout")
	public Map<String, Object> logout() {
		session.removeAttribute("nguoiDung");
		session.removeAttribute("keyWord");
		Map<String, Object> response = new HashMap<>();
		response.put("success", true);
		response.put("message", "0");
		response.put("redirectUrl", "/user/home/index");
		dangXuat = true;
		return response;
	}

	@ResponseBody
	@PostMapping("/user/like")
	public Map<String, Object> like(@RequestParam("maSP") Integer maSP) {
		Map<String, Object> response = new HashMap<>();
		NguoiDung nguoiDung = (NguoiDung) session.getAttribute("nguoiDung");
		SanPham sanPham = sanPhamService.findByMaSPLike(maSP);
		ThichSanPham thichSanPham = thichSanPhamService.findByMaNDAndMaSPLike(nguoiDung.getMaND(), maSP);
		if (thichSanPham == null) {
			thichSanPhamService.save(new ThichSanPham(nguoiDung, sanPham));
			response.put("success", true);
			response.put("message", "0");
		} else {
			thichSanPhamService.delete(thichSanPham);
			response.put("success", true);
			response.put("message", "1");
		}
		return response;
	}

	@GetMapping("/user/detail/index")
	public String detail(@RequestParam("maSP") Integer maSP, @RequestParam("maLoai") Integer maLoai, Model model) {
		NguoiDung nguoiDung = (NguoiDung) session.getAttribute("nguoiDung");
		header(model);
		ReportLike reportLike = thichSanPhamService.getAllSanPhamAndLikes_maSP(
				nguoiDung == null ? 0 : nguoiDung.getMaND(),
				maSP);
		model.addAttribute("reportLike", reportLike);

		List<ReportLike> items = thichSanPhamService
				.getAllSanPhamAndLikeTheoLoai(nguoiDung == null ? 0 : (int) nguoiDung.getMaND(), maLoai, 8);
		model.addAttribute("items", items);

		return "user/detail";
	}

	@GetMapping("/user/home/index")
	public String index(Model model) {
		header(model);
		NguoiDung nguoiDung = (NguoiDung) session.getAttribute("nguoiDung");
		List<ReportLike> items = thichSanPhamService.getAllSanPhamAndLike(
				nguoiDung == null ? 0 : (int) nguoiDung.getMaND(),
				12);
		model.addAttribute("items", items);
		return "/user/index";
	}

	@GetMapping("/error404")
	public String error(Model model) {
		header(model);
		return "user/pageNotFound";
	}

	@ResponseBody
	@PostMapping("/user/login")
	public Map<String, Object> login(@RequestParam("taiKhoan") String taiKhoan,
			@RequestParam("matKhau") String matKhau) {
		Map<String, Object> response = new HashMap<>();

		NguoiDung nguoiDung = nguoiDungService.findByTaiKhoanAndMatKhauLike(taiKhoan, matKhau);
		// Kiểm tra tài khoản và mật khẩu
		if (nguoiDung != null && nguoiDung.getIsBanned() == true) {
			// Đăng nhập thành công
			session.setAttribute("nguoiDung", nguoiDung);
			response.put("success", true);
			dangNhap = true;
			if (nguoiDung.getVaiTro() == 0) {
				response.put("redirectUrl", "/user/home/index");
			} else {
				response.put("redirectUrl", "/trangchu/index");
			}
			// URL chuyển hướng về trang
			// home
		} else {
			// Đăng nhập thất bại
			response.put("success", false);
		}
		return response;
	}

	@RequestMapping("/user/signup/form")
	public String signup(Model model, @ModelAttribute("nguoiDung2") NguoiDung nguoiDung) {
		header(model);
		nguoiDung.setGioiTinh(true);
		return "user/signup";
	}

	@RequestMapping("/user/info")
	public String changeInfo(Model model, @ModelAttribute("user") NguoiDung nguoiDung) {
		header(model);
		nguoiDung = (NguoiDung) session.getAttribute("nguoiDung");
		NguoiDung user = nguoiDungService.findById(nguoiDung.getMaND());
		model.addAttribute("user", user);
		return "user/changeInfo";
	}

	@PostMapping("/user/info/check")
	public String infoCheck(Model model, @ModelAttribute("user") NguoiDung nguoiDung) {
		NguoiDung user = (NguoiDung) session.getAttribute("nguoiDung");
		user.setHoTen(nguoiDung.getHoTen());
		user.setEmail(nguoiDung.getEmail());
		user.setDiaChi(nguoiDung.getDiaChi());
		user.setSdt(nguoiDung.getSdt());
		user.setGioiTinh(nguoiDung.getGioiTinh());
		nguoiDungService.save(user);
		return "redirect:/user/home/index";
	}

	@ResponseBody
	@PostMapping("/user/cart/open")
	public Map<String, Object> openCart() {
		Map<String, Object> response = new HashMap<>();
		// Đăng nhập thất bại
		response.put("success", true);
		response.put("message", "0");
		response.put("redirectUrl", "/user/cart/index");
		return response;
	}

	@GetMapping("/user/cart/index")
	public String cart(Model model) {
		header(model);
		NguoiDung nguoiDung = (NguoiDung) session.getAttribute("nguoiDung");
		NguoiDung user = nguoiDungService.findById(nguoiDung.getMaND());
		Long tienBanh = gioHangChiTietService.totalPrice(nguoiDung.getMaND());
		model.addAttribute("user", user);
		model.addAttribute("tienBanh", tienBanh);
		return "user/cart";
	}

	@GetMapping("/user/checkout/index")
	public String checkout(Model model) {
		header(model);
		return "user/checkout";
	}

	@GetMapping("/user/contact/index")
	public String contact(Model model) {
		header(model);
		return "user/contact";
	}

	@RequestMapping("/user/cart/purchase")
	public String invoice() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		NguoiDung user = (NguoiDung) session.getAttribute("nguoiDung");
		user = nguoiDungService.findById(user.getMaND());
		HoaDon hd = new HoaDon(null, null, timestamp, null, user, null);
		hoaDonService.save(hd);
		for (GioHangChiTiet ghct : user.getGioHangChiTiets()) {
			ChiTietHoaDon cthd = new ChiTietHoaDon(null, ghct.getSoLuong(), ghct.getSanPham().getGiaBan(),
					ghct.getSanPham().getGiaNhap(), hd, ghct.getSanPham());
			chiTietHoaDonService.save(cthd);
			SanPham sp = sanPhamService.findById(ghct.getSanPham().getMaSP());
			sp.setSoLuong(sp.getSoLuong() - ghct.getSoLuong());
			sanPhamService.save(sp);
			gioHangChiTietService.delete(ghct);
		}
		HoaDon recent = hoaDonService.getRecentReceipt(user.getMaND());
		return "redirect:/user/invoice/" + recent.getMaHoaDon().toString();
	}

	@RequestMapping("/user/invoice/{id}")
	public String invoiceDetail(@PathVariable("id") Integer id, Model model) {
		header(model);
		HoaDon hd = hoaDonService.findById(id);
		NguoiDung user = (NguoiDung) session.getAttribute("nguoiDung");
		user = nguoiDungService.findById(user.getMaND());
		if (hd.getNguoiDung().getMaND().equals(user.getMaND()) || user.getVaiTro() == 1) {
			model.addAttribute("user", user);
			model.addAttribute("hd", hd);
			return "/user/invoiceDetail";
		} else {
			return "redirect:/error404";
		}
	}

	@RequestMapping("/user/invoice")
	public String invoice(Model model, @RequestParam("p") Optional<Integer> p) {
		header(model);
		NguoiDung user = (NguoiDung) session.getAttribute("nguoiDung");
		Pageable pageable = PageRequest.of(p.orElse(0), 4);
		Page<HoaDon> hds = hoaDonService.findHoaDonByMaND(user.getMaND(), pageable);
		var numberOfPages = hds.getTotalPages();
		model.addAttribute("invoice", hds);
		model.addAttribute("currIndex", p.orElse(0));
		model.addAttribute("numberOfPages", numberOfPages);
		return "/user/invoiceList";
	}

	@ResponseBody
	@PostMapping("/user/saveKeyWords")
	public void hehe(@RequestParam("keyWord") String keyWord) {
		session.setAttribute("keyWord", keyWord);
	}
}
