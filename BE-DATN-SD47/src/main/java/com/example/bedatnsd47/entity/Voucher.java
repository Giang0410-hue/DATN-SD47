package com.example.bedatnsd47.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "voucher")
@Builder
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_voucher")
    @NotBlank(message = "Không được trống mã")
    private String maVoucher;

    @Column(name = "ten_voucher")
    @NotBlank(message = "Không được trống tên")
    private String tenVoucher;

    @Column(name = "ngay_bat_dau")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Ngày bắt đầu không được trống")
    @FutureOrPresent(message = "Phải là một ngày trong hiện tại hoặc tương lại")
    private Date ngayBatDau;

    @Column(name = "ngay_ket_thuc")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Ngày kết thúc không được trống")
    @FutureOrPresent(message = "Phải là một ngày trong hiện tại hoặc tương lại")
    private Date ngayKetThuc;


    @Column(name = "ngay_tao")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayTao;

    @Column(name = "ngay_sua")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngaySua;

    @Column(name = "so_luong")
    @NotNull(message = "Số lượng không để trống")
    @Min(value = 1,message = "Số lượng  không nhỏ hơn 1")
    @Max(value = 100,message = "Số lượng  không lớn hơn 100")
    private Long soLuong;

    @Column(name = "phan_tram_giam")
    @NotNull(message = " Phần trăm giảm không để trống")
    @Min(value = 1,message = "Phần trăm giảm  không nhỏ hơn 1")
    @Max(value = 100,message = "Phần trăm giảm  không lớn hơn 100")
    private Long phanTramGiam;

    @Column(name = "gia_tri_don_toi_thieu")
    @NotNull(message = "Giá trị tối thiểu không để trống")
    @Min(value = 1,message = "Giá trị tối thiểu không nhỏ hơn 1")
    @Max(value = 100000000,message = "Giá trị tối thiểu không lớn hơn 1000000000")
    private Long giaTriDonToiThieu;

    @Column(name = "trang_thai")
    @NotNull(message = "Không được để trống trạng thái")
    private Integer trangThai;
}
