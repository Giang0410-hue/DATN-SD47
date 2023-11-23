package com.example.bedatnsd47.service.impl;

import com.example.bedatnsd47.entity.HoaDon;
import com.example.bedatnsd47.repository.HoaDonRepository;
import com.example.bedatnsd47.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoaDonServiceImpl implements HoaDonService {

    @Autowired
    HoaDonRepository hoaDonRepository;

    @Override
    public List<HoaDon> findAll() {
        return hoaDonRepository.findAll();
    }

    @Override
    public HoaDon findById(Long id) {
        return hoaDonRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        hoaDonRepository.deleteById(id);
    }

    @Override
    public void saveOrUpdate(HoaDon hoaDon) {
        hoaDonRepository.save(hoaDon);
    }

    @Override
    public List<HoaDon> findByTrangThai(Integer trangThai) {
        return hoaDonRepository.findByTrangThai(trangThai);
    }

    @Override
    public Integer countHoaDonTreo() {
        return hoaDonRepository.countHoaDonTreo();
    }

    @Override
    public List<HoaDon> find5ByTrangThai(Integer trangThai) {
        return hoaDonRepository.find5ByTrangThai(trangThai);
    }

    @Override
    public HoaDon findByMa(String ma) {
        return hoaDonRepository.findByMa(ma);
    }

    @Override
    public List<HoaDon> findAllOrderByNgaySua() {
        return hoaDonRepository.findAllOrderByNgaySua();
    }
}
