package com.example.bedatnsd47.service.impl;

import com.example.bedatnsd47.entity.TaiKhoan;
import com.example.bedatnsd47.repository.NhanVienRepository;
import com.example.bedatnsd47.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NhanVienServiceImpl implements NhanVienService {

    @Autowired
    private NhanVienRepository repository;

    @Override
    public List<TaiKhoan> getAll() {

        return repository.findAll();

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
    public Page<TaiKhoan> getPage(Integer pageNo) {
        return repository.findAll(PageRequest.of(pageNo,5));
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
    public boolean checkTenTrung(String ten) {

        return false;

    }

    @Override
    public boolean checkTenTrungSua(String ma, String ten) {

        return false;

    }

}
