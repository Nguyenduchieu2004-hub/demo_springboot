package com.example.demo_springboot.Validation;

import com.example.demo_springboot.dto.CauThuDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CauThuValidate implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        CauThuDTO cauThuDTO = (CauThuDTO) target;

        // Kiểm tra họ tên
        if (cauThuDTO.getHoTen() == null || cauThuDTO.getHoTen().trim().isEmpty()) {
            errors.rejectValue("hoTen", "notEmpty", "Họ tên không được để trống");
        } else if (!cauThuDTO.getHoTen().matches("^[A-Z][a-zA-Z\\s]*$")) {
            errors.rejectValue("hoTen", "name.pattern", "Họ tên phải viết hoa chữ đầu");
        }

    }
}