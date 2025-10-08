package main.web;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/letters")
public class LettersController {

    public LettersController() {
    }

    @GetMapping
    public ModelAndView getLetters(@CookieValue(value = "favouriteLetter", defaultValue = "?") String favouriteLetter) {
        ModelAndView modelAndView = new ModelAndView("letters.html");
        modelAndView.addObject("favouriteLetter", favouriteLetter);

        return modelAndView;
    }

    @PostMapping
    public ModelAndView setLetter(HttpServletResponse response, @RequestParam("my_letter") String pickedLetter) {
        Cookie cookie = new Cookie("favouriteLetter", pickedLetter);
        cookie.setMaxAge(3600);
        cookie.setPath("/");

        response.addCookie(cookie);


        return new ModelAndView("redirect:/letters");
    }
}
