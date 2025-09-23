package main.web;

import main.model.User;
import main.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/users")
public class PostUsersController {

    private UserService userService;

    public PostUsersController(UserService userService) {
        this.userService = userService;
    }


}
