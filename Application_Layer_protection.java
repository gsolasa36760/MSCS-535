// Example: SecureController.java
package com.example.securitydemo;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.owasp.encoder.Encode;

import javax.servlet.http.HttpSession;
import java.sql.*;

@Controller
public class SecureController {

    // SQL Injection Protection: Use PreparedStatement
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        if (!isValidInput(username) || !isValidInput(password)) {
            model.addAttribute("error", "Invalid input detected.");
            return "login";
        }

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "user", "pass")) {
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password); // In real apps, use hashed passwords!
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                session.setAttribute("user", username);
                return "home";
            } else {
                model.addAttribute("error", "Login failed.");
                return "login";
            }
        } catch (SQLException e) {
            model.addAttribute("error", "Database error.");
            return "login";
        }
    }

    // XSS Protection: Encode output
    @GetMapping("/profile")
    public String profile(HttpSession session, Model model) {
        String username = (String) session.getAttribute("user");
        if (username != null) {
            // Encode output to prevent XSS
            model.addAttribute("username", Encode.forHtml(username));
            return "profile";
        }
        return "redirect:/login";
    }

    // CSRF Protection: Spring Security auto-injects CSRF tokens
    @GetMapping("/form")
    public String form(CsrfToken token, Model model) {
        model.addAttribute("_csrf", token.getToken());
        return "form";
    }

    // Input Validation Example
    private boolean isValidInput(String input) {
        return input != null && input.matches("^[a-zA-Z0-9_@.-]{3,30}$");
    }
}