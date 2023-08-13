package com.poly.asm_nhom_6.controller;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.poly.asm_nhom_6.DAO.ChiTietHoaDonDAO;
import com.poly.asm_nhom_6.DAO.GioHangChiTietDAO;
import com.poly.asm_nhom_6.DAO.HoaDonDAO;
import com.poly.asm_nhom_6.DAO.NguoiDungDAO;
import com.poly.asm_nhom_6.DAO.SanPhamDAO;
import com.poly.asm_nhom_6.DTO.CartDTO;
import com.poly.asm_nhom_6.config.PaypalPaymentIntent;
import com.poly.asm_nhom_6.config.PaypalPaymentMethod;
import com.poly.asm_nhom_6.model.ChiTietHoaDon;
import com.poly.asm_nhom_6.model.GioHangChiTiet;
import com.poly.asm_nhom_6.model.HoaDon;
import com.poly.asm_nhom_6.model.NguoiDung;
import com.poly.asm_nhom_6.model.SanPham;
import com.poly.asm_nhom_6.service.PaypalService;
import com.poly.asm_nhom_6.service.VNPayService;
import com.poly.asm_nhom_6.utils.Utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class PaymentController {
	@Autowired
	SanPhamDAO sanPhamDAO;

	@Autowired
	HoaDonDAO hoaDonDAO;

	@Autowired
	GioHangChiTietDAO gioHangChiTietDAO;

	@Autowired
	private VNPayService vnPayService;

	public static final String URL_PAYPAL_SUCCESS = "pay/success";
	public static final String URL_PAYPAL_CANCEL = "pay/cancel";

	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private PaypalService paypalService;

	@Autowired
	HttpSession session;

	@Autowired
	NguoiDungDAO nguoiDungDAO;

	@Autowired
	ChiTietHoaDonDAO cthdDAO;

	List<CartDTO> cartDTOs;

	// Paypal

	@PostMapping("/pay")
	public String pay(HttpServletRequest request, @RequestParam("price") double price) {
		String cancelUrl = Utils.getBaseURL(request) + "/" + URL_PAYPAL_CANCEL;
		String successUrl = Utils.getBaseURL(request) + "/" + URL_PAYPAL_SUCCESS;
		if (checkValid()) {
			try {
				price = price / 24000;
				Payment payment = paypalService.createPayment(price, "USD", PaypalPaymentMethod.paypal,
						PaypalPaymentIntent.sale, "payment description", cancelUrl, successUrl);
				for (Links links : payment.getLinks())
					if (links.getRel().equals("approval_url"))
						return "redirect:" + links.getHref();
			} catch (PayPalRESTException e) {
				log.error(e.getMessage());
			}
		}
		return "redirect:/user/cart/index";
	}

	@GetMapping(URL_PAYPAL_SUCCESS)
	public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
		try {
			Payment payment = paypalService.executePayment(paymentId, payerId);
			if (payment.getState().equals("approved"))
				return "redirect:/user/cart/purchase";
		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
		}
		return "redirect:/user/cart/index";
	}

	@GetMapping(URL_PAYPAL_CANCEL)
	public String cancelPay() {
		return "redirect:/user/cart/index";
	}

	// VNPay

	@PostMapping("/submitOrder")
	public String submidOrder(@RequestParam("price") int orderTotal,
			// @RequestParam("orderInfo") String orderInfo,
			HttpServletRequest request) {
		if (checkValid()) {
			String orderInfo = "ABC";
			String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
			String vnpayUrl = vnPayService.createOrder(orderTotal, orderInfo, baseUrl);
			return "redirect:" + vnpayUrl;
		} else {
			return "redirect:/user/cart/index";
		}
	}

	@GetMapping("/vnpay-payment")
	public String GetMapping(HttpServletRequest request, Model model) {
		int paymentStatus = vnPayService.orderReturn(request);
		String orderInfo = request.getParameter("vnp_OrderInfo");
		String paymentTime = request.getParameter("vnp_PayDate");
		String transactionId = request.getParameter("vnp_TransactionNo");
		String totalPrice = request.getParameter("vnp_Amount");

		model.addAttribute("orderId", orderInfo);
		model.addAttribute("totalPrice", totalPrice);
		model.addAttribute("paymentTime", paymentTime);
		model.addAttribute("transactionId", transactionId);

		return paymentStatus == 1 ? "redirect:/user/cart/purchase" : "redirect:/user/cart/index";
	}

	// Others

	public Boolean checkValid() {
		NguoiDung user = (NguoiDung) session.getAttribute("nguoiDung");
		user = nguoiDungDAO.findById(user.getMaND()).get();
		if (user.getGioHangChiTiets().size() > 0 && user.getGioHangChiTiets().size() == cartDTOs.size()) {
			return true;
		} else {
			return false;
		}
	}

	@ResponseBody
	@PostMapping("/getDTO")
	public void pay2(@RequestBody List<CartDTO> cartDTO) {
		cartDTOs = cartDTO;
	}

	@RequestMapping("/user/cart/purchase")
	public String invoice() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		NguoiDung user = (NguoiDung) session.getAttribute("nguoiDung");
		user = nguoiDungDAO.findById(user.getMaND()).get();
		HoaDon hd = new HoaDon(null, null, timestamp, null, user, null);
		hoaDonDAO.save(hd);
		for (GioHangChiTiet ghct : user.getGioHangChiTiets()) {
			for (CartDTO cdto : cartDTOs) {
				if (cdto.getGioHangChiTiet().getMaGioHang().equals(ghct.getMaGioHang())
						&& cdto.getIsChecked()) {
					ChiTietHoaDon cthd = new ChiTietHoaDon(null, ghct.getSoLuong(), ghct.getSanPham().getGiaBan(),
							ghct.getSanPham().getGiaNhap(), hd, ghct.getSanPham());
					cthdDAO.save(cthd);
					SanPham sp = sanPhamDAO.findById(ghct.getSanPham().getMaSP()).get();
					sp.setSoLuong(sp.getSoLuong() - ghct.getSoLuong());
					sanPhamDAO.save(sp);
					gioHangChiTietDAO.delete(ghct);
				}
			}
		}
		HoaDon recent = hoaDonDAO.getRecentReceipt(user.getMaND());
		return "redirect:/user/invoice/" + recent.getMaHoaDon().toString();
	}
}
