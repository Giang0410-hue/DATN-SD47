package com.example.bedatnsd47.service.impl;


import com.example.bedatnsd47.entity.HoaDonChiTiet;
import com.example.bedatnsd47.repository.HoaDonChiTietRepository;
import com.example.bedatnsd47.repository.HoaDonRepository;
import com.example.bedatnsd47.service.HoaDonChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public List<Object[]> findByTongSoLuongBetween(Date startDate, Date endDate) {
        return hoaDonChiTietRepository.findByTongSoLuongBetween(startDate,endDate);
    }

    @Override
    public List<Object[]> findByTongSoLuongNgay(Date ngayTao) {
        return hoaDonChiTietRepository.findByTongSoLuongNgay(ngayTao);
    }

    @Override
    public List<Object[]> findByTongSoLuongThang(Date ngayTao) {
        return hoaDonChiTietRepository.findByTongSoLuongThang(ngayTao);
    }

    @Override
    public Integer sumSanPhamHoaDonThang(Date ngayTao) {
        return hoaDonChiTietRepository.sumSanPhamHoaDonThang(ngayTao);
    }

    @Override
    public Integer sumSanPhamHoaDonNgay(Date ngayTao) {
        return hoaDonChiTietRepository.sumSanPhamHoaDonNgay(ngayTao);
    }

    @Override
    public Integer sumSanPhamHoaDonBetween(Date startDate, Date endDate) {
        return hoaDonChiTietRepository.sumSanPhamHoaDonBetween(startDate,endDate);
    }

    @Override
    public List<Object[]> thongKeSanPhamTheoNgay(Date startDateChart, Date endDateChart) {
        return hoaDonChiTietRepository.thongKeSanPhamTheoNgay(startDateChart, endDateChart);
    }

    @Override
    public Integer sumSanPhamHoaDonAll() {
        return hoaDonChiTietRepository.sumSanPhamHoaDonAll();
    }

    @Override
    public List<Object[]> findByTongSoLuongAll() {
        return hoaDonChiTietRepository.findByTongSoLuongAll();
    }

}
