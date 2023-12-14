package com.example.bedatnsd47.service.impl;

import com.example.bedatnsd47.entity.HoaDon;
import com.example.bedatnsd47.entity.HoaDonChiTiet;
import com.example.bedatnsd47.repository.HoaDonRepository;
import com.example.bedatnsd47.service.HoaDonService;
import jakarta.activation.DataSource;
import jakarta.mail.internet.MimeMessage;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@Service
public class HoaDonServiceImpl implements HoaDonService {

    @Autowired
    HoaDonRepository hoaDonRepository;

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private ResourceLoader resourceLoader;

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
    public HoaDon finByHoaDonMaHDSdt(String maDonHang, String sdt) {
        return hoaDonRepository.finByHoaDonMaHDSdt(maDonHang, sdt);
    }

    @Override
    public List<HoaDon> findAllOrderByNgaySua() {
        return hoaDonRepository.findAllOrderByNgaySua();
    }

    @Override
    public List<HoaDon> getHoaDonByTaiKhoanByTrangThaiOrderByNgaySua(Long idTaiKhoan, Integer trangThai) {

        return hoaDonRepository.findAllHoaDonByTaiKhoanAndTrangThaiOrderByNgaySua(idTaiKhoan, trangThai);

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
        return hoaDonRepository.countHoaDonBetween(startDate, endDate);
    }

    @Override
    public Long sumGiaTriHoaDonBetween(Date startDate, Date endDate) {
        return hoaDonRepository.sumGiaTriHoaDonBetween(startDate, endDate);
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
        HoaDon hoaDon = this.findById(10314L);

        String from = "glacatshopshoes@gmail.com";
        String to = "anhntph27418@fpt.edu.vn";
        String subject = "Đơn hàng " + hoaDon.getMaHoaDon() + " đã giao hàng thành công\n";
        StringBuilder content = new StringBuilder();
        int index = 0;
        content.append("<p style=\"color: black;\"><b>Xin chào ").append(hoaDon.getNguoiNhan()).append(",</b></p>");

        if (hoaDon.getTrangThai() == 1) {
            content.append("<p style=\"color: black;\">Chúng tôi cảm ơn vì bạn đã lựa chọn mua sắm tại Glacat.</p>")
                    .append("<p style=\"color: black;\">Chúng tôi xin thông báo rằng đơn hàng của bạn có mã số " + hoaDon.getMaHoaDon() + " đang được chuẩn bị và sẽ sớm được giao đến địa chỉ bạn đã cung cấp.</p>");

        } else if (hoaDon.getTrangThai() == 2) {
            content.append("<p style=\"color: black;\"></p>")
                    .append("<p style=\"color: black;\">.</p>");

        }

        content.append("<p style=\"color: black;\">Chúng tôi xin chân thành cảm ơn bạn đã lựa chọn Glacat là địa chỉ mua sắm của mình.</p>")
                .append("<p style=\"color: black;\">Đơn hàng ")
                .append("<span style=\"color:red\">" + hoaDon.getMaHoaDon() + "</span>")
                .append(" của bạn đã được giao thành công. Dưới đây là chi tiết về đơn hàng:</p>");

        if (hoaDon.getTrangThai() != 5) {
            content.append("<section class=\"row\" style=\"margin: auto;background-color: white\">" +
                    "    <div class=\"col-md-12\" style=\"text-align: center\">" +
                    "        <div class=\"mid\">" +
                    "            <h2>THÔNG TIN ĐƠN HÀNG</h2>" +
                    "            <h4>Mã đơn hàng: ").append("<span style=\"color:red\">" + hoaDon.getMaHoaDon() + "</span>").append(" </h4>" +
                    "            <h4>Số điện thoại: ").append("<span style=\"color:red\">" + hoaDon.getSdtNguoiNhan() + "</span>").append(" </h4>" +
                    "            <h4>Trạng thái: ").append("<span style=\"color:red\">" + hoaDon.getStringTrangThai() + "</span>").append(" </h4>" +
                    "        </div>" +
                    "    </div>" +
                    "        <div style=\"border-bottom: 1px solid black;margin-bottom:2%;\"></div>" +
                    "    <div class=\"col-md-12\" style=\"text-align: center;margin-bottom: 1rem;\">" +
                    "        <table class=\"table\" style=\"margin: auto;width: 100%\">" +
                    "            <thead>" +
                    "            <tr>" +
                    "                <th scope=\"col\">STT</th>" +
                    "                <th scope=\"col\">Tên Sản Phẩm</th>" +
                    "                <th scope=\"col\">Số Lượng</th>" +
                    "                <th scope=\"col\">Giá</th>" +
                    "            </tr>");
            content
                    .append("</thead>")
                    .append("<tbody>");
            for (HoaDonChiTiet hoaDonChiTiet : hoaDon.getLstHoaDonChiTiet()) {
                content.append("<tr>")
                        .append("<td style=\"padding: 0.75rem;border-top: 1px solid #dee2e6;\">" + index++ + "</td>")
                        .append("<td style=\"padding: 0.75rem;border-top: 1px solid #dee2e6;\">" + hoaDonChiTiet.getChiTietSanPham().getSanPham().getTen() + "</td>")
                        .append("<td style=\"padding: 0.75rem;border-top: 1px solid #dee2e6;\">" + hoaDonChiTiet.getSoLuong() + "</td>")
                        .append("<td style=\"padding: 0.75rem;border-top: 1px solid #dee2e6;\">" + hoaDonChiTiet.getDonGia() + " VND" + "</td>")
                        .append("</tr>");
            }
            content.append("</tbody>")
                    .append("</table>")
                    .append("</div>");

            content.append(
                    "    <div class=\"col-md-12\">" +
                            "        <div style=\"border-bottom: 1px solid black\"></div>" +
                            "        <div>" +
                            "            <p>" +
                            "                <span style=\"display: inline-block;width: 200px;font-weight: bold;margin-bottom: 10px;margin-left: 50%;\">Tổng tiền hàng:</span>" +
                            "                <span style=\"white-space: nowrap;float: right;font-weight: bold;\">500000 VND</span>" +
                            "            </p>" +
                            "            <p><span style=\"display: inline-block;width: 200px;font-weight: bold;margin-bottom: 10px;margin-left: 50%;\">Phí vận chuyển:</span>" +
                            "                <span style=\"white-space: nowrap;float: right;font-weight: bold;\">500000 VND</span>" +
                            "            </p>" +
                            "            <p><span style=\"display: inline-block;width: 200px;font-weight: bold;margin-bottom: 10px;margin-left: 50%;\">Tiền giảm:</span>" +
                            "                <span style=\"white-space: nowrap;float: right;font-weight: bold;\">500000 VND</span>" +
                            "            </p>" +
                            "            <p><span style=\"display: inline-block;width: 200px;font-weight: bold;margin-bottom: 10px;margin-left: 50%;\">Tổng tiền thanh toán:</span>" +
                            "                <span style=\"white-space: nowrap;float: right;font-weight: bold;color:red;\">500000 VND</span></p>" +
                            "        </div>" +
                            "    </div>" + "</section>"
            );
        }
        content.append(
                "<div class=\"col-md-12\">" +
                        "<p style=\"color: black;\" class=\"email-content\">\n" +
                        "Chúc mừng bạn đã nhận được sản phẩm. Chúng tôi hy vọng rằng nó sẽ đáp ứng hoặc vượt quá mong đợi của bạn. Nếu có bất kỳ thắc mắc nào hoặc cần hỗ trợ, hãy liên hệ với chúng tôi qua số 0377463664 để được hỗ trợ.\n" +
                        "</p>" +
                        "<p style=\"color: black;\">Trân trọng,</p>" +
                        "<p style=\"color: black;\">Shop Glacat</p>" +
                        "</div>"

        );

        try {

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setFrom(from, "Glacat");
            helper.setTo(to);
            helper.setSubject(subject);


            helper.setText(String.valueOf(content), true);

            javaMailSender.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
