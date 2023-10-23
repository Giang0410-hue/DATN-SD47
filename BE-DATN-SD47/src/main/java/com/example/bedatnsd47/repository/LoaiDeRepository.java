package com.example.bedatnsd47.repository;

import com.example.bedatnsd47.entity.LoaiDe;
import com.example.bedatnsd47.entity.ThuongHieu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoaiDeRepository extends JpaRepository<LoaiDe,Long> {
    @Query("SELECT t FROM LoaiDe t WHERE LOWER(t.ten) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "AND (:trang_thai IS NULL OR t.trangThai = :trang_thai)")
    Page<ThuongHieu> findByTenContainingAndTrangThai(@Param("keyword") String keyword, @Param("trang_thai") Integer trang_thai, Pageable pageable);

    @Query("SELECT t FROM LoaiDe t WHERE t.ten = :ten")
    LoaiDe findByTen(@Param("ten") String ten);
}

