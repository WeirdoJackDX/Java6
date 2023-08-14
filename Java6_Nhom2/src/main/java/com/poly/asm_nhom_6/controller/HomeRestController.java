package com.poly.asm_nhom_6.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poly.asm_nhom_6.model.GioHangChiTiet;
import com.poly.asm_nhom_6.model.NguoiDung;
import com.poly.asm_nhom_6.model.SanPham;
import com.poly.asm_nhom_6.model.ThichSanPham;
import com.poly.asm_nhom_6.service.GioHangChiTietService;
import com.poly.asm_nhom_6.service.NguoiDungService;
import com.poly.asm_nhom_6.service.SanPhamService;
import com.poly.asm_nhom_6.service.ThichSanPhamService;

import jakarta.servlet.http.HttpSession;

@CrossOrigin("*")
@RestController
public class HomeRestController {

    @Autowired
    HttpSession session;

    @Autowired
    ThichSanPhamService thichSanPhamService;

    @Autowired
    SanPhamService sanPhamService;

    @Autowired
    NguoiDungService nguoiDungService;

    @Autowired
    GioHangChiTietService gioHangChiTietService;

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

    @PostMapping("/user/saveKeyWords")
    public void hehe(@RequestParam("keyWord") String keyWord) {
        session.setAttribute("keyWord", keyWord);
    }

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
                            "Số lượng bánh không đủ cung cấp!\n Giỏ hàng của bạn quá lớn!");
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
}
