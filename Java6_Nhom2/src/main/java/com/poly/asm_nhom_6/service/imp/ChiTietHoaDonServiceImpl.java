package com.poly.asm_nhom_6.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.asm_nhom_6.DAO.ChiTietHoaDonDAO;
import com.poly.asm_nhom_6.model.ChiTietHoaDon;
import com.poly.asm_nhom_6.service.ChiTietHoaDonService;

@Service
public class ChiTietHoaDonServiceImpl implements ChiTietHoaDonService {

    @Autowired
    ChiTietHoaDonDAO dao;

    @Override
    public void save(ChiTietHoaDon cthd) {
        dao.save(cthd);
    }

}
