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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "danh_sach_yeu_thich")
public class DanhSachYeuThich {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ghi_chu", length = 255)
    private String ghiChu;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "chi_tiet_san_pham_id", referencedColumnName = "id")
    private ChiTietSanPham chiTietSanPham;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "danh_sach_yeu_thich_id", referencedColumnName = "id")
    private DanhSachYeuThich danhSachYeuThich;

}
