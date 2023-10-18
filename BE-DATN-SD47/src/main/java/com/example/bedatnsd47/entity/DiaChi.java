package com.example.bedatnsd47.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "dia_chi")
public class DiaChi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "thanh_pho", length = 50)
    private String thanhPho;

    @Column(name = "quan_huyen", length = 50)
    private String quanHuyen;

    @Column(name = "phuong_xa", length = 50)
    private String phuongXa;

    @Column(name = "dia_chi_cu_the", length = 100)
    private String diaChiCuThe;

    @Column(name = "ngay_tao")
    private Date ngayTao;

    @Column(name = "ngay_sua")
    private Date ngaySua;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @ManyToOne
    @JoinColumn(name = "tai_khoan_id")
    private TaiKhoan taiKhoan;
}
