package com.example.bedatnsd47.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tai_khoan")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TaiKhoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ho_va_ten", length = 100, unique = true, nullable = false)
    private String hoVaTen;


    @Column(name = "ngay_sinh")
    private Date ngaySinh;

    @Column(name = "gioi_tinh")
    private boolean gioiTinh;

    @Column(name = "so_dien_thoai", length = 15)
    private String soDienThoai;

    @Column(name = "email", length = 255,unique = true, nullable = false)
    private String email;

    @Column(name = "thanh_pho", length = 50)
    private String thanhPho;

    @Column(name = "quan_huyen", length = 50)
    private String quanHuyen;

    @Column(name = "phuong_xa", length = 50)
    private String phuongXa;

    @Column(name = "dia_chi_cu_the", length = 100)
    private String diaChiCuThe;

    @Column(name = "anh_dai_dien", length = 255)
    private String anhDaiDien;

    @Column(name = "mat_khau", length = 30, unique = true, nullable = false)
    @JsonIgnore
    private String matKhau;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "ngay_tao")
    private Date ngayTao;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "ngay_sua")
    private Date ngaySua;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "vai_tro_id")
    @JoinTable(name = "taikhoan_vaitro",joinColumns = @JoinColumn(name = "id_taikhoan"),
    inverseJoinColumns =  @JoinColumn(name = "id_vaitro")
    )
    private Set<VaiTro> vaiTro = new HashSet<>();
}
