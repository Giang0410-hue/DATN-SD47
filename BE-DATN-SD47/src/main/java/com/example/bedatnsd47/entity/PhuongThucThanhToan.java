package com.example.bedatnsd47.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "phuong_thuc_thanh_toan")
public class PhuongThucThanhToan {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_phuong_thuc)")
    private String maPhuongThuc;

    @Column(name = "ten")
    private String ten;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "trang_thai")
    private Integer trangThai;

//    @OneToMany(mappedBy = "hoa_don_id")
//    private List<HoaDon> hoaDons;

}
