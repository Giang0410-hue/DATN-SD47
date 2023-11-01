package com.example.bedatnsd47.service.impl;

import com.example.bedatnsd47.entity.ChiTietSanPham;
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
    public List<ChiTietSanPham> getAllDangHoatDong() {

        return repository.fillAllDangHoatDong();

    }

    @Override
    public List<ChiTietSanPham> getAllNgungHoatDong() {

        return repository.fillAllNgungHoatDong();

    }

    @Override
    public ChiTietSanPham add(
            List<String> listSanPham, List<String> listKichCo,
            List<String> listMauSac, List<String> listLoaiDe,
            List<String> listSoLuong, List<String> listDonGia) {
//
//        List<ChiTietSanPham> chiTietSanPhamList = new ArrayList<>();
//
//        for (int i = 0; i < listSanPham.size(); i++) {
//            ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
//            chiTietSanPham.setSanPham(SanPham.builder().id(Long.valueOf(listSanPham.get(i))).build()); // Thiết lập từng phần tử từ danh sách
//
//            // Thiết lập các thuộc tính khác dựa trên danh sách tương ứng, tương tự như đoạn mã trên
//            chiTietSanPham.setKichCo(listKichCo.get(i));
//            chiTietSanPham.setMauSac(listMauSac.get(i));
//            chiTietSanPham.setLoaiDe(listLoaiDe.get(i));
//            chiTietSanPham.setSoLuong(Integer.parseInt(listSoLuong.get(i)));
//            chiTietSanPham.setGiaHienHanh(Double.parseDouble(listDonGia.get(i)));
//            chiTietSanPham.setTrangThai(0);
//
//            chiTietSanPhamList.add(chiTietSanPham); // Thêm đối tượng đã tạo vào danh sách
//        }
//
//        // Lưu tất cả các đối tượng ChiTietSanPham đã tạo trong repository
//        return repository.saveAll(chiTietSanPhamList);
        return null;
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
    public boolean checkTenTrung(String ten) {

        return false;

    }

    @Override
    public boolean checkTenTrungSua(String ma, String ten) {

        return false;

    }

}
