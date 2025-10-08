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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/Cauthu") // ánh xạ đường dẫn
public class CauThuController {

    @ModelAttribute("monHoc")
    public List<String> getSubjects() {
        return Arrays.asList("Chiến thuật", "Thể lực", "Kỹ năng phòng ngự & tấn công");
    }
    @Autowired
    private ICauThuService cauThuService;

    // hiển thị danh sách
//    @RequestMapping(value = "", method = RequestMethod.GET)
//    public ModelAndView showList() {
//        ModelAndView modelAndView = new ModelAndView("/cauthu/list");
//        modelAndView.addObject("cauthuList", cauThuService.findAll());
//        return modelAndView;
//    }

//    @GetMapping(value = "")
//    public ModelAndView showList(@PageableDefault(page = 0,size = 3,sort = "name",direction = Sort.Direction.DESC) Pageable pageable,
//                                 @RequestParam(name = "searchName", required = false,defaultValue = "")String searchName){
//        ModelAndView modelAndView = new ModelAndView("/student/list");
//        Page<Student> studentPage = studentService.findAll(searchName,pageable);
//        modelAndView.addObject("studentPage", studentPage);
//        modelAndView.addObject("searchName", searchName);
//        return modelAndView;
//    }

    @GetMapping(value = "")
    public ModelAndView showList(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "searchName", required = false, defaultValue = "") String searchName) {

        Pageable pageable = PageRequest.of(page, 3, Sort.by("hoTen").descending().and(Sort.by("gioiTinh").ascending()));

        ModelAndView modelAndView = new ModelAndView("/cauthu/list");
        Page<CauThu> cauThuPage = cauThuService.findAll(searchName, pageable);
        modelAndView.addObject("cauThuPage", cauThuPage);
        modelAndView.addObject("searchName", searchName);
        return modelAndView;
    }

    //thêm mới
    @GetMapping("/add")
    public String showFormAdd(Model model) {
        model.addAttribute("cauThu", new CauThuDTO());
//        model.addAttribute("cauThu", new CauThu());
        return "cauthu/add";
    }

    @PostMapping("/add")
    public String save(@Validated @ModelAttribute("cauThu") CauThuDTO cauThuDTO,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes,
                       Model model) {

        if (bindingResult.hasErrors()) {
            return "cauthu/add"; // giữ nguyên dữ liệu và hiển thị lỗi
        }

        CauThu cauThu = new CauThu();
        BeanUtils.copyProperties(cauThuDTO, cauThu);
        cauThuService.addCauThu(cauThu);
        redirectAttributes.addFlashAttribute("mess", "Thêm cầu thủ thành công!");
        return "redirect:/Cauthu";
    }

    //tìm kiếm
    @GetMapping(value = "/search")
    public ModelAndView search(@RequestParam(name = "searchName") String searchName) {
        ModelAndView modelAndView = new ModelAndView("/cauthu/list");
        modelAndView.addObject("cauthuList", cauThuService.searchByName(searchName));
        return modelAndView;
    }
    //chi tiết
    @GetMapping("/detail")
    public String detail(@RequestParam(name = "id", required = false, defaultValue = "1") int id,
                         Model model) {
        CauThu cauThu = cauThuService.findById(id);
        model.addAttribute("cauThu", cauThu);
        return "cauthu/detail"; // trả về file detail.jsp
    }

    @GetMapping("/detail/{id:[0-9]+}")
    public String detail2(@PathVariable(name = "id") int id,
                          Model model) {
        CauThu cauThu = cauThuService.findById(id);
        model.addAttribute("cauThu", cauThu);
        return "cauthu/detail";
    }
    //xóa
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int maCauThu,
                         RedirectAttributes redirectAttributes) {
        cauThuService.deleteCauThu(maCauThu);
        redirectAttributes.addFlashAttribute("mess", "Xóa cầu thủ thành công!");
        return "redirect:/Cauthu";
    }
    //sửa
    @GetMapping("/edit/{id}")
    public String showFormEdit(@PathVariable("id") int id, Model model) {
        CauThu cauThu = cauThuService.findById(id);
        if (cauThu != null) {
            model.addAttribute("cauThu", cauThu);
            return "cauthu/edit";
        }
        return "redirect:/Cauthu";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute("cauThu") CauThu cauThu,
                               RedirectAttributes redirectAttributes) {
        cauThuService.updateCauThu(cauThu);
        redirectAttributes.addFlashAttribute("mess", "Cập nhật cầu thủ thành công!");
        return "redirect:/Cauthu";
    }
}
