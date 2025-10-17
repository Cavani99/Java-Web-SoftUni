package main.web;

import jakarta.validation.Valid;
import main.model.Player;
import main.service.PlayerService;
import main.web.dto.RegisterRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private PlayerService playerService;

    public RegisterController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public ModelAndView getRegister() {
        ModelAndView modelAndView = new ModelAndView("register.html");

        modelAndView.addObject("player", new Player());

        return modelAndView;
    }

    @PostMapping
    public ModelAndView register(@Valid @ModelAttribute("player") RegisterRequest player,
                                 BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView mav = new ModelAndView("register");
            mav.addObject("player", player);
            return mav;
        }

        Player player1 = playerService.register(new RegisterRequest(
                player.getUsername(),
                player.getPassword(),
                player.getNickname()
        ));

        return new ModelAndView("redirect:/login");
    }
}
