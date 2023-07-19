package com.poly.asm_nhom_6.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import com.poly.asm_nhom_6.model.NguoiDung;
import com.poly.asm_nhom_6.service.SessionService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class SecurityInterceptorAdmin implements HandlerInterceptor {
    @Autowired
    SessionService session;

    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        String error = "";
        NguoiDung admin = (NguoiDung) session.get("admin");
        if (session.get("admin") == null) {
            error = "NoLogin";
            response.sendRedirect("/errorPage?error=" + error);
            return false;
        }
        if (admin.getVaiTro() == 0 && (uri.startsWith("/admin") || uri.startsWith("/staff"))) {
            error = "norightforuser";
            response.sendRedirect("/errorPage?error=" + error);
            return false;
        }
        if (admin.getVaiTro() == 1 && uri.startsWith("/admin")) {
            error = "norightforstaff";// nếu vai trò đăng nhập bằng nhân viên và có dường dẫn mapping = /admin
            response.sendRedirect("/errorPage?error=" + error);
            return false;
        }
        if (error.length() > 0) {
            session.set(uri, error);
            response.sendRedirect("/errorPage?error=" + error);
            return false;
        }

        return true;
    }
}
