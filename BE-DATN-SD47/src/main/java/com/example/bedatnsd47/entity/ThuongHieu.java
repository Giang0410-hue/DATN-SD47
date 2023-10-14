package com.example.bedatnsd47.entity;

import com.example.bedatnsd47.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "thuong_hieu")
@Getter
@Setter
@AllArgsConstructor
public class ThuongHieu extends BaseEntity<ThuongHieu> {
    public ThuongHieu(Long id, String ten, Date ngayTao, Date ngaySua, int trangThai) {
        super(id, ten, ngayTao, ngaySua, trangThai);
    }
}
