package com.example.bedatnsd47.service.impl;

import com.example.bedatnsd47.entity.HoaDon;
import com.example.bedatnsd47.repository.HoaDonRepository;
import com.example.bedatnsd47.service.HoaDonService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

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
        String content = "<section class=\"row\" style=\"margin: auto;margin-top:15%;width: 900px;height:1000px;background-color: white\">\n" +
                "    <div class=\"col-md-12\">\n" +
                "\n" +
                "        <div class=\"logo-hoa-don\">\n" +
                "            <img src=\"../../static/img/logo.png\" alt=\"\">\n" +
                "            <h1>Glacat</h1>\n" +
                "        </div>\n" +
                "        <div class=\"border-hoadon\"></div>\n" +
                "    </div>\n" +
                "    <div class=\"col-md-12\">\n" +
                "        <div class=\"logo-hoa-don2\">\n" +
                "            <h6>Dị Nậu - Thạch Thất - Hà Nội</h6>\n" +
                "            <h6>Tel: 0377463664</h6>\n" +
                "        </div>\n" +
                "        <div class=\"mid\">\n" +
                "            <h2>HÓA ĐƠN BÁN HÀNG</h2>\n" +
                "            <h5>Số HĐ: 123 - 21/01/2003 - 12:00:10</h5>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div class=\"col-md-12\">\n" +
                "        <table class=\"table\">\n" +
                "            <thead>\n" +
                "            <tr>\n" +
                "                <th scope=\"col\">STT</th>\n" +
                "                <th scope=\"col\" colspan=\"2\">Tên Hàng</th>\n" +
                "                <th scope=\"col\">Số Lượng</th>\n" +
                "                <th scope=\"col\">Giá gồm VAT</th>\n" +
                "                <th scope=\"col\">Thành Tiền</th>\n" +
                "            </tr>\n" +
                "            </thead>\n" +
                "            <tbody>\n" +
                "            <tr>\n" +
                "                <th>1</th>\n" +
                "                <td colspan=\"2\">Mar1</td>\n" +
                "                <td>Otto</td>\n" +
                "                <td>@mdo</td>\n" +
                "                <td>@mdo</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <th>2</th>\n" +
                "                <td colspan=\"2\">Jacob</td>\n" +
                "                <td>Thornton</td>\n" +
                "                <td>@fat</td>\n" +
                "                <td>@fat</td>\n" +
                "            </tr>\n" +
                "            </tbody>\n" +
                "        </table>\n" +
                "    </div>\n" +
                "\n" +
                "    <div class=\"col-md-12\">\n" +
                "        <div class=\"border-hoadon-duoi\"></div>\n" +
                "        <div>\n" +
                "            <p><span class=\"info-label\">Tổng tiền:</span> <span class=\"info-value\">500000 VND</span>\n" +
                "            </p>\n" +
                "            <p><span class=\"info-label\">Giảm giá:</span> <span class=\"info-value\">500000 VND</span>\n" +
                "            </p>\n" +
                "            <p><span class=\"info-label\">Phí vận chuyển:</span> <span class=\"info-value\">500000 VND</span>\n" +
                "            </p>\n" +
                "            <p><span class=\"info-label\">Thành tiền:</span> <span class=\"info-value\">500000 VND</span></p>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div class=\"col-md-12\">\n" +
                "        <div class=\"border-hoadon-duoi\"></div>\n" +
                "\n" +
                "        <div class=\"\">\n" +
                "            <h5>KHÁCH HÀNG:</h5>\n" +
                "            <h6>Tên Khách hàng: Nguyễn Tuấn Anh</h6>\n" +
                "            <h6>Số điện thoại: 0377463664</h6>\n" +
                "            <h6>Địa chỉ: Dị Nậu - Thạch Thất - Hà Nội</h6>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div class=\"col-md-12\">\n" +
                "        <div class=\"border-hoadon-duoi\"></div>\n" +
                "\n" +
                "        <div class=\"bottom-hoadon\">\n" +
                "            <h3>Xin cảm ơn quý khách !</h3>\n" +
                "            <h6>Nhân viên: Nguyễn Tuấn Anh</h6>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</section>\n" +
                "<style>\n" +
                "    .inHoaDon .table{\n" +
                "        margin-top: 3%;\n" +
                "    }\n" +
                "    .inHoaDon .bottom-hoadon{\n" +
                "        text-align: center;\n" +
                "    }\n" +
                "    .inHoaDon .logo-hoa-don {\n" +
                "        margin-top: 10%;\n" +
                "        display: flex;\n" +
                "        align-items: center;\n" +
                "        justify-content: center;\n" +
                "        text-align: center;\n" +
                "    }\n" +
                "\n" +
                "    .inHoaDon .logo-hoa-don img {\n" +
                "        max-width: 100%;\n" +
                "        margin-right: 10px;\n" +
                "    }\n" +
                "\n" +
                "    .inHoaDon .logo-hoa-don h1 {\n" +
                "        margin-top: 10px;\n" +
                "    }\n" +
                "\n" +
                "    .inHoaDon .info-label {\n" +
                "        display: inline-block;\n" +
                "        width: 200px;\n" +
                "        font-weight: bold;\n" +
                "        margin-bottom: 10px;\n" +
                "        margin-left: 50%;\n" +
                "    }\n" +
                "\n" +
                "    .inHoaDon .info-value {\n" +
                "        white-space: nowrap;\n" +
                "        float: right;\n" +
                "        font-weight: bold;\n" +
                "    }\n" +
                "\n" +
                "    .inHoaDon .logo-hoa-don2 {\n" +
                "        text-align: center;\n" +
                "        margin-top: -3%;\n" +
                "        margin-bottom: 3%;\n" +
                "\n" +
                "    }\n" +
                "    .inHoaDon .logo-hoa-don2 h6{\n" +
                "        font-weight: bold;\n" +
                "    }\n" +
                "\n" +
                "    .inHoaDon .mid {\n" +
                "        text-align: center;\n" +
                "    }\n" +
                "    .inHoaDon .mid h2{\n" +
                "        font-weight: bold;\n" +
                "    }\n" +
                "\n" +
                "    .inHoaDon .border-hoadon {\n" +
                "        border-top: 1px solid #000000;\n" +
                "        margin-top: 5%;\n" +
                "        margin-bottom: 5%;\n" +
                "    }\n" +
                "\n" +
                "    .inHoaDon .border-hoadon-duoi {\n" +
                "        border-top: 1px solid #000000;\n" +
                "        margin-top: 1%;\n" +
                "        margin-bottom: 5%;\n" +
                "    }\n" +
                "</style>";
        try {

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setFrom(from, "Becoder");
            helper.setTo(to);
            helper.setSubject(subject);

            content = content.replace("[[name]]", "123");
            String siteUrl = url + "/verify?code=" + "123";

            System.out.println(siteUrl);

            content = content.replace("[[URL]]", siteUrl);

            helper.setText(content, true);

            javaMailSender.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
