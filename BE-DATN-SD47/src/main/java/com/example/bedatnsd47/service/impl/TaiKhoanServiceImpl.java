package com.example.bedatnsd47.service.impl;

import com.example.bedatnsd47.entity.GioHang;
import com.example.bedatnsd47.entity.TaiKhoan;
import com.example.bedatnsd47.entity.VaiTro;
import com.example.bedatnsd47.repository.TaiKhoanRepository;
import com.example.bedatnsd47.service.GioHangService;
import com.example.bedatnsd47.service.TaiKhoanService;
import jakarta.mail.internet.MimeMessage;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.groups.Default;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.Set;

@Service
public class TaiKhoanServiceImpl implements TaiKhoanService {
    @Autowired
    private TaiKhoanRepository repository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private GioHangService gioHangService;

    @Override
    public String addUser(TaiKhoan userInfo) {
        userInfo.setTrangThai(0);
        userInfo.setMatKhau(passwordEncoder.encode(userInfo.getMatKhau()));
        repository.save(userInfo);
        GioHang gioHang = new GioHang();
        Date currentDate = new Date();
        gioHang.setMaGioHang("GH" + gioHangService.genMaTuDong());
        gioHang.setGhiChu("");
        gioHang.setNgayTao(currentDate);
        gioHang.setNgaySua(currentDate);
        gioHang.setTaiKhoan(TaiKhoan.builder().id(userInfo.getId()).build());
        gioHang.setTrangThai(0);
        gioHangService.save(gioHang);
        return "user added to system";
    }

    @Override
    public String updateUser(TaiKhoan userInfo) {
        userInfo.setTrangThai(0);
        userInfo.setMatKhau(passwordEncoder.encode(userInfo.getMatKhau()));
        repository.save(userInfo);
        return "user added to system";
    }

    //   Cac phương thức để  email xác nhận
    @Override
    public void sendEmail(TaiKhoan taiKhoan, String url) {
        String from = "daspabitra55@gmail.com";
        String to = taiKhoan.getEmail();
        String subject = "Khôi Phục Mật Khẩu Tài Khoản Glacat của Bạn";
        String content = "<p class=\"email-content\" style=\"font-family: 'Arial', sans-serif;font-size: 16px;color: #333;line-height: 1.5;\">\n" +
                "Chào [[name]], <br>\n" +
                "Chúc mừng! Bạn đã yêu cầu hướng dẫn khôi phục mật khẩu cho tài khoản của mình trên Glacat. Để tiếp tục quá trình này, vui lòng nhấn vào liên kết dưới đây:\n" +
                "</p>\n" +

                "<p class=\"email-content\">\n" +
                "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>" +
                "</p>\n" +

                "<p class=\"email-content\">\n" +
                "Nếu bạn không yêu cầu hướng dẫn khôi phục mật khẩu hoặc không nhớ việc này, hãy bỏ qua email này. Liên kết xác nhận sẽ hết hạn sau 24 giờ.\n" +
                "<br>\n" +
                "Chân thành cảm ơn,\n" +
                "<br>\n" +
                "Đội ngũ Glacat\n" +
                "</p>";
        try {

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setFrom(from, "Becoder");
            helper.setTo(to);
            helper.setSubject(subject);

            content = content.replace("[[name]]", taiKhoan.getTenTaiKhoan());
            String siteUrl = url + "/verify?code=" + taiKhoan.getMatKhau();

            System.out.println(siteUrl);

            content = content.replace("[[URL]]", siteUrl);

            helper.setText(content, true);

            javaMailSender.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void sendEmail1(TaiKhoan taiKhoan, String url, String random) {
        String from = "daspabitra55@gmail.com";
        String to = taiKhoan.getEmail();
        String subject = "Chào mừng bạn đến với Glacat - Xác Minh Tài Khoản của Bạn";
        String content =
                "<p class=\"email-content\" style=\"font-family: 'Arial', sans-serif;font-size: 16px;color: #333;line-height: 1.5;\">\n" +
                        "Chào [[name]], <br>\n" +
                        "Chúc mừng! Tài khoản Khách Hàng mới của bạn tại Glacat đã được tạo thành công. Để bảo vệ tính bảo mật của tài khoản, chúng tôi cần xác minh rằng địa chỉ email này thuộc về bạn.\n" +
                        "</p>\n" +

                        "<p class=\"email-content\">\n" +
                        "**Mã Xác Minh Tài Khoản:\n" +
                        "<span class=\"verification-code\" style=\"color: #ff0000;font-weight: bold;\">" + random + "</span> **\n" +
                        "</p>\n" +

                        "<p class=\"email-content\">\n" +
                        "Hãy sử dụng mã xác minh trên để hoàn tất quá trình đăng ký của bạn. Nếu bạn không thực hiện thao tác này, tài khoản của bạn có thể không được kích hoạt.\n" +
                        "<br>\n" +
                        "Cảm ơn bạn đã chọn Glacat! Nếu bạn có bất kỳ câu hỏi hoặc cần hỗ trợ, đừng ngần ngại liên hệ với chúng tôi.\n" +
                        "</p>";
        try {

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setFrom(from, "Becoder");
            helper.setTo(to);
            helper.setSubject(subject);

            content = content.replace("[[name]]", taiKhoan.getTenTaiKhoan());
            String siteUrl = random;

            System.out.println(siteUrl);

            content = content.replace("[[URL]]", siteUrl);

            helper.setText(content, true);

            javaMailSender.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean verifyAccount(String verificationPassWord, String resetPass) {
        TaiKhoan user = repository.findByMatKhau(verificationPassWord);

        if (user == null) {
            return false;
        } else {
            user.setMatKhau(resetPass);
            repository.save(user);
            return true;
        }
    }

    @Override
    public TaiKhoan saveUser(TaiKhoan user, String url) {
        String password = passwordEncoder.encode(user.getMatKhau());
        user.setMatKhau(password);
        VaiTro vaiTro = new VaiTro();
        vaiTro.setId(Long.valueOf(2));
        user.setVaiTro(vaiTro);

        user.setTrangThai(0);
        TaiKhoan newuser = repository.save(user);

        if (newuser != null) {
            sendEmail(newuser, url);
        }

        return newuser;
    }

    @Override
    public TaiKhoan getTaiKhoanByName(String name) {

        return repository.findByTenTaiKhoan(name).orElse(null);

    }
}
