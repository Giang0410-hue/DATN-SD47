package com.example.bedatnsd47.service.impl;

import com.example.bedatnsd47.entity.ThuongHieu;
import com.example.bedatnsd47.repository.ThuongHieuRepository;
import com.example.bedatnsd47.service.ThuongHieuService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ThuongHieuServiceImpl implements ThuongHieuService {

    @Autowired
    private ThuongHieuRepository thuongHieuRepository;

    @Override
    public List<ThuongHieu> findAll() {

        Sort sort = Sort.by(Sort.Direction.DESC, "ngaySua");
        return thuongHieuRepository.findAll(sort);

    }

    @Override
    public List<ThuongHieu> getAllDangHoatDong() {
        return thuongHieuRepository.fillAllDangHoatDong();
    }

    @Override
    public List<ThuongHieu> getAllNgungHoatDong() {
        return thuongHieuRepository.fillAllNgungHoatDong();
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    @Transactional
    public ThuongHieu save(ThuongHieu thuongHieu) {

            return thuongHieuRepository.save(thuongHieu);

    }

    @Override
    public boolean checkTenTrung(String ten) {

        for (ThuongHieu sp : thuongHieuRepository.findAll()) {
            if (sp.getTen().equalsIgnoreCase(ten)) {
                return false;
            }
        }
        return true;

    }

    @Override
    public boolean checkTenTrungSua(Long id, String ten) {

        for (ThuongHieu sp : thuongHieuRepository.findAll()) {
            if (sp.getTen().equalsIgnoreCase(ten)) {
                if (!sp.getId().equals(id)){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public ThuongHieu update(ThuongHieu thuongHieu) {

        return thuongHieuRepository.save(thuongHieu);

    }

    @Override
    public ThuongHieu getById(Long id) {

        return thuongHieuRepository.findById(id).get();

    }

}
