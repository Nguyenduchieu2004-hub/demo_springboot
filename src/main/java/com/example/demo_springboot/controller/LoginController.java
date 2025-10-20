package com.example.demo_springboot.controller;

import com.example.demo_springboot.entity.User;
import com.example.demo_springboot.repository.IUserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("user")
public class LoginController {
    @Autowired
    private IUserRepository userRepository;


    @ModelAttribute("user")
    public User setupUser() {
        return new User();
    }

    @GetMapping("/login")
    public String showForm(@CookieValue(value = "rememberEmail", defaultValue = "") String rememberedEmail,
                           Model model) {
        Cookie cookie = new Cookie("rememberEmail", rememberedEmail);
        model.addAttribute("emailRemembered", cookie.getValue());
        return "views/login";
    }

    @PostMapping("/doLogin")
    public String doLogin(@ModelAttribute("user") User user,
                          Model model,
                          HttpServletResponse response) {
        User foundUser = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());

        if (foundUser != null) {
            // ✅ Nếu tìm thấy, lưu cookie rememberEmail
            Cookie cookie = new Cookie("rememberEmail", foundUser.getEmail());
            cookie.setMaxAge(24 * 60 * 60); // 1 ngày
            response.addCookie(cookie);

            model.addAttribute("message", "✅ Đăng nhập thành công!");
            model.addAttribute("user", foundUser);
        } else {
            // ❌ Sai tài khoản hoặc mật khẩu
            model.addAttribute("message", "❌ Sai email hoặc mật khẩu. Vui lòng thử lại.");
            user.setEmail("");
        }

        return "views/login";
    }
}