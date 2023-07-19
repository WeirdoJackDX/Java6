package com.poly.asm_nhom_6.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

@Service
public class GlobalInterceptor implements HandlerInterceptor {
    // @Autowired

    // @Override
    // public boolean preHandle(HttpServletRequest request,
    // HttpServletResponse response, Object handler) throws Exception {
    // request.setAttribute("uri", request.getRequestURI());
    // return true;
    // }

    // @Override
    // public void postHandle(HttpServletRequest req, HttpServletResponse res,
    // Object handler, ModelAndView mv) throws Exception {
    // req.setAttribute("categories", dao.findAll());
    // }
}
