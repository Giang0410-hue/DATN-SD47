package com.example.bedatnsd47.dto.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class SanPhamChiTietRequest {

    private List<String> listIdSanPham;

    private List<String> listIdSoLuong;

    private List<String> listIdGiaHienHanh;

    private List<String> listIdLoaiDe;

    private List<String> listIdKichCo;

    private List<String> listIdMauSac;

}
