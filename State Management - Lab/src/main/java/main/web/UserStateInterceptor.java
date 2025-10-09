package main.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalDateTime;


@Component
public class UserStateInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getRequestURI();

        if (path.startsWith("/custom-error")) {
            return true;
        }

        LocalDateTime currentTime = LocalDateTime.now();
        int hour = currentTime.getHour();

        if (hour < 8 || hour > 18) {
            request.setAttribute("interceptorError", "Access not allowed outside working hours.");
            request.getRequestDispatcher("/custom-error").forward(request, response);
            return false;
        }

        return true;
    }
}
