package com.example.bedatnsd47.service.impl;

import com.example.bedatnsd47.entity.TaiKhoan;
import com.example.bedatnsd47.repository.KhachHangRepository;
import com.example.bedatnsd47.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KhachHangServiceImpl implements KhachHangService {

    @Autowired
    KhachHangRepository repository;

    @Override
    public List<TaiKhoan> getAll() {
        return repository.findAll();
    }

    @Override
    public TaiKhoan add(TaiKhoan taiKhoan) {
        return repository.save(taiKhoan);
    }

    @Override
    public TaiKhoan update(TaiKhoan taiKhoan) {
        return repository.save(taiKhoan);
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
    public Page<TaiKhoan> getPage(Integer pageNo) {
        return repository.findAll(PageRequest.of(pageNo, 5));
    }

    @Override
    public Integer checkPageNo(Integer pageNo) {
        Integer sizeList = repository.findAll().size();
        Integer pageCount = (int) Math.ceil((double) sizeList / 5);
        if (pageNo >= pageCount) {
            pageNo = 0;
        } else if (pageNo < 0) {
            pageNo = pageCount - 1;
        }
        return pageNo;
    }

    @Override
    public Integer genMaTuDong() {
        return null;
    }

    @Override
    public boolean checkTenTrung(String ten) {
//        for (TaiKhoan sp : repository.findAll()) {
//            if (sp.getTen_tai_khoan().equalsIgnoreCase(ten)) {
//                return false;
//            }
//        }
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
    public boolean checkEmail(String email) {
        for (TaiKhoan sp : repository.findAll()) {
            if (sp.getEmail().equalsIgnoreCase(email)) {
                return false;
            }
        }
        return true;
    }

//    @Override
//    public boolean checkTenTkTrungSua(Long id, String ten) {
//        for (TaiKhoan sp : repository.findAll()) {
//            if (sp.getTen_tai_khoan().equalsIgnoreCase(ten)) {
//                if(!sp.getId().equals(id)){
//                    return false;
//                }
//
//            }
//        }
//        return true;
//    }

    @Override
    public boolean checkTenTkTrungSua(Long id, String ten) {
        for (TaiKhoan sp : repository.findAll()) {
            if (sp.getTen_tai_khoan().equalsIgnoreCase(ten)) {
                if(!sp.getId().equals(id)){
                    return false;
                }

            }
        }
        return true;
    }

    @Override
    public boolean checkEmailSua(Long id, String email) {
        for (TaiKhoan sp : repository.findAll()) {
            if (sp.getEmail().equalsIgnoreCase(email)) {
                if(!sp.getId().equals(id)){
                    return false;
                }

            }
        }
        return true;
    }
}
