package com.example.bedatnsd47.repository;

import com.example.bedatnsd47.entity.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaiKhoanRepository extends JpaRepository<TaiKhoan,Long> {
    Optional<TaiKhoan> findByTenTaiKhoan(String name);

    public TaiKhoan findByMatKhau(String code);
}
