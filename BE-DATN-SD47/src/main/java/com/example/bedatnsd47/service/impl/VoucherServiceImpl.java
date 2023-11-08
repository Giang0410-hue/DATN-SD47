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
    public List<Voucher> fillAllDangDienRa() {
        return voucherRepository.fillAllDangDienRa();
    }

    @Override
    public List<Voucher> fillAllDaKetThuc() {
        return voucherRepository.fillAllDaKetThuc();
    }

    @Override
    public List<Voucher> fillAllSapDienRa() {
        return voucherRepository.fillAllSapDienRa();
    }


    @Override
    public Voucher save(Voucher voucher) {
        return voucherRepository.save(voucher);
    }

    @Override
    public boolean checkMaTrung(String ma) {
        for (Voucher sp : voucherRepository.findAll()) {
            if (sp.getMaVoucher().equalsIgnoreCase(ma)) {
                return false;
            }
        }
        return true;    }

    @Override
    public boolean checkMaTrungSua(String ma, String ten) {
        for (Voucher sp : voucherRepository.findAll()) {
            if (sp.getMaVoucher().equalsIgnoreCase(ma)) {
                if (!sp.getTenVoucher().equalsIgnoreCase(ten)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean checkTenTrung(String ten) {
        for (Voucher sp : voucherRepository.findAll()) {
            if (sp.getTenVoucher().equalsIgnoreCase(ten)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean checkTenTrungSua(String ma, String ten) {
        for (Voucher sp : voucherRepository.findAll()) {
            if (sp.getTenVoucher().equalsIgnoreCase(ten)) {
                if (!sp.getMaVoucher().equalsIgnoreCase(ma)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public Voucher update(Voucher voucher) {

        return voucherRepository.save(voucher);

    }

    @Override
    public Voucher getById(Long id) {

        return voucherRepository.findById(id).get();

    }

}
