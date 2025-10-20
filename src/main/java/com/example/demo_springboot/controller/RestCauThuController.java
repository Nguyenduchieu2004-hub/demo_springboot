package com.example.demo_springboot.controller;

import com.example.demo_springboot.Validation.CauThuValidate;
import com.example.demo_springboot.dto.CauThuDTO;
import com.example.demo_springboot.entity.CauThu;
import com.example.demo_springboot.service.ICauThuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/api/cauthus")
public class RestCauThuController {

    @Autowired
    private ICauThuService cauThuService;

    @Autowired
    private CauThuValidate cauThuValidate;

    // ✅ 1. Lấy danh sách cầu thủ (phân trang)
    @GetMapping("")
    public ResponseEntity<Page<CauThu>> showList(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page) {

        Pageable pageable = PageRequest.of(page, 3);
        Page<CauThu> cauThuPage = cauThuService.findAll( pageable);
        if (cauThuPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cauThuPage, HttpStatus.OK);
    }

    // ✅ 2. Trả về cầu thủ theo id
    @GetMapping("/{id}")
    public ResponseEntity<CauThu> detail(@PathVariable(name = "id") int id) {
        CauThu cauThu = cauThuService.findById(id);
        if (cauThu == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cauThu, HttpStatus.OK);
    }

    // ✅ 3. Thêm mới cầu thủ
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody CauThuDTO cauThuDTO, BindingResult bindingResult) {
        cauThuValidate.validate(cauThuDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            // Trả về danh sách lỗi cụ thể
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        CauThu cauThu = new CauThu();
        BeanUtils.copyProperties(cauThuDTO, cauThu);
        cauThuService.addCauThu(cauThu);
        return new ResponseEntity<>(cauThu, HttpStatus.CREATED);
    }

    // ✅ 4. Xoá cầu thủ theo id
    @DeleteMapping("/{id}")
    public ResponseEntity<CauThu> delete(@PathVariable(name = "id") int id) {
        CauThu cauThu = cauThuService.findById(id);
        if (cauThu == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        cauThuService.deleteCauThu(id);
        return new ResponseEntity<>(cauThu, HttpStatus.OK);
    }

    // ✅ 5. Sửa thông tin cầu thủ
    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody CauThuDTO cauThuDTO) {
        CauThu cauThu = cauThuService.findById(id);
        if (cauThu == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        BeanUtils.copyProperties(cauThuDTO, cauThu);
        cauThuService.updateCauThu(cauThu);
        return new ResponseEntity<>(cauThu, HttpStatus.OK);
    }
}
