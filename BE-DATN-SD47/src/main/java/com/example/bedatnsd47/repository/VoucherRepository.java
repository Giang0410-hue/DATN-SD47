package com.example.bedatnsd47.repository;

import com.example.bedatnsd47.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Long> {

    @Query(value = "select * from voucher where trang_thai = 0",nativeQuery = true)
    List<Voucher> fillAllDangDienRa();

    @Query(value = "select * from voucher where trang_thai = 1",nativeQuery = true)
    List<Voucher> fillAllDaKetThuc();

    @Query(value = "select * from voucher where trang_thai = 2",nativeQuery = true)
    List<Voucher> fillAllSapDienRa();

    @Query(value = "SELECT MAX(CONVERT(varchar, SUBSTRING(ma_voucher,9,10))) from voucher",nativeQuery = true)
    Integer index();

}

