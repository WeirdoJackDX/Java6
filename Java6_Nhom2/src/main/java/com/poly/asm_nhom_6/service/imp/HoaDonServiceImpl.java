package com.poly.asm_nhom_6.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.poly.asm_nhom_6.DAO.HoaDonDAO;
import com.poly.asm_nhom_6.model.HoaDon;
import com.poly.asm_nhom_6.service.HoaDonService;

@Service
public class HoaDonServiceImpl implements HoaDonService {

    @Autowired
    HoaDonDAO dao;

    @Override
    public HoaDon getRecentReceipt(Integer maND) {
        return dao.getRecentReceipt(maND);
    }

    @Override
    public HoaDon findById(Integer id) {
        return dao.findById(id).get();
    }

    @Override
    public Page<HoaDon> findHoaDonByMaND(Integer maND, Pageable pageable) {
        return dao.findHoaDonByMaND(maND, pageable);
    }

    @Override
    public void save(HoaDon hd) {
        dao.save(hd);
    }

}
