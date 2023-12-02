package com.example.bedatnsd47.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "lich_su_hoa_don")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LichSuHoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ngay_tao")
    private Date ngayTao;

    @Column(name = "ngay_sua")
    private Date ngaySua;

    @Column(name = "ghi_chu", length = 255)
    private String ghiChu;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hoa_don_id", referencedColumnName = "id")
    private HoaDon hoaDon;

    public String getStringTrangThai() {
        if (this.trangThai == null) {
            return "Trạng thái null";
        }
        switch (this.trangThai) {
            case 0:
                return "Tạo hóa đơn";
            case 1:
                return "Đặt hàng thành công";
            case 2:
                return "Đã bàn giao cho đơn vị vận chuyển";
            case 3:
                return "Đã thanh toán";
            case 4:
                return "Đã xác nhận";
            case 5:
                return "Đã hủy";
            case 6:
                return "Đã giao";

            default:
                return "";

        }

    }
}
