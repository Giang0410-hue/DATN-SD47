package com.example.bedatnsd47.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "hoa_don_chi_tiet")
public class HoaDonChiTiet {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "so_luong")
    private Integer soLuong;

    @Column(name = "don_gia")
    private BigDecimal donGia;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "ngay_tao")
    private Date ngayTao;

    @Column(name = "ngay_sua")
    private Date ngaySua;

    @Column(name = "nguoi_tao")
    private String nguoiTao;

    @Column(name = "nguoi_sua")
    private String nguoiSua;

    @ManyToOne
    @JoinColumn(name = "hoa_don_id")
    com.example.bedatnsd47.entity.HoaDon hoaDon;

    @ManyToOne
    @JoinColumn(name = "chi_tiet_san_pham_id")
    private com.example.bedatnsd47.entity.ChiTietSanPham chiTietSanPham;



}
