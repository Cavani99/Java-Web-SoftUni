package main.web;

import main.model.User;
import main.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {

    private UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView renderUsers() {
        ModelAndView modelAndView = new ModelAndView();

        List<User> users = userService.getAllUsers();


        modelAndView.addObject("users", users);
        modelAndView.setViewName("users.html");

        return modelAndView;
    }
}
