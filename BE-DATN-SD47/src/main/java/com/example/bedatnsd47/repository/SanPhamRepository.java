package com.example.bedatnsd47.repository;

import com.example.bedatnsd47.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, Long> {

    @Query(value = "SELECT MAX(CONVERT(INT, SUBSTRING(Ma,3,10))) from san_pham",nativeQuery = true)
    Integer index();
}
