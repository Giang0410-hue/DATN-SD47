package com.example.bedatnsd47.service.impl;

import com.example.bedatnsd47.entity.LichSuHoaDon;
import com.example.bedatnsd47.repository.LichSuHoaDonRepository;
import com.example.bedatnsd47.service.LichSuHoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LichSuHoaDonServieImpl implements LichSuHoaDonService {

    @Autowired
    LichSuHoaDonRepository lichSuHoaDonRepository;

    @Override
    public List<LichSuHoaDon> findAll() {
        return lichSuHoaDonRepository.findAll();
    }

    @Override
    public LichSuHoaDon findById(Long id) {
        return lichSuHoaDonRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        lichSuHoaDonRepository.deleteById(id);
    }

    @Override
    public void saveOrUpdate(LichSuHoaDon lichSuHoaDon) {
        lichSuHoaDonRepository.save(lichSuHoaDon);
    }
}
