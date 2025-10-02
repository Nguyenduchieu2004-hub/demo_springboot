package com.example.demo_springboot.service;

import com.example.demo_springboot.entity.CauThu;

import java.util.List;

public interface ICauThuService {
    List<CauThu> findAll();
    CauThu findById(int id);
    Boolean addCauThu(CauThu cauThu);
    void deleteCauThu(int id);
    void updateCauThu(CauThu cauThu);
//    List<CauThu> searchByName(String hoTen);
}
