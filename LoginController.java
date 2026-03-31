package ro.example.catalogcarti.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginForm() {
        return "login";   // afiseaza pagina login.html
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, 
                        @RequestParam String password,
                        HttpSession session, Model model) {
        
        if ("admin".equals(username) && "admin".equals(password)) {
            session.setAttribute("role", "ADMIN");
            return "redirect:/catalog";
        }
        if ("bibliotecar".equals(username) && "biblio".equals(password)) {
            session.setAttribute("role", "BIBLIOTECAR");
            return "redirect:/catalog";
        }
        
        model.addAttribute("error", "Date incorecte!");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}