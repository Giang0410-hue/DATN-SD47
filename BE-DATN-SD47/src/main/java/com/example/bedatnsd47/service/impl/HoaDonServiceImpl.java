package com.example.bedatnsd47.service.impl;

import com.example.bedatnsd47.entity.HoaDon;
import com.example.bedatnsd47.repository.HoaDonRepository;
import com.example.bedatnsd47.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Override
    public List<HoaDon> getHoaDonByTaiKhoanByTrangThaiOrderByNgaySua(Long idTaiKhoan,Integer trangThai) {

        return hoaDonRepository.findAllHoaDonByTaiKhoanAndTrangThaiOrderByNgaySua(idTaiKhoan,trangThai);

    }

    @Override
    public List<HoaDon> getAllHoaDonByTaiKhoanOrderByNgaySua(Long idTaiKhoan) {

        return hoaDonRepository.findAllHoaDonByTaiKhoanOrderByNgaySua(idTaiKhoan);

    }

    public Integer countHoaDonDay(Date ngayTao) {
        return hoaDonRepository.countHoaDonNgay(ngayTao);
    }

    @Override
    public Long sumHoaDonDay(Date ngayTao) {
        return hoaDonRepository.sumGiaTriHoaDonNgay(ngayTao);
    }

    @Override
    public Integer countHoaDonMonth(Date ngayTao) {
        return hoaDonRepository.countHoaDonThang(ngayTao);
    }

    @Override
    public Long sumHoaDonMonth(Date ngayTao) {
        return hoaDonRepository.sumGiaTriHoaDonThang(ngayTao);
    }

    @Override
    public Integer countHoaDon(Integer trangThai) {
        return hoaDonRepository.countHoaDon(trangThai);
    }

    @Override
    public Integer countHoaDonBetween(Date startDate, Date endDate) {
        return hoaDonRepository.countHoaDonBetween(startDate,endDate);
    }

    @Override
    public Long sumGiaTriHoaDonBetween(Date startDate, Date endDate) {
        return hoaDonRepository.sumGiaTriHoaDonBetween(startDate,endDate);
    }

    @Override
    public void deleteHoaDonHoanTra() {
        // TODO Auto-generated method stub
        hoaDonRepository.deleteHoaDonHoanTra();
    }
}
