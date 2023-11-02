package com.example.bedatnsd47.service.impl;

import com.example.bedatnsd47.entity.TaiKhoan;
import com.example.bedatnsd47.repository.NhanVienRepository;
import com.example.bedatnsd47.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NhanVienServiceImpl implements NhanVienService {

    @Autowired
    private NhanVienRepository repository;

    @Override
    public List<TaiKhoan> getAll() {

        return repository.fillAllNhanVien();

    }

    @Override
    public List<TaiKhoan> getAllDangHoatDong() {

        return repository.fillAllDangHoatDong();

    }

    @Override
    public List<TaiKhoan> getAllNgungHoatDong() {

        return repository.fillAllNgungHoatDong();

    }

    @Override
    public TaiKhoan add(TaiKhoan nhanVien) {

        return repository.save(nhanVien);

    }

    @Override
    public TaiKhoan update(TaiKhoan nhanVien) {

        return repository.save(nhanVien);
    }

    @Override
    public void remove(Long id) {

        repository.deleteById(id);

    }

    @Override
    public TaiKhoan getById(Long id) {

        return repository.findById(id).get();

    }

    @Override
    public boolean checkTenTrung(String ten) {

        for (TaiKhoan sp : repository.findAll()) {
            if (sp.getTen_tai_khoan().equalsIgnoreCase(ten)) {
                return false;
            }
        }
        return true;

    }

    @Override
    public boolean checkTenTrungSua(String id, String ten) {

        for (TaiKhoan tk : repository.findAll()) {
            if (tk.getTen_tai_khoan().equalsIgnoreCase(ten)) {
                if (!tk.getId().equals(id)) {
                    return false;
                }
            }
        }
        return true;

    }

    @Override
    public boolean checkTenTkTrungSua(Long id, String ten) {

        for (TaiKhoan sp : repository.findAll()) {
            if (sp.getTen_tai_khoan().equalsIgnoreCase(ten)) {
                if (!sp.getId().equals(id)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean checkTenTaiKhoanTrung(String ten) {

        for (TaiKhoan sp : repository.findAll()) {
            if (sp.getTen_tai_khoan().equalsIgnoreCase(ten)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean checkEmailSua(Long id, String email) {

        for (TaiKhoan sp : repository.findAll()) {
            if (sp.getEmail().equalsIgnoreCase(email)) {
                if (!sp.getId().equals(id)) {
                    return false;
                }

            }
        }
        return true;

    }

    @Override
    public boolean checkEmail(String email) {
        for (TaiKhoan sp : repository.findAll()) {
            if (sp.getEmail().equalsIgnoreCase(email)) {
                return false;
            }
        }
        return true;
    }

}
