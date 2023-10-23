package com.example.bedatnsd47.service.impl;

import com.example.bedatnsd47.entity.LoaiDe;
import com.example.bedatnsd47.entity.ThuongHieu;
import com.example.bedatnsd47.repository.LoaiDeRepository;
import com.example.bedatnsd47.service.LoaiDeService;
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
public class LoaiDeServiceImpl  implements LoaiDeService {
    @Autowired
    LoaiDeRepository loaiDeRepository;
    @Override
    public List<LoaiDe> findAll() {
        return loaiDeRepository.findAll();
    }

    @Override
    public Optional<LoaiDe> findById(Long id) {
        return loaiDeRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void saveOrUpdate(LoaiDe thuongHieu, String ten) {
        try {
            thuongHieu.setTen(ten);
            thuongHieu.setNgayTao(new Date());
            thuongHieu.setNgaySua(new Date());
            thuongHieu.setTrangThai(1);
            loaiDeRepository.save(thuongHieu);
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            // Handle the exception as needed
        }
    }

    @Override
    public void update(LoaiDe thuongHieu, Long id, Integer trangThai, String ten, Date ngayTao) {
        try {
            List<LoaiDe> list = LoaiDeServiceImpl.this.findAll();
            boolean duplicateFound = false;
            for (LoaiDe thuongHieuTim : list
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
                loaiDeRepository.save(thuongHieu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public LoaiDe findByTen(String ten) {
        return loaiDeRepository.findByTen(ten);
    }

    @Override
    public Page<LoaiDe> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "ngaySua"));
        return loaiDeRepository.findAll(pageable);
    }

    @Override
    public Page<LoaiDe> findByTenContaining(String keyword, Integer trang_thai, int page, int size) {
        return null;
    }
}
