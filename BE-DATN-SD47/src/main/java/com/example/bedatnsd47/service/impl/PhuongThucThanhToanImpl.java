package com.example.bedatnsd47.service.impl;


import com.example.bedatnsd47.entity.PhuongThucThanhToan;
import com.example.bedatnsd47.repository.PhuongThucThanhToanRepository;
import com.example.bedatnsd47.service.PhuongThucThanhToanServie;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PhuongThucThanhToanImpl implements PhuongThucThanhToanServie {
    @Autowired
    PhuongThucThanhToanRepository phuongThucThanhToanRepository;

    @Override
    public List<PhuongThucThanhToan> findAll() {
        return phuongThucThanhToanRepository.findAll();
    }
}
