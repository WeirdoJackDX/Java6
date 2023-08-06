package com.poly.asm_nhom_6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import com.poly.asm_nhom_6.model.NguoiDung;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Service
public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    HttpSession session;

    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        NguoiDung user = (NguoiDung) session.getAttribute("nguoiDung"); // lấy từ session
        try {
            if (user == null) {
                if (uri.startsWith("/user/cart") || uri.startsWith("/user/like")) {
                    return false;
                }
                if (uri.startsWith("/admin") || uri.startsWith("/staff")) {
                    response.sendRedirect("/error404");
                    return false;
                }
                return false;
            } else if (uri.contains("admin") || uri.contains("staff")) {
                response.sendRedirect("/error404");
                return false;
            }
        } catch (Exception e) {
            response.sendRedirect("/error404");
            return false;
        }
        return true;
    }

}
