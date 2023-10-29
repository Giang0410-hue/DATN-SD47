package com.example.bedatnsd47.repository;

import com.example.bedatnsd47.entity.HinhAnhSanPham;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HinhAnhSanPhamRepository extends JpaRepository<HinhAnhSanPham,Long> {

    @Query(value = "select * from hinh_anh_san_pham where san_pham_id = :id",nativeQuery = true)
    List<HinhAnhSanPham> fillAllByIdSp(@Param("id")Long id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM hinh_anh_san_pham WHERE san_pham_id = :id",nativeQuery = true)
    void deleteAllByIdSp(@Param("id")Long id);

}
