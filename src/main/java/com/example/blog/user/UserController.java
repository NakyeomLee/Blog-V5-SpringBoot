package com.example.blog.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.http.HttpRequest;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    // IOC 컨테이너에서 세션 꺼내옴 (세션은 싱글턴이라서 하나만 있으니까 IOC가 관리해줌)
    private final HttpSession session;

    @GetMapping("/login-form")
    public String loginForm() {
        return "user/login-form"; // view
    }

    // 로그인은 post 요청!!
    @PostMapping("/login")
    public String login(UserRequest.LoginDTO loginDTO) {
        User sessionUser = userService.로그인(loginDTO);
        session.setAttribute("sessionUser", sessionUser);
        return "redirect:/";
    }
}