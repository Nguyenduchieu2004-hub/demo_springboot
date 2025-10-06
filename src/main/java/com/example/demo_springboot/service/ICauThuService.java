package com.example.demo_springboot.service;

import com.example.demo_springboot.entity.CauThu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICauThuService {
    List<CauThu> findAll();
    CauThu findById(int id);
    Boolean addCauThu(CauThu cauThu);
    void deleteCauThu(int id);
    void updateCauThu(CauThu cauThu);
    Page<CauThu> findAll(String hoTen, Pageable pageable);
    List<CauThu> searchByName(String hoTen);
}
