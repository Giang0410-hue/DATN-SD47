package com.example.bedatnsd47.service.impl;


import com.example.bedatnsd47.entity.HoaDonChiTiet;
import com.example.bedatnsd47.repository.HoaDonChiTietRepository;
import com.example.bedatnsd47.repository.HoaDonRepository;
import com.example.bedatnsd47.service.HoaDonChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HoaDonChiTietServiceImpl implements HoaDonChiTietService {

    @Autowired
    HoaDonChiTietRepository hoaDonChiTietRepository;

    @Autowired
    HoaDonRepository hoaDonRepository;

    @Override
    public List<HoaDonChiTiet> findAll() {
        return hoaDonChiTietRepository.findAll();
    }

    @Override
    public HoaDonChiTiet findById(Long id) {
        return hoaDonChiTietRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        hoaDonChiTietRepository.deleteById(id);
    }

    @Override
    public void saveOrUpdate(HoaDonChiTiet hoaDonChiTiet) {
        hoaDonChiTietRepository.save(hoaDonChiTiet);
    }

    @Override
    public List<HoaDonChiTiet> findByIdHoaDon(Long idHoaDon) {
        // TODO Auto-generated method stub
        return hoaDonChiTietRepository.findByIdHoaDon(idHoaDon);
    }

    @Override
    public List<HoaDonChiTiet> finTop5HDCT() {

        return hoaDonChiTietRepository.fillAllIdHoaDonTrangThaiHoanThanh(hoaDonRepository.fillAllIdHoaDonTrangThaiHoanThanh());

    }

}
