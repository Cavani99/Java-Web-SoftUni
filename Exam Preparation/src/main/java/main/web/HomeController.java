package main.web;

import main.service.PlayerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home")
public class HomeController {

    private PlayerService playerService;

    public HomeController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public ModelAndView rolesSelect() {
        return new ModelAndView("items.html");
    }
}
