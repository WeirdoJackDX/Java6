package com.poly.asm_nhom_6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import com.poly.asm_nhom_6.model.NguoiDung;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
                if (uri.startsWith("/user/home/index")
                        || uri.startsWith("/user/contact") || uri.startsWith("/user/search")
                        || uri.startsWith("/user/detail") || uri.startsWith("/user/signup")
                        || uri.startsWith("/user/shop")) {
                    return true;
                }
                if (uri.startsWith("/trangchu")) {
                    response.sendRedirect("/error404");
                    return false;
                }
                response.sendRedirect("/user/home/index");
                return false;
            } else if (uri.contains("/trangchu") && user.getVaiTro() != 1) {// vai trò 1 là admin
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
