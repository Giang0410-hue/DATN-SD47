package com.example.bedatnsd47.service.impl;

import com.example.bedatnsd47.entity.ChiTietSanPham;
import com.example.bedatnsd47.entity.KichCo;
import com.example.bedatnsd47.entity.LoaiDe;
import com.example.bedatnsd47.entity.MauSac;
import com.example.bedatnsd47.entity.SanPham;
import com.example.bedatnsd47.repository.ChiTietSanPhamRepository;
import com.example.bedatnsd47.service.ChiTietSanPhamSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChiTietSanPhamSerivceImpl implements ChiTietSanPhamSerivce {

    @Autowired
    private ChiTietSanPhamRepository repository;

    @Override
    public List<ChiTietSanPham> getAll() {

        return repository.findAll();

    }

    @Override
    public List<ChiTietSanPham> getAllCtspOneSanPham() {
        return repository.fillAllCtspOneSanPham();
    }

    @Override
    public List<ChiTietSanPham> getAllDangHoatDong() {

        return repository.fillAllDangHoatDong();

    }

    @Override
    public List<ChiTietSanPham> getAllNgungHoatDong() {

        return repository.fillAllNgungHoatDong();

    }

    @Override
    public List<ChiTietSanPham> add(
            List<String> listSanPham, List<String> listKichCo,
            List<String> listMauSac, List<String> listLoaiDe,
            List<String> listSoLuong, List<String> listDonGia) {

        List<ChiTietSanPham> chiTietSanPhamList = new ArrayList<>();

        for (int i = 0; i < listSanPham.size(); i++) {
            ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
            chiTietSanPham.setSanPham(SanPham.builder().id(Long.valueOf(listSanPham.get(i))).build());
            chiTietSanPham.setKichCo(KichCo.builder().id(Long.valueOf(listKichCo.get(i))).build());
            chiTietSanPham.setMauSac(MauSac.builder().id(Long.valueOf(listMauSac.get(i))).build());
            chiTietSanPham.setLoaiDe(LoaiDe.builder().id(Long.valueOf(listLoaiDe.get(i))).build());
            chiTietSanPham.setSoLuong(Integer.parseInt(listSoLuong.get(i)));
            chiTietSanPham.setGiaHienHanh(Long.valueOf(listDonGia.get(i)));
            chiTietSanPham.setTrangThai(0);
            if(chiTietSanPham.getSoLuong()==0){
                continue;
            }
            ChiTietSanPham savedChiTietSanPham = repository.save(chiTietSanPham);
            chiTietSanPhamList.add(savedChiTietSanPham);
        }

        return chiTietSanPhamList;
    }

    @Override
    public ChiTietSanPham update(ChiTietSanPham sanPham) {

        return repository.save(sanPham);

    }

    @Override
    public void remove(Long id) {

        repository.deleteById(id);

    }

    @Override
    public ChiTietSanPham getById(Long id) {

        return repository.findById(id).get();

    }

    @Override
    public List<ChiTietSanPham> getAllById(Long id) {

        return repository.fillAllChiTietSpShop(id);

    }

    @Override
    public List<ChiTietSanPham> getAllbyIdSPAndIdMS(Long idSanPham, Long idMauSac) {

        return repository.fillAllChiTietSpMauSac(idSanPham,idMauSac);

    }

    @Override
    public boolean checkTenTrung(String ten) {

        return false;

    }

    @Override
    public boolean checkTenTrungSua(String ma, String ten) {

        return false;

    }

    @Override
    public List<ChiTietSanPham> fillAllDangHoatDongLonHon0() {
        return repository.fillAllDangHoatDongLonHon0();
    }

}
