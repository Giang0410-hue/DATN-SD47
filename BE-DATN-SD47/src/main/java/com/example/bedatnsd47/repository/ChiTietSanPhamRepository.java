package com.example.bedatnsd47.repository;

import com.example.bedatnsd47.entity.ChiTietSanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChiTietSanPhamRepository extends JpaRepository<ChiTietSanPham,Long> {

}
