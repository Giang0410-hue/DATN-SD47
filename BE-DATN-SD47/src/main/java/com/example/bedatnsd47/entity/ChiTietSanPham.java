package com.example.bedatnsd47.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "chi_tiet_san_pham")
public class ChiTietSanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "so_luong")
    private Integer soLuong;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "san_pham_id")
    private SanPham sanPham;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "size_id")
    private Size size;
}
