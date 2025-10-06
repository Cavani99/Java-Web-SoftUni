package main.web;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HTTPController {

    public HTTPController() {

    }

    @GetMapping
    public ModelAndView getHomepage(HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView("response.html");

        response.setHeader("X-Client-Type", "default");
        String header = response.getHeader("X-Client-Type");

        String value = "Unknown client. Please specify 'X-Client-Type' header.";
        if (header != null) {
            value = switch (header) {
                case "web" -> "Welcome, web user!";
                case "mobile" -> "Hello from the mobile interface.";
                case "admin-panel" -> "Admin access granted.";
                default -> "Unknown client. Please specify 'X-Client-Type' header.";
            };
        }

        modelAndView.addObject("header", value);

        return modelAndView;
    }

    @GetMapping("/web")
    public ModelAndView getWeb(HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView("response.html");

        response.setHeader("X-Client-Type", "web");
        String header = response.getHeader("X-Client-Type");

        String value = "Unknown client. Please specify 'X-Client-Type' header.";
        if (header != null) {
            value = switch (header) {
                case "web" -> "Welcome, web user!";
                case "mobile" -> "Hello from the mobile interface.";
                case "admin-panel" -> "Admin access granted.";
                default -> "Unknown client. Please specify 'X-Client-Type' header.";
            };
        }

        modelAndView.addObject("header", value);

        return modelAndView;
    }

    @GetMapping("/mobile")
    public ModelAndView setMobile(HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView("response.html");

        response.setHeader("X-Client-Type", "mobile");
        String header = response.getHeader("X-Client-Type");

        String value = "Unknown client. Please specify 'X-Client-Type' header.";
        if (header != null) {
            value = switch (header) {
                case "web" -> "Welcome, web user!";
                case "mobile" -> "Hello from the mobile interface.";
                case "admin-panel" -> "Admin access granted.";
                default -> "Unknown client. Please specify 'X-Client-Type' header.";
            };
        }

        modelAndView.addObject("header", value);

        return modelAndView;
    }

    @GetMapping("/admin_panel")
    public ModelAndView setAdminPanel(HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView("response.html");

        response.setHeader("X-Client-Type", "admin-panel");
        String header = response.getHeader("X-Client-Type");

        String value = "Unknown client. Please specify 'X-Client-Type' header.";
        if (header != null) {
            value = switch (header) {
                case "web" -> "Welcome, web user!";
                case "mobile" -> "Hello from the mobile interface.";
                case "admin-panel" -> "Admin access granted.";
                default -> "Unknown client. Please specify 'X-Client-Type' header.";
            };
        }

        modelAndView.addObject("header", value);

        return modelAndView;
    }
}
