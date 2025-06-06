package com.edu.controller;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    // Hardcoded credentials
    private static final String HARDCODED_USERNAME = "zxc";
    private static final String HARDCODED_PASSWORD = "123";

    // Show login page
    @GetMapping("/login")
    public String showLoginForm() {
        return "login.html"; // Returns login.html
    }

    // Handle login form submission
    @PostMapping("/logins")
    public String processLogin(@RequestParam String username,
                               @RequestParam String password,
                               HttpSession session,
                               Model model) {
                                System.out.println("test1");
        if (HARDCODED_USERNAME.equals(username) && HARDCODED_PASSWORD.equals(password)) {
            session.setAttribute("user", username); // Store user in session
            return "redirect:/dashboard"; // Redirect to dashboard
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login"; // Reload login page with error message
        }
    }

    // Dashboard Page (After Successful Login)
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        String user = (String) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login"; // Redirect if not logged in
        }
        model.addAttribute("username", user);
        return "index.html"; // Returns dashboard.html
    }

    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Clear session
        return "redirect:/login"; // Redirect to login page
    }
}
