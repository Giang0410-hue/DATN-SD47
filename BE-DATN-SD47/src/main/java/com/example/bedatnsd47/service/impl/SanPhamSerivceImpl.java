package com.example.bedatnsd47.service.impl;

import com.example.bedatnsd47.entity.HinhAnhSanPham;
import com.example.bedatnsd47.entity.SanPham;
import com.example.bedatnsd47.repository.HinhAnhSanPhamRepository;
import com.example.bedatnsd47.repository.SanPhamRepository;
import com.example.bedatnsd47.service.SanPhamSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SanPhamSerivceImpl implements SanPhamSerivce {

    @Autowired
    private SanPhamRepository repository;




    @Override
    public List<SanPham> getAll() {

        return repository.findAll();

    }

//    public void saveImage(List<MultipartFile> files, SanPham sanPham) {
//        for (MultipartFile multipartFile : files) {
//            if (!multipartFile.isEmpty()) {
//                try {
//                    HinhAnhSanPham hinhAnh = new HinhAnhSanPham();
//                    // Lưu tệp vào cơ sở dữ liệu
//                    hinhAnh.setUrl(multipartFile.getOriginalFilename());
//                    hinhAnh.setNgayTao(currentDate);
//                    hinhAnh.setTrangThai(1);
//                    hinhAnh.setSanPham(sanPham);
//                    // Thực hiện các tác vụ khác nếu cần thiết
//                    hinhAnhSanPhamRepository.save(hinhAnh);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    // Xử lý lỗi
//                }
//            }
//        }
//    }

    @Override
    public SanPham add(SanPham sanPham) {
        return repository.save(sanPham);

    }

    @Override
    public SanPham update(SanPham sanPham) {

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
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return repository.findAll(PageRequest.of(pageNo, 5, sort));

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

    @Override
    public boolean checkTenTrung(String ten) {

        for (SanPham sp : repository.findAll()) {
            if (sp.getTen().equalsIgnoreCase(ten)) {
                return false;
            }
        }
        return true;

    }

    @Override
    public boolean checkTenTrungSua(String ma, String ten) {

        for (SanPham sp : repository.findAll()) {
            if (sp.getTen().equalsIgnoreCase(ten)) {
                if (!sp.getMa().equalsIgnoreCase(ma)) {
                    return false;
                }
            }
        }
        return true;

    }

}
