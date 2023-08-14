package com.poly.asm_nhom_6.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.poly.asm_nhom_6.model.HoaDon;

public interface HoaDonService {

    HoaDon getRecentReceipt(Integer maND);

    HoaDon findById(Integer id);

    Page<HoaDon> findHoaDonByMaND(Integer maND, Pageable pageable);

    void save(HoaDon hd);
    
}
