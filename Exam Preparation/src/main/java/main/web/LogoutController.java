package main.web;

import jakarta.servlet.http.HttpSession;
import main.service.PlayerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/logout")
public class LogoutController {

    private PlayerService playerService;

    public LogoutController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public ModelAndView logout(HttpSession session) {
        session.setAttribute("user_id", null);
        session.setAttribute("user_role", null);

        return new ModelAndView("redirect:/");
    }
}
