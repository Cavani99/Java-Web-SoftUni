package main.web;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import main.model.Player;
import main.service.PlayerService;
import main.web.dto.LoginRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class LoginController {

    private PlayerService playerService;

    public LoginController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public ModelAndView getLogin() {
        ModelAndView modelAndView = new ModelAndView("login.html");

        modelAndView.addObject("player", new Player());

        return modelAndView;
    }

    @PostMapping
    public ModelAndView login(@Valid @ModelAttribute("player") LoginRequest player, BindingResult result, HttpSession session) {

        if (result.hasErrors()) {
            ModelAndView mav = new ModelAndView("login");
            mav.addObject("player", player);
            return mav;
        }

        Player player1 = playerService.login(new LoginRequest(
                player.getUsername(),
                player.getPassword()
        ));

        if (player1 != null) {
            session.setAttribute("user_id", player1.getId());
            session.setAttribute("user_role", player1.getRole());

            return new ModelAndView("redirect:/roles");
        }

        ModelAndView mav = new ModelAndView("login");
        mav.addObject("player", player);
        return mav;
    }
}
