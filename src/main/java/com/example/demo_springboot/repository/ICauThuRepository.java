package com.example.demo_springboot.repository;

import com.example.demo_springboot.entity.CauThu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICauThuRepository extends JpaRepository<CauThu, Integer> {
//    List<CauThu>findAllByHoTenContaining(String hoTen);
}
