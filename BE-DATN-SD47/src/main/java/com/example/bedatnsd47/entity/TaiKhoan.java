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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tai_khoan")

public class TaiKhoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ho_va_ten", length = 100)
    @NotBlank(message = "Họ và tên không được trống")
    private String hoVaTen;

    @Column(name = "ngay_sinh")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngaySinh;

    @Column(name = "gioi_tinh")
    private Integer gioiTinh;

    @Column(name = "so_dien_thoai", length = 15)
    @NotBlank(message = "Số điện thoai không được trống")
    @Pattern(regexp = "^0\\d{9,10}", message = "SĐT phải là số và bắt đầu bằng 0 và có 10-11 số")
    private String soDienThoai;

    @Column(name = "email", length = 255)
    @NotBlank(message = "Email không được trống")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Email không hợp lệ")
    private String email;

    @Column(name = "thanh_pho", length = 50)
    @NotBlank(message = "Thành phố không được trống")
    private String thanhPho;

    @Column(name = "quan_huyen", length = 50)
    @NotBlank(message = "Quận huyện không được trống")
    private String quanHuyen;

    @Column(name = "phuong_xa", length = 50)
    @NotBlank(message = "Phường xã không được trống")
    private String phuongXa;

    @Column(name = "dia_chi_cu_the", length = 100)
    @NotBlank(message = "Địa chỉ cụ thể không được trống")
    private String diaChiCuThe;

    @Column(name = "ten_tai_khoan", length = 100)
    @NotBlank(message = "Tên tài khoản không được trống")
    private String ten_tai_khoan;

    @Column(name = "mat_khau", length = 30)
    @NotBlank(message = "Mật khẩu không được trống")
    private String matKhau;

    @Column(name = "ngay_tao")
//    @NotNull(message = "Ngày tạo không được trống")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayTao;

    @Column(name = "ngay_sua")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngaySua;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vai_tro_id", referencedColumnName = "id")
    private VaiTro vaiTro;

}
