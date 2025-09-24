package main.web;

import jakarta.validation.Valid;
import main.model.User;
import main.repository.UserRepository;
import main.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {

    private UserService userService;
    private UserRepository userRepository;

    public UsersController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public ModelAndView renderUsers() {
        ModelAndView modelAndView = new ModelAndView();

        List<User> users = userService.getAllUsers();

        modelAndView.addObject("users", users);
        modelAndView.setViewName("users.html");

        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView userDetails(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView();

        User user = userService.getUserById(id);

        modelAndView.addObject("user", user);
        modelAndView.setViewName("user-details.html");

        return modelAndView;
    }

    @GetMapping("/new-user")
    public ModelAndView newUserView() {
        ModelAndView modelAndView = new ModelAndView("new-user.html");

        modelAndView.addObject("user", new User());

        return modelAndView;
    }

    @PostMapping("/new-user")
    public ModelAndView registerUser(@Valid @ModelAttribute("user") User user,
                                     BindingResult result) {
        if (user.getUsername() == null || user.getUsername().isBlank()) {
            result.rejectValue("username", "error.username", "Username is required!");
        } else if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            result.rejectValue("username", "error.username", "Username already exists!");
        }

        if (user.getFirstName() == null || user.getFirstName().isBlank()) {
            result.rejectValue("firstName", "error.firstName", "First name is required!");
        }
        if (user.getCountry() == null || user.getCountry().isBlank()) {
            result.rejectValue("country", "error.country", "Country is required!");
        }
        if (user.getBalance() == null) {
            result.rejectValue("balance", "error.balance", "Balance is required!");
        }
        if (user.getPhoneNumber() == null || user.getPhoneNumber().isBlank()) {
            result.rejectValue("phoneNumber", "error.phoneNumber", "Phone number is required!");
        }
        if (user.getAddress() == null || user.getAddress().isBlank()) {
            result.rejectValue("address", "error.address", "Address is required!");
        }
        if (user.getProfilePictureUrl() == null || user.getProfilePictureUrl().isBlank()) {
            result.rejectValue("profilePictureUrl", "error.profilePictureUrl", "Profile picture URL is required!");
        }

        if (result.hasErrors()) {
            return new ModelAndView("new-user.html").addObject("user", user);
        }

        userRepository.save(user);

        return new ModelAndView("redirect:/users");
    }

}
