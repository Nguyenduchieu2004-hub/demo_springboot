package com.example.demo_springboot.controller;

import com.example.demo_springboot.entity.CauThu;
import com.example.demo_springboot.service.ICauThuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView showList() {
        ModelAndView modelAndView = new ModelAndView("/cauthu/list");
        modelAndView.addObject("cauthuList", cauThuService.findAll());
        return modelAndView;
    }

    @GetMapping("/add")
    public String showFormAdd(Model model) {
        model.addAttribute("cauThu", new CauThu());
        return "cauthu/add";
    }

    @PostMapping("/add")
    public String save(@ModelAttribute("cauThu") CauThu cauThu,
                             RedirectAttributes redirectAttributes) {
        cauThuService.addCauThu(cauThu);
        redirectAttributes.addFlashAttribute("mess", "Thêm cầu thủ thành công!");
        return "redirect:/Cauthu";
    }
//    @GetMapping(value = "/search")
//    public ModelAndView search(@RequestParam(name = "searchName") String searchName) {
//        ModelAndView modelAndView = new ModelAndView("/cauthu/list");
//        modelAndView.addObject("cauthuList", cauThuService.searchByName(searchName));
//        return modelAndView;
//    }

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

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int maCauThu,
                         RedirectAttributes redirectAttributes) {
        cauThuService.deleteCauThu(maCauThu);
        redirectAttributes.addFlashAttribute("mess", "Xóa cầu thủ thành công!");
        return "redirect:/Cauthu";
    }

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
