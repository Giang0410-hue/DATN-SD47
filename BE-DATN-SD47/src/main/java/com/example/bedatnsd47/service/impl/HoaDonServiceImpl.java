package com.example.bedatnsd47.service.impl;

import com.example.bedatnsd47.entity.HoaDon;
import com.example.bedatnsd47.repository.HoaDonRepository;
import com.example.bedatnsd47.service.HoaDonService;
import jakarta.mail.internet.MimeMessage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class HoaDonServiceImpl implements HoaDonService {

    @Autowired
    HoaDonRepository hoaDonRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public List<HoaDon> findAll() {
        return hoaDonRepository.findAll();
    }

    @Override
    public HoaDon findById(Long id) {
        return hoaDonRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        hoaDonRepository.deleteById(id);
    }

    @Override
    public void saveOrUpdate(HoaDon hoaDon) {
        hoaDonRepository.save(hoaDon);
    }

    @Override
    public List<HoaDon> findByTrangThai(Integer trangThai) {
        return hoaDonRepository.findByTrangThai(trangThai);
    }

    @Override
    public Integer countHoaDonTreo() {
        return hoaDonRepository.countHoaDonTreo();
    }

    @Override
    public List<HoaDon> find5ByTrangThai(Integer trangThai) {
        return hoaDonRepository.find5ByTrangThai(trangThai);
    }

    @Override
    public HoaDon findByMa(String ma) {
        return hoaDonRepository.findByMa(ma);
    }

    @Override
    public HoaDon finByHoaDonMaHDSdt(String maDonHang,String sdt) {
        return hoaDonRepository.finByHoaDonMaHDSdt(maDonHang,sdt);
    }

    @Override
    public List<HoaDon> findAllOrderByNgaySua() {
        return hoaDonRepository.findAllOrderByNgaySua();
    }

    @Override
    public List<HoaDon> getHoaDonByTaiKhoanByTrangThaiOrderByNgaySua(Long idTaiKhoan,Integer trangThai) {

        return hoaDonRepository.findAllHoaDonByTaiKhoanAndTrangThaiOrderByNgaySua(idTaiKhoan,trangThai);

    }

    @Override
    public Integer countHoaDonDay(Date ngayTao) {
        return hoaDonRepository.countHoaDonNgay(ngayTao);
    }

    @Override
    public List<HoaDon> getAllHoaDonByTaiKhoanOrderByNgaySua(Long idTaiKhoan) {

        return hoaDonRepository.findAllHoaDonByTaiKhoanOrderByNgaySua(idTaiKhoan);

    }


    @Override
    public Long sumHoaDonDay(Date ngayTao) {
        return hoaDonRepository.sumGiaTriHoaDonNgay(ngayTao);
    }

    @Override
    public Integer countHoaDonMonth(Date ngayTao) {
        return hoaDonRepository.countHoaDonThang(ngayTao);
    }

    @Override
    public Long sumHoaDonMonth(Date ngayTao) {
        return hoaDonRepository.sumGiaTriHoaDonThang(ngayTao);
    }

    @Override
    public Integer countHoaDon(Integer trangThai) {
        return hoaDonRepository.countHoaDon(trangThai);
    }

    @Override
    public Integer countHoaDonBetween(Date startDate, Date endDate) {
        return hoaDonRepository.countHoaDonBetween(startDate,endDate);
    }

    @Override
    public Long sumGiaTriHoaDonBetween(Date startDate, Date endDate) {
        return hoaDonRepository.sumGiaTriHoaDonBetween(startDate,endDate);
    }

    @Override
    public Integer countHoaDonTrangThaiBetween(Date startDate, Date endDate, Integer trangThai) {
        return hoaDonRepository.countHoaDonTrangThaiBetween(startDate, endDate, trangThai);
    }

    @Override
    public Integer countHoaDonTrangThaiNgay(Date ngayTao, Integer trangThai) {
        return hoaDonRepository.countHoaDonTrangThaiNgay(ngayTao, trangThai);
    }

    @Override
    public Integer countHoaDonTrangThaiThang(Date ngayTao, Integer trangThai) {
        return hoaDonRepository.countHoaDonTrangThaiThang(ngayTao, trangThai);
    }

    @Override
    public Integer countHoaDonAll() {
        return hoaDonRepository.countHoaDonAll();
    }

    @Override
    public Long sumGiaTriHoaDonAll() {
        return hoaDonRepository.sumGiaTriHoaDonAll();
    }

    public void deleteHoaDonHoanTra() {
        // TODO Auto-generated method stub
        hoaDonRepository.deleteHoaDonHoanTra();
    }

    @Override
    public void guiHoaDonDienTu() {
        String from = "daspabitra55@gmail.com";
        String to = "anhntph27418@fpt.edu.vn";
        String url = "daspabitra55@gmail.com";
        String subject = "Khôi Phục Mật Khẩu Tài Khoản Glacat của Bạn";
        String content = "templates/admin-template/in-hoa-don.html";

        String filePath = "src/main/resources/templates/admin-template/in-hoa-don.html"; // Thay thế đường dẫn với đường dẫn tuyệt đối đến tệp HTML của bạn

        try {
            // Đọc nội dung từ tệp HTML
            Document document = Jsoup.parse(new File(filePath), "UTF-8");

            Element cssLink = document.createElement("link")
                    .attr("rel", "stylesheet")
                    .attr("href", "../../static/css/bootstrap.min.css");
                // Nhúng nội dung CSS vào tài liệu HTML
                document.head().append("<style>.table{\n" +
                        "        margin-top: 3%;\n" +
                        "    }\n" +
                        "    .bottom-hoadon{\n" +
                        "        text-align: center;\n" +
                        "    }\n" +
                        "    .logo-hoa-don {\n" +
                        "        margin-top: 10%;\n" +
                        "        display: flex;\n" +
                        "        align-items: center;\n" +
                        "        justify-content: center;\n" +
                        "        text-align: center;\n" +
                        "    }\n" +
                        "\n" +
                        "    .logo-hoa-don img {\n" +
                        "        max-width: 100%;\n" +
                        "        margin-right: 10px;\n" +
                        "    }\n" +
                        "\n" +
                        "    .logo-hoa-don h1 {\n" +
                        "        margin-top: 10px;\n" +
                        "    }\n" +
                        "\n" +
                        "    .info-label {\n" +
                        "        display: inline-block;\n" +
                        "        width: 200px;\n" +
                        "        font-weight: bold;\n" +
                        "        margin-bottom: 10px;\n" +
                        "        margin-left: 50%;\n" +
                        "    }\n" +
                        "\n" +
                        "    .info-value {\n" +
                        "        white-space: nowrap;\n" +
                        "        float: right;\n" +
                        "        font-weight: bold;\n" +
                        "    }\n" +
                        "\n" +
                        "    .logo-hoa-don2 {\n" +
                        "        text-align: center;\n" +
                        "        margin-top: -3%;\n" +
                        "        margin-bottom: 3%;\n" +
                        "\n" +
                        "    }\n" +
                        "    .logo-hoa-don2 h6{\n" +
                        "        font-weight: bold;\n" +
                        "    }\n" +
                        "\n" +
                        "    .mid {\n" +
                        "        text-align: center;\n" +
                        "    }\n" +
                        "    .mid h2{\n" +
                        "        font-weight: bold;\n" +
                        "    }\n" +
                        "\n" +
                        "    .border-hoadon {\n" +
                        "        border-top: 1px solid #000000;\n" +
                        "        margin-top: 5%;\n" +
                        "        margin-bottom: 5%;\n" +
                        "    }\n" +
                        "\n" +
                        "    .border-hoadon-duoi {\n" +
                        "        border-top: 1px solid #000000;\n" +
                        "        margin-top: 1%;\n" +
                        "        margin-bottom: 5%;\n" +
                        "    }</style>"+cssLink);


            // Lấy toàn bộ nội dung HTML và gán vào biến content
             content = document.outerHtml();

            // Hiển thị nội dung HTML
            System.out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setFrom(from, "Becoder");
            helper.setTo(to);
            helper.setSubject(subject);

            content = content.replace("[[name]]", "123");
            String siteUrl = url + "/verify?code=" + "123";

            System.out.println(siteUrl);

//            content = content.replace("[[URL]]", siteUrl);

            helper.setText(content, true);

            javaMailSender.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
