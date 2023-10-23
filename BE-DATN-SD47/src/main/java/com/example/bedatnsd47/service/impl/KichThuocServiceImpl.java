package com.example.bedatnsd47.service.impl;

import com.example.bedatnsd47.entity.KichCo;
import com.example.bedatnsd47.entity.ThuongHieu;
import com.example.bedatnsd47.repository.KichThuocRepository;
import com.example.bedatnsd47.service.KichThuocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class KichThuocServiceImpl implements KichThuocService {
    @Autowired
    KichThuocRepository kichThuocRepository;

    @Override
    public List<KichCo> findAll() {
        return kichThuocRepository.findAll();
    }

    @Override
    public Optional<KichCo> findById(Long id) {
        return kichThuocRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void saveOrUpdate(KichCo thuongHieu, Float ten) {
        try {
            thuongHieu.setTen(ten);
            thuongHieu.setNgayTao(new Date());
            thuongHieu.setNgaySua(new Date());
            thuongHieu.setTrangThai(1);
            kichThuocRepository.save(thuongHieu);
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            // Handle the exception as needed
        }
    }

    @Override
    public void update(KichCo thuongHieu, Long id, Integer trangThai, Float ten, Date ngayTao) {
        try {
            List<KichCo> list = KichThuocServiceImpl.this.findAll();
            boolean duplicateFound = false;
            for (KichCo thuongHieuTim : list
            ) {
                if (thuongHieuTim.getId() != id && thuongHieuTim.getTen().equals(ten)) {
                    duplicateFound = true;
                    break;
                }
            }
            if (duplicateFound) {
                System.out.println("Da ton tai");
            } else {
                thuongHieu.setId(id);
                thuongHieu.setTen(ten);
                thuongHieu.setNgayTao(ngayTao);
                thuongHieu.setNgaySua(new Date());
                thuongHieu.setTrangThai(trangThai);
                kichThuocRepository.save(thuongHieu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public KichCo findByTen(Float ten) {
        return kichThuocRepository.findByTen(ten);
    }

    @Override
    public Page<KichCo> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "ngaySua"));
        return kichThuocRepository.findAll(pageable);

    }

    @Override
    public Page<KichCo> findByTenContaining(String keyword, Integer trang_thai, int page, int size) {
        return null;
    }
}
