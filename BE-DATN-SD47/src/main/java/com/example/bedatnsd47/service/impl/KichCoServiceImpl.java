package com.example.bedatnsd47.service.impl;

import com.example.bedatnsd47.entity.KichCo;
import com.example.bedatnsd47.repository.KichCoRepository;
import com.example.bedatnsd47.service.KichCoService;
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
public class KichCoServiceImpl implements KichCoService {
    @Autowired
    KichCoRepository kichCoRepository;

    @Override
    public List<KichCo> findAll() {
        return kichCoRepository.findAll();
    }

    @Override
    public Optional<KichCo> findById(Long id) {
        return kichCoRepository.findById(id);
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
            kichCoRepository.save(thuongHieu);
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            // Handle the exception as needed
        }
    }

    @Override
    public void update(KichCo thuongHieu, Long id, Integer trangThai, Float ten, Date ngayTao) {
//        try {
//            List<KichCo> list = KichThuocServiceImpl.this.findAll();
//            boolean duplicateFound = false;
//            for (KichCo thuongHieuTim : list
//            ) {
//                if (thuongHieuTim.getId() != id && thuongHieuTim.getTen().equals(ten)) {
//                    duplicateFound = true;
//                    break;
//                }
//            }
//            if (duplicateFound) {
//                System.out.println("Da ton tai");
//            } else {
//                thuongHieu.setId(id);
//                thuongHieu.setTen(ten);
//                thuongHieu.setNgayTao(ngayTao);
//                thuongHieu.setNgaySua(new Date());
//                thuongHieu.setTrangThai(trangThai);
//                kichThuocRepository.save(thuongHieu);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public KichCo findByTen(Float ten) {
        return kichCoRepository.findByTen(ten);
    }

    @Override
    public Page<KichCo> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "ngaySua"));
        return kichCoRepository.findAll(pageable);

    }

    @Override
    public Page<KichCo> findByTenContaining(String keyword, Integer trang_thai, int page, int size) {
        return null;
    }

    @Override
    public boolean checkTenTrung(Float ten) {

        if (ten == null) {
            return false; // No need to check for duplicates if ten is null
        }
        for (KichCo sp : kichCoRepository.findAll()) {
            if (sp.getTen().equals(ten)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean checkTenTrungSua(Long id, Float ten) {

        if (ten == null) {
            return false; // No need to check for duplicates if ten is null
        }
        for (KichCo sp : kichCoRepository.findAll()) {
            if (sp.getTen().equals(ten)) {
                if (!sp.getId().equals(id)){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public KichCo update(KichCo kichCo) {
        return kichCoRepository.save(kichCo);
    }

    @Override
    public KichCo getById(Long id) {
        return kichCoRepository.findById(id).get();
    }

    @Override
    public Integer checkPageNo(Integer pageNo) {
        Integer sizeList = kichCoRepository.findAll().size();
        Integer pageCount = (int) Math.ceil((double) sizeList / 5);
        if (pageNo >= pageCount) {
            pageNo = 0;
        } else if (pageNo < 0) {
            pageNo = pageCount - 1;
        }
        return pageNo;
    }

    @Override
    public Page<KichCo> getPage(Integer pageNo) {
        return kichCoRepository.findAll(PageRequest.of(pageNo, 5));
    }
}
