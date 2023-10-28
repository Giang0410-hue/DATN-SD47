package com.example.bedatnsd47.service.impl;

import com.example.bedatnsd47.entity.MauSac;
import com.example.bedatnsd47.entity.SanPham;
import com.example.bedatnsd47.entity.ThuongHieu;
import com.example.bedatnsd47.repository.MauSacRepository;
import com.example.bedatnsd47.service.MauSacService;
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
public class MauSacSericeImpl implements MauSacService {

    @Autowired
    MauSacRepository mauSacRepository;

    @Override
    public List<MauSac> findAll() {
        return mauSacRepository.findAll();
    }

    @Override
    public Optional<MauSac> findById(Long id) {
        return mauSacRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void saveOrUpdate(MauSac mauSac, String ten) {
        try {
            mauSac.setTen(ten);
            mauSac.setMaMau(null);
            mauSac.setNgayTao(new Date());
            mauSac.setNgaySua(new Date());
            mauSac.setTrangThai(1);
            mauSacRepository.save(mauSac);
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            // Handle the exception as needed
        }
    }

    @Override
    public void update(MauSac thuongHieu, Long id, Integer trangThai, String ten, Date ngayTao) {
        try {
            List<MauSac> list = MauSacSericeImpl.this.findAll();
            boolean duplicateFound = false;
            for (MauSac thuongHieuTim : list
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
                thuongHieu.setMaMau(null);
                thuongHieu.setNgayTao(ngayTao);
                thuongHieu.setNgaySua(new Date());
                thuongHieu.setTrangThai(trangThai);
                mauSacRepository.save(thuongHieu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public MauSac findByTen(String ten) {
        return mauSacRepository.findByTen(ten);
    }

    @Override
    public Page<MauSac> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "ngaySua"));
        return mauSacRepository.findAll(pageable);
    }

    @Override
    public Page<MauSac> findByTenContaining(String keyword, Integer trang_thai, int page, int size) {

//        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "ngaySua"));
//        return thuongHieuRepository.findByTenContainingAndTrangThai(keyword, trang_thai, pageable);
        return null;
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
    public Integer checkPageNo(Integer pageNo) {
        Integer sizeList = mauSacRepository.findAll().size();
        Integer pageCount = (int) Math.ceil((double) sizeList / 5);
        if (pageNo >= pageCount) {
            pageNo = 0;
        } else if (pageNo < 0) {
            pageNo = pageCount - 1;
        }
        return pageNo;
    }

    @Override
    public Page<MauSac> getPage(Integer pageNo) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return mauSacRepository.findAll(PageRequest.of  (  pageNo, 5,sort));
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
