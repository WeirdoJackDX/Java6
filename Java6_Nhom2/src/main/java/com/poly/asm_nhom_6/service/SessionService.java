package com.poly.asm_nhom_6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class SessionService {
    @Autowired
    HttpSession session;

    @SuppressWarnings("unchecked")
	public <T> T get(String name) {
        if (session.getAttribute(name) == null) {
            return null;
        }
        return (T) session.getAttribute(name);
    }

    public void set(String name, Object value) {
        session.setAttribute(name, value);
    }
    public void remove(String name) {
        session.removeAttribute(name);
    }
}
