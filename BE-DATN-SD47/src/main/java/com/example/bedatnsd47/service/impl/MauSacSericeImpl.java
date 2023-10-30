package com.example.bedatnsd47.service.impl;

import com.example.bedatnsd47.entity.MauSac;
import com.example.bedatnsd47.repository.MauSacRepository;
import com.example.bedatnsd47.service.MauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MauSacSericeImpl implements MauSacService {

    @Autowired
    MauSacRepository mauSacRepository;

    @Override
    public List<MauSac> findAll() {

        Sort sort = Sort.by(Sort.Direction.DESC, "ngaySua");
        return mauSacRepository.findAll(sort);

    }

    @Override
    public List<MauSac> getAllDangHoatDong() {

        return mauSacRepository.fillAllDangHoatDong();

    }

    @Override
    public List<MauSac> getAllNgungHoatDong() {

        return mauSacRepository.fillAllNgungHoatDong();

    }


    @Override
    public void deleteById(Long id) {

    }

    @Override
    public MauSac save(MauSac mauSac) {

          return  mauSacRepository.save(mauSac);

    }

    @Override
    public boolean checkTenTrung(String ten) {
        for (MauSac sp : mauSacRepository.findAll()) {
            if (sp.getTen().equalsIgnoreCase(ten)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean checkTenTrungSua(String ma, String ten) {
        for (MauSac sp : mauSacRepository.findAll()) {
            if (sp.getTen().equalsIgnoreCase(ten)) {
                if (!sp.getMaMau().equalsIgnoreCase(ma)){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public MauSac update(MauSac mauSac) {

        return mauSacRepository.save(mauSac);
    }

    @Override
    public MauSac getById(Long id) {

        return mauSacRepository.findById(id).get();
    }


    @Override
    public Integer genMaTuDong() {
        String maStr = "";
        try {
            if (mauSacRepository.index() != null) {
                maStr = mauSacRepository.index().toString();
            } else {
                maStr = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (maStr == null) {
            maStr = "0";
            int ma = Integer.parseInt(maStr);
            return ++ma;
        }
        int ma = Integer.parseInt(maStr);
        return ++ma;
    }

}
