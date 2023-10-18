package com.example.bedatnsd47.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "san_pham")
public class SanPham {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma", length = 50, nullable = false)
    private String ma;

    @Column(name = "ten", length = 255)
    private String ten;

    @Column(name = "mo_ta", columnDefinition = "NVARCHAR(MAX)")
    private String moTa;

    @Column(name = "gia_hien_hanh")
    private Double giaHienHanh;

    @Column(name = "ngay_tao")
    private java.sql.Timestamp ngayTao;

    @Column(name = "ngay_sua")
    private java.sql.Timestamp ngaySua;

    @Column(name = "nguoi_tao", length = 100)
    private String nguoiTao;

    @Column(name = "nguoi_sua", length = 100)
    private String nguoiSua;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "mau_sac_id")
    private MauSac mauSac;


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "loai_de_id")
    private LoaiDe loaiDe;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "thuong_hieu_id")
    private ThuongHieu thuongHieu;

    //    @ManyToOne
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "nha_san_xuat_id")
    private NhaSanXuat nhaSanXuat;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "hinh_anh_san_pham_id")
    private HinhAnhSanPham hinhAnhSanPham;

}
