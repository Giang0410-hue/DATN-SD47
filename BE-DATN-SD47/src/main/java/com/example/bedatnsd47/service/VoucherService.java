package com.example.bedatnsd47.service;


import com.example.bedatnsd47.entity.Voucher;

import java.util.List;

public interface VoucherService {
    List<Voucher> findAll();

    Voucher findById(Long id);

    void deleteById(Long id);

    void saveOrUpdate(Voucher voucher);
}
