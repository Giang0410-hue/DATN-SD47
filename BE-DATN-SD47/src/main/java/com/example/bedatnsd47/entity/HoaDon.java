package com.example.bedatnsd47.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hoa_don")
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_hoa_don", length = 20)
    private String maHoaDon;

    @Column(name = "ngay_thanh_toan")
    private Date ngayThanhToan;

    @Column(name = "loai_hoa_don")
    private Integer loaiHoaDon;

    @Column(name = "phi_ship")
    private Double phiShip;

    @Column(name = "tong_tien")
    private Double tongTien;

    @Column(name = "tong_tien_khi_giam")
    private Double tongTienKhiGiam;

    @Column(name = "ghi_chu", length = 255)
    private String ghiChu;

    @Column(name = "nguoi_nhan", length = 100)
    private String nguoiNhan;

    @Column(name = "sdt_nguoi_nhan", length = 15)
    private String sdtNguoiNhan;

    @Column(name = "dia_chi_nguoi_nhan", length = 100)
    private String diaChiNguoiNhan;

    @Column(name = "email_nguoi_nhan", length = 100)
    private String emailNguoiNhan;

    @Column(name = "ngay_nhan")
    private Date ngayNhan;

    @Column(name = "ngay_mong_muon")
    private Date ngayMongMuon;

    @Column(name = "ngay_tao")
    private Date ngayTao;

    @Column(name = "ngay_sua")
    private Date ngaySua;

    @Column(name = "nguoi_tao", length = 100)
    private String nguoiTao;

    @Column(name = "nguoi_sua", length = 100)
    private String nguoiSua;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @ManyToOne
    @JoinColumn(name = "voucher_id")
    private Voucher voucher;

    @ManyToOne
    @JoinColumn(name = "tai_khoan_id")
    private TaiKhoan taiKhoan;

    @ManyToOne
    @JoinColumn(name = "phuong_thuc_thanh_toan_id")
    private PhuongThucThanhToan phuongThucThanhToan;
}
