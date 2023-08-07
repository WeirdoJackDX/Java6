package com.poly.asm_nhom_6.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.poly.asm_nhom_6.DAO.NguoiDungDAO;
import com.poly.asm_nhom_6.config.PaypalPaymentIntent;
import com.poly.asm_nhom_6.config.PaypalPaymentMethod;
import com.poly.asm_nhom_6.model.NguoiDung;
import com.poly.asm_nhom_6.service.PaypalService;
import com.poly.asm_nhom_6.utils.Utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class PaymentController {
	public static final String URL_PAYPAL_SUCCESS = "pay/success";
	public static final String URL_PAYPAL_CANCEL = "pay/cancel";

	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private PaypalService paypalService;

	@Autowired
	HttpSession session;

	@Autowired
	NguoiDungDAO nguoiDungDAO;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@PostMapping("/pay")
	public String pay(HttpServletRequest request, @RequestParam("price") double price) {
		String cancelUrl = Utils.getBaseURL(request) + "/" + URL_PAYPAL_CANCEL;
		String successUrl = Utils.getBaseURL(request) + "/" + URL_PAYPAL_SUCCESS;
		NguoiDung user = (NguoiDung) session.getAttribute("nguoiDung");
		user = nguoiDungDAO.findById(user.getMaND()).get();
		if (user.getGioHangChiTiets().size() > 0) {
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

	@GetMapping(URL_PAYPAL_CANCEL)
	public String cancelPay() {
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
}
