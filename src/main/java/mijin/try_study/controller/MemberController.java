package mijin.try_study.controller;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import mijin.try_study.dto.MemberDTO;
import mijin.try_study.service.MemderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemderService memderService;

    @PostMapping("/login")
    private String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        MemberDTO loginResult = memderService.login(memberDTO);
        if (loginResult != null) {
            session.setAttribute("loginnumber", loginResult.getNumber());
            System.out.println("로그인 성공");
            return "main";
        }else {
            System.out.println("로그인 실패");
            return "login";
        }
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/logout")
    public String logoutForm() { return "index";}
}
