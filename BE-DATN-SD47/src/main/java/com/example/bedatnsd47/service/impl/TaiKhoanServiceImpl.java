package com.example.bedatnsd47.service.impl;

import com.example.bedatnsd47.entity.TaiKhoan;
import com.example.bedatnsd47.entity.VaiTro;
import com.example.bedatnsd47.repository.TaiKhoanRepository;
import com.example.bedatnsd47.service.TaiKhoanService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//@Service
//public class TaiKhoanServiceImpl implements TaiKhoanService {
//    @Autowired
//    private TaiKhoanRepository repository;


//    @Autowired
////    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private JavaMailSender javaMailSender;
//
//    @Override
//    public String addUser(TaiKhoan userInfo) {
//        userInfo.setTrangThai(1);
//        userInfo.setMatKhau(passwordEncoder.encode(userInfo.getMatKhau()));
//        repository.save(userInfo);
//        return "user added to system";
//    }
//
//    //   Cac phương thức để  email xác nhận
//    @Override
//    public void sendEmail(TaiKhoan taiKhoan, String url) {
//        String from = "daspabitra55@gmail.com";
//        String to = taiKhoan.getEmail();
//        String subject = "Account Verfication";
//        String content = "Dear [[name]],<br>" + "Please click the link below to verify your registration:<br>"
//                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>" + "Thank you,<br>" + "Becoder";
//        try {
//
//            MimeMessage message = javaMailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(message);
//
//            helper.setFrom(from, "Becoder");
//            helper.setTo(to);
//            helper.setSubject(subject);
//
//            content = content.replace("[[name]]", taiKhoan.getTenTaiKhoan());
//            String siteUrl = url + "/verify?code=" + taiKhoan.getMatKhau();
//
//            System.out.println(siteUrl);
//
//            content = content.replace("[[URL]]", siteUrl);
//
//            helper.setText(content, true);
//
//            javaMailSender.send(message);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    @Override
//    public boolean verifyAccount(String verificationPassWord) {
//        TaiKhoan user = repository.findByMatKhau(verificationPassWord);
//
//        if (user == null) {
//            return false;
//        } else {
//
//            user.setTrangThai(1);
//            repository.save(user);
//
//            return true;
//        }
//    }
//
//    @Override
//    public TaiKhoan saveUser(TaiKhoan user, String url) {
//        String password = passwordEncoder.encode(user.getMatKhau());
//        user.setMatKhau(password);
//        VaiTro vaiTro = new VaiTro();
//        vaiTro.setId(Long.valueOf(2));
//        user.setVaiTro(vaiTro);
//
//        user.setTrangThai(0);
//        TaiKhoan newuser =  repository.save(user);
//
//        if (newuser != null) {
//            sendEmail(newuser, url);
//        }
//
//        return newuser;
//    }
//}
