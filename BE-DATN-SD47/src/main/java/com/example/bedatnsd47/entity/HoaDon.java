package com.example.bedatnsd47.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name="hoa_don")
public class HoaDon {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_hoa_don")
    private String maHoaDon;

    @Column(name="ngay_thanh_toan")
    private Date ngayThanhToan;

    @Column(name="loai_hoa_don")
    private Integer loaiHoaDon;

    @Column(name = "phi_ship")
    private BigDecimal phiShip;

    @Column(name = "tong_tien")
    private BigDecimal tongTien;

    @Column(name = "tong_tien_khi_giam")
    private BigDecimal tongTienKhiGiam;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "nguoi_nhan")
    private String nguoiNhan;

    @Column(name = "sdt_nguoi_nhan")
    private String sdtNguoiNhan;

    @Column(name = "ngay_ship")
    private Date ngayShip;

    @Column(name = "dia_chi_nguoi_nhan")
    private String diaChiNguoiNhan;

    @Column(name = "email_nguoi_nhan")
    private String emailNguoiNhan;

    @Column(name = "ngayNhan")
    private Date ngay_nhan;

    @Column(name = "ngay_mong_muon")
    private Date ngayMongMuon;

    @Column(name = "ngay_tao")
    private Date ngayTao;

    @Column(name = "ngay_sua")
    private Date ngaySua;

    @Column(name = "nguoi_tao")
    private String nguoiTao;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @ManyToOne
    @JoinColumn(name = "voucher_id")
    private Voucher voucher;
}

