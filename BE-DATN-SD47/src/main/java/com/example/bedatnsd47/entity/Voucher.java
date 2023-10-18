package com.example.bedatnsd47.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "voucher")
public class Voucher {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_voucher")
    private String maVoucher;

    @Column(name = "ten_voucher")
    private String tenVoucher;

    @Column(name = "ngay_bat_dau")
    private Date ngayBatDau;

    @Column(name = "ngay_ket_thuc")
    private Date ngayKetThuc;

    @Column(name = "ngay_tao")
    private Date ngayTao;

    @Column(name = "ngay_sua")
    private Date ngaySua;

    @Column(name = "gia_tri_giam")
    private Long giaTriGiam;

    @Column(name = "gia_tri_giam_toi_da")
    private Long giaTriGiamToiDa;

    @Column(name = "hinh_thuc_giam")
    private Integer hinhThucGiam;

    @Column(name = "trang_thai")
    private Integer trangThai;

//    @OneToMany(mappedBy = "hoa_don_id")
//    private List<HoaDon> hoaDons;

}
