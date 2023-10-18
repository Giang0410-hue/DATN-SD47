package com.example.bedatnsd47.service.impl;

import com.example.bedatnsd47.entity.Voucher;
import com.example.bedatnsd47.repository.VoucherRepository;
import com.example.bedatnsd47.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoucherServiceImpl implements VoucherService {

    @Autowired
    VoucherRepository voucherRepository;


    @Override
    public List<Voucher> findAll() {
        return voucherRepository.findAll();
    }

    @Override
    public Voucher findById(Long id) {
        return voucherRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        voucherRepository.deleteById(id);
    }

    @Override
    public void saveOrUpdate(Voucher voucher) {
        voucherRepository.save(voucher);
    }
}
