package main.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import main.model.Player;
import main.model.PlayerClass;
import main.model.PlayerRole;
import main.service.PlayerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@RequestMapping("/roles")
public class RolesController {
    private PlayerService playerService;

    public RolesController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public ModelAndView rolesSelect() {
        return new ModelAndView("role-select.html");
    }

    @PostMapping()
    public ModelAndView pickRole(
            @RequestParam("role") String role, HttpServletRequest request, HttpSession session) {

        UUID userId = (UUID) request.getSession(false).getAttribute("user_id");
        Player user = playerService.getUser(userId);

        if (user == null) {
            return new ModelAndView("redirect:/roles");
        }

        if (role.equals("adventurer")) {
            user.setRole(PlayerRole.ADVENTURER);

            user.setPlayerClass(PlayerClass.random());
        } else {
            user.setRole(PlayerRole.QUEST_MASTER);
        }
        Player player = playerService.savePlayer(user);

        session.setAttribute("user_role", player.getRole());

        return new ModelAndView("redirect:/home");
    }
}
