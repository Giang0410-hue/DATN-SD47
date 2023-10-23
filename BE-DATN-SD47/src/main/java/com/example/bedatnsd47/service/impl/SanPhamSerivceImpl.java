package com.example.bedatnsd47.service.impl;

import com.example.bedatnsd47.entity.SanPham;
import com.example.bedatnsd47.repository.SanPhamRepository;
import com.example.bedatnsd47.service.SanPhamSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SanPhamSerivceImpl implements SanPhamSerivce {

    @Autowired
    private SanPhamRepository repository;

    private String ma = "SP";

    @Override
    public List<SanPham> getAll() {

        return repository.findAll();

    }

    @Override
    public SanPham add(SanPham sanPham) {

        return repository.save(sanPham);

    }

    @Override
    public SanPham update(SanPham sanPham, Long id) {

        return repository.save(sanPham);

    }

    @Override
    public void remove(Long id) {

        repository.deleteById(id);

    }

    @Override
    public SanPham getById(Long id) {

        return repository.findById(id).get();

    }

    @Override
    public Page<SanPham> getPage(Integer pageNo) {

        return repository.findAll(PageRequest.of(pageNo, 5));

    }

    @Override
    public Integer checkPageNo(Integer pageNo) {

        Integer sizeList = repository.findAll().size();
        Integer pageCount = (int) Math.ceil((double) sizeList / 5);
        if (pageNo >= pageCount) {
            pageNo = 0;
        }else if (pageNo < 0) {
            pageNo = pageCount-1;
        }
        return pageNo;
    }

    @Override
    public Integer genMaTuDong() {
        String maStr = "";
        try {

            if (repository.index() != null) {
                maStr = repository.index().toString();
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
