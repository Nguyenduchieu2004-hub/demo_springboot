package com.example.demo_springboot.Validation;

import com.example.demo_springboot.dto.CauThuDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CauThuValidate implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return CauThuDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CauThuDTO cauThuDTO = (CauThuDTO) target;

        // 🔹 Họ tên
        if (cauThuDTO.getHoTen() == null || cauThuDTO.getHoTen().trim().isEmpty()) {
            errors.rejectValue("hoTen", "notEmpty", "Họ tên không được để trống");
        } else if (!cauThuDTO.getHoTen().matches("^[A-ZÀ-Ỹ][a-zà-ỹA-ZÀ-Ỹ\\s]*$")) {
            errors.rejectValue("hoTen", "pattern", "Họ tên phải viết hoa chữ cái đầu và không chứa ký tự đặc biệt");
        }

        // 🔹 Ngày sinh
        if (cauThuDTO.getNgaySinh() == null) {
            errors.rejectValue("ngaySinh", "notNull", "Ngày sinh không được để trống");
        }

        // 🔹 Kinh nghiệm
        if (cauThuDTO.getKinhNghiem() == null || cauThuDTO.getKinhNghiem().trim().isEmpty()) {
            errors.rejectValue("kinhNghiem", "notEmpty", "Kinh nghiệm không được để trống");
        }

        // 🔹 Vị trí
        if (cauThuDTO.getViTri() == null || cauThuDTO.getViTri().trim().isEmpty()) {
            errors.rejectValue("viTri", "notEmpty", "Vị trí không được để trống");
        }

        // 🔹 Hình ảnh
        if (cauThuDTO.getHinhAnh() == null || cauThuDTO.getHinhAnh().trim().isEmpty()) {
            errors.rejectValue("hinhAnh", "notEmpty", "Hình ảnh không được để trống");
        }

        // 🔹 Giới tính
        if (cauThuDTO.getGioiTinh() == null) {
            errors.rejectValue("gioiTinh", "notNull", "Giới tính không được để trống");
        }
    }
}
