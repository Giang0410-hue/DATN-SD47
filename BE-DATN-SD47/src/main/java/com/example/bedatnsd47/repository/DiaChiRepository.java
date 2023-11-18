package com.example.bedatnsd47.repository;

import com.example.bedatnsd47.entity.DiaChi;
import com.example.bedatnsd47.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiaChiRepository extends JpaRepository<DiaChi,Long> {

        @Query(value = "select*from dia_chi where tai_khoan_id = :idTaiKhoan order by trang_thai asc",nativeQuery = true)
        List<DiaChi> getAllByIdTaiKhoan(@Param("idTaiKhoan") Long id);


}
