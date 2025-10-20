package com.example.demo_springboot.service;

import com.example.demo_springboot.entity.CauThu;
import com.example.demo_springboot.repository.ICauThuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CauThuService implements ICauThuService {
    @Autowired
    private ICauThuRepository cauThuRepository;
    @Override
    public Page<CauThu> findAll(Pageable pageable) {
        return cauThuRepository.findAll(pageable);
    }

    @Override
    public CauThu findById(int id) {
        return cauThuRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean addCauThu(CauThu cauThu) {

        return cauThuRepository.save(cauThu)!=null;
    }

    @Override
    public void deleteCauThu(int id) {

        cauThuRepository.deleteById(id);
    }

    @Override
    public void updateCauThu(CauThu cauThu) {
        cauThuRepository.save(cauThu);
    }

    @Override
    public Page<CauThu> findAll(String hoTen, Pageable pageable) {
        return cauThuRepository.findAllByHoTenContaining(hoTen,pageable);
    }

    @Override
    public List<CauThu> searchByName(String hoTen) {
        return cauThuRepository.findAllByHoTenContaining(hoTen);
    }
}
