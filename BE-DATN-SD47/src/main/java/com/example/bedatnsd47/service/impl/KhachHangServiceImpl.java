package com.example.bedatnsd47.service.impl;

import com.example.bedatnsd47.entity.TaiKhoan;
import com.example.bedatnsd47.repository.KhachHangRepository;
import com.example.bedatnsd47.service.KhachHangService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KhachHangServiceImpl implements KhachHangService {

    @Autowired
    KhachHangRepository repository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public List<TaiKhoan> getAll() {

        return repository.fillAllKhachHang();

    }

    @Override
    public List<TaiKhoan> getAllDangHoatDong() {

        return repository.fillAllDangHoatDong();

    }

    @Override
    public List<TaiKhoan> getAllNgungHoatDong() {

        return repository.fillAllNgungHoatDong();

    }

    @Override
    public TaiKhoan add(TaiKhoan taiKhoan) {

        return repository.save(taiKhoan);

    }

    @Override
    public TaiKhoan update(TaiKhoan taiKhoan) {

        return repository.save(taiKhoan);

    }

    @Override
    public void remove(Long id) {

        repository.deleteById(id);

    }

    @Override
    public TaiKhoan getById(Long id) {

        return repository.findById(id).get();

    }


    @Override
    public boolean checkTenTrung(String ten) {
//        for (TaiKhoan sp : repository.findAll()) {
//            if (sp.getTenTaiKhoan().equalsIgnoreCase(ten)) {
//                return false;
//            }
//        }
        return true;
    }

    @Override
    public boolean checkTenTaiKhoanTrung(String ten) {
        for (TaiKhoan sp : repository.findAll()) {
            if (sp.getTenTaiKhoan().equalsIgnoreCase(ten)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean checkEmail(String email) {
        for (TaiKhoan sp : repository.findAll()) {
            if (sp.getEmail().equalsIgnoreCase(email)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean checkTenTkTrungSua(Long id, String ten) {
        for (TaiKhoan sp : repository.findAll()) {
            if (sp.getTenTaiKhoan().equalsIgnoreCase(ten)) {
                if (!sp.getId().equals(id)) {
                    return false;
                }

            }
        }
        return true;
    }

    @Override
    public boolean checkEmailSua(Long id, String email) {
        for (TaiKhoan sp : repository.findAll()) {
            if (sp.getEmail().equalsIgnoreCase(email)) {
                if (!sp.getId().equals(id)) {
                    return false;
                }

            }
        }
        return true;
    }

    @Override
    public void sendEmail(TaiKhoan taiKhoan, String path, String random) {
        String from = "daspabitra55@gmail.com";
        String to = taiKhoan.getEmail();
        String subject = "Account Verfication";
        String content = "Khách hàng" + "<br>" + "Tài khoản  " + taiKhoan.getTenTaiKhoan() + "<br>" + "Mật khẩu   " + random;
        try {

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom(from, "Becoder");
            helper.setTo(to);
            helper.setSubject(subject);
            content = content.replace("[[name]]", taiKhoan.getTenTaiKhoan());
            String siteUrl = "Mật khẩu" + random + "Tài khoản" + taiKhoan.getTenTaiKhoan();

            System.out.println(siteUrl);

            content = content.replace("[[URL]]", siteUrl);

            helper.setText(content, true);

            javaMailSender.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
