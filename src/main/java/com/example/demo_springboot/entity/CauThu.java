package com.example.demo_springboot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CauThu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maCauThu;
    private String hoTen;
    private LocalDate ngaySinh;
    private String kinhNghiem;
    private String viTri;
    private String hinhAnh;
    private boolean gioiTinh;
//    private List<String> monHoc;

}