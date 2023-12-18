package com.example.bedatnsd47.service.impl;

import com.example.bedatnsd47.entity.ChiTietSanPham;
import com.example.bedatnsd47.entity.KichCo;
import com.example.bedatnsd47.entity.LoaiDe;
import com.example.bedatnsd47.entity.MauSac;
import com.example.bedatnsd47.entity.SanPham;
import com.example.bedatnsd47.repository.ChiTietSanPhamRepository;
import com.example.bedatnsd47.service.ChiTietSanPhamSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
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

//    @Override
//    public List<ChiTietSanPham> getAllCtspOneSanPhamMinGia() {
//
//        return repository.fillAllCtspOneSanPhamMinGia();
//
//    }

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
        List<ChiTietSanPham> listCtspCheck = repository.findAll();
        for (int i = 0; i < listSanPham.size(); i++) {
            ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
            boolean isUpdated = false;

            for (ChiTietSanPham listCheck : listCtspCheck) {
                if (listCheck.getSanPham().getId().equals(Long.valueOf(listSanPham.get(i))) &&
                        listCheck.getKichCo().getId().equals(Long.valueOf(listKichCo.get(i))) &&
                        listCheck.getMauSac().getId().equals(Long.valueOf(listMauSac.get(i))) &&
                        listCheck.getLoaiDe().getId().equals(Long.valueOf(listLoaiDe.get(i)))
                ) {
                    int soLuongMoi = Integer.parseInt(listSoLuong.get(i));
                    listCheck.setSoLuong(listCheck.getSoLuong() + soLuongMoi);
                    listCheck.setGiaHienHanh(Long.valueOf(listDonGia.get(i)));
                    listCheck.setTrangThai(0);
                    chiTietSanPham.setNgaySua(new Date());

                    ChiTietSanPham updatedChiTietSanPham = repository.save(listCheck);
                    chiTietSanPhamList.add(updatedChiTietSanPham);

                    isUpdated = true;

                    break;
                }
            }

            if (!isUpdated) {
                chiTietSanPham.setSanPham(SanPham.builder().id(Long.valueOf(listSanPham.get(i))).build());
                chiTietSanPham.setKichCo(KichCo.builder().id(Long.valueOf(listKichCo.get(i))).build());
                chiTietSanPham.setMauSac(MauSac.builder().id(Long.valueOf(listMauSac.get(i))).build());
                chiTietSanPham.setLoaiDe(LoaiDe.builder().id(Long.valueOf(listLoaiDe.get(i))).build());
                chiTietSanPham.setSoLuong(Integer.parseInt(listSoLuong.get(i)));
                chiTietSanPham.setGiaHienHanh(Long.valueOf(listDonGia.get(i)));
                chiTietSanPham.setTrangThai(0);
                chiTietSanPham.setNgayTao(new Date());
                chiTietSanPham.setNgaySua(new Date());

                if (chiTietSanPham.getSoLuong() > 0) {
                    ChiTietSanPham savedChiTietSanPham = repository.save(chiTietSanPham);
                    chiTietSanPhamList.add(savedChiTietSanPham);
                }
            }

        }

        return chiTietSanPhamList;
    }

    @Override
    public List<ChiTietSanPham> updateAllCtsp(
            List<String> listIdChiTietSp, List<String> listSanPham,
            List<String> listKichCo, List<String> listMauSac,
            List<String> listLoaiDe, List<String> listTrangThai,
            List<String> listSoLuong, List<String> listDonGia) {
        ChiTietSanPham chiTietSanPhamNew = this.getById(Long.valueOf(listIdChiTietSp.get(0)));
        List<ChiTietSanPham> chiTietSanPhamList = new ArrayList<>();

        for (int i = 0; i < listSanPham.size(); i++) {
            ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
            chiTietSanPham.setId(Long.valueOf(listIdChiTietSp.get(i)));
            chiTietSanPham.setSanPham(SanPham.builder().id(Long.valueOf(listSanPham.get(i))).build());
            chiTietSanPham.setKichCo(KichCo.builder().id(Long.valueOf(listKichCo.get(i))).build());
            chiTietSanPham.setMauSac(MauSac.builder().id(Long.valueOf(listMauSac.get(i))).build());
            chiTietSanPham.setLoaiDe(LoaiDe.builder().id(Long.valueOf(listLoaiDe.get(i))).build());
            chiTietSanPham.setTrangThai(Integer.parseInt(listTrangThai.get(i)));
            chiTietSanPham.setSoLuong(Integer.parseInt(listSoLuong.get(i)));
            chiTietSanPham.setGiaHienHanh(Long.valueOf(listDonGia.get(i)));
            chiTietSanPham.setNgayTao(chiTietSanPhamNew.getNgayTao());
            chiTietSanPham.setNgaySua(new Date());
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
    public ChiTietSanPham saveExcel(ChiTietSanPham chiTietSanPham) {

        return repository.save(chiTietSanPham);

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

        return repository.fillAllChiTietSpMauSac(idSanPham, idMauSac);

    }

    @Override
    public List<ChiTietSanPham> getAllCtspByIdSanPham(Long idSanPham) {

        return repository.fillAllChiTietSpBySanPham(idSanPham);

    }

    @Override
    public List<ChiTietSanPham> fillAllDangHoatDongLonHon0() {
        return repository.fillAllDangHoatDongLonHon0();
    }

    @Override
    public Page<List<ChiTietSanPham>> searchAll(Integer pageNo,Integer size, String tenSanPham, List<Long> idMauSac, List<Long> idKichCo,
                                                List<Long> idLoaiDe, List<Long> idThuongHieu, Long minGia,
                                                Long maxGia) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return repository.searchAll(pageable, tenSanPham, idMauSac, idKichCo, idLoaiDe, idThuongHieu, minGia, maxGia);

    }

    @Override
    public List<Long> getAllIdMauSacCTSP() {
        return repository.getAllIdMauSacCTSP();
    }

    @Override
    public List<Long> getAllIdKichCoCTSP() {
        return repository.getAllIdKichCoCTSP();
    }

    @Override
    public List<Long> getAllIdLoaiDeCTSP() {
        return repository.getAllIdLoaiDeCTSP();
    }

    @Override
    public List<Long> getAllIdThuongHieuCTSP() {
        return repository.getAllIdThuongHieuCTSP();
    }

    @Override
    public Long getAllMinGiaCTSP() {
        return repository.getAllMinGiaCTSP();
    }

    @Override
    public Long getAllMaxGiaCTSP() {
        return repository.getAllMaxGiaCTSP();
    }

    @Override
    public Integer checkPage(Integer page) {
        Integer sizeList = repository.findAll().size();
        Integer pageCount = (int) Math.ceil((double) sizeList / 5);
        if (page >= pageCount) {
            page = 0;
        }else if (page < 0) {
            page = pageCount-1;
        }
        return page;
    }
    
    public List<Object[]> danhSachHangSapHet(Integer soLuong) {
        return repository.danhSachHangSapHet(soLuong);
    }

    @Override
    public void checkSoLuongBang0() {
        repository.checkSoLuongBang0();
    }

}
