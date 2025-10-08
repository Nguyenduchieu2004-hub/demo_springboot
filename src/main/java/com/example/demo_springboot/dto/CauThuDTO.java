package com.example.demo_springboot.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CauThuDTO {

    private int maCauThu;
    @NotEmpty(message = "Họ Tên không được để trống")
    @Size(min = 3, max = 50, message = "Họ tên phải có độ dài từ 3 đến 50 ký tự")
    @Pattern(regexp = "^[A-ZÀ-Ỹ][a-zà-ỹA-ZÀ-Ỹ\\s]*$", message = "Họ tên phải viết hoa chữ đầu và không chứa ký tự đặc biệt")
    private String hoTen;

    @NotNull(message = "Ngày sinh không được để trống")
    private LocalDate ngaySinh;

    @NotEmpty(message = "Kinh nghiệm không được để trống")
    private String kinhNghiem;

    @NotEmpty(message = "Vị trí không được để trống")
    private String viTri;

    @NotEmpty(message = "Hình ảnh không được để trống")
    private String hinhAnh;

    @NotNull(message = "Giới tính không được để trống")
    private Boolean gioiTinh;
}
