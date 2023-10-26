package com.example.bedatnsd47.service.impl;

import com.example.bedatnsd47.entity.SanPham;
import com.example.bedatnsd47.entity.ThuongHieu;
import com.example.bedatnsd47.repository.ThuongHieuRepository;
import com.example.bedatnsd47.service.ThuongHieuService;
import jakarta.transaction.Transactional;
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
public class ThuongHieuServiceImpl implements ThuongHieuService {

    @Autowired
    private ThuongHieuRepository thuongHieuRepository;

    @Override
    public List<ThuongHieu> findAll() {
        return thuongHieuRepository.findAll();
    }

    @Override
    public Optional<ThuongHieu> findById(Long id) {
        return thuongHieuRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    @Transactional
    public void saveOrUpdate(ThuongHieu thuongHieu, String ten) {
        try {
            thuongHieu.setTen(ten);
            thuongHieu.setNgayTao(new Date());
            thuongHieu.setNgaySua(new Date());
            thuongHieu.setTrangThai(1);
            thuongHieuRepository.save(thuongHieu);
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            // Handle the exception as needed
        }
    }

    @Override
    public void update(ThuongHieu thuongHieu, Long id, Integer trangThai, String ten,Date ngayTao) {
        try {
            List<ThuongHieu> list = ThuongHieuServiceImpl.this.findAll();
            boolean duplicateFound = false;
            for (ThuongHieu thuongHieuTim : list
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
                thuongHieuRepository.save(thuongHieu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ThuongHieu findByTen(String ten) {
        return thuongHieuRepository.findByTen(ten);
    }

    @Override
    public Page<ThuongHieu> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "ngaySua"));
        return thuongHieuRepository.findAll(pageable);

    }

    @Override
    public Page<ThuongHieu> findByTenContaining(String keyword, Integer trang_thai, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "ngaySua"));
        return thuongHieuRepository.findByTenContainingAndTrangThai(keyword, trang_thai, pageable);
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

    @Override
    public Integer checkPageNo(Integer pageNo) {
        Integer sizeList = thuongHieuRepository.findAll().size();
        Integer pageCount = (int) Math.ceil((double) sizeList / 5);
        if (pageNo >= pageCount) {
            pageNo = 0;
        } else if (pageNo < 0) {
            pageNo = pageCount - 1;
        }
        return pageNo;
    }

    @Override
    public Page<ThuongHieu> getPage(Integer pageNo) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return thuongHieuRepository.findAll(PageRequest.of(pageNo, 5,sort));
    }


}
