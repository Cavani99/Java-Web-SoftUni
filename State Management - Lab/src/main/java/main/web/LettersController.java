package main.web;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/letters")
public class LettersController {

    public LettersController() {
    }

    @GetMapping
    public ModelAndView getLetters(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("letters.html");

        String favouriteLetter = "?";

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("favouriteLetter".equals(cookie.getName())) {
                    favouriteLetter = cookie.getValue();
                    break;
                }
            }
        }

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
