package com.example.bedatnsd47.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "lich_su_hoa_don")
public class LichSuHoaDon {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ngay_tao")
    private Date ngayTao;

    @Column(name = "ngay_sua")
    private Date ngaySua;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name="trangThai")
    private Integer trangThai;

    @ManyToOne
    @JoinColumn(name = "hoa_don_id")
    private HoaDon hoaDon;
}
