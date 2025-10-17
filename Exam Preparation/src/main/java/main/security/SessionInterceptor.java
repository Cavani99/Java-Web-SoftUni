package main.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Set;

@Component
public class SessionInterceptor implements HandlerInterceptor {

    public static final Set<String> UNAUTHENTICATED_ENDPOINTS = Set.of("/login", "/register", "/", "/logout", "/error");
    public static final String ROLE_PICK_ENDPOINT = "/roles";
    public static final String USER_ID_FROM_SESSION = "user_id";
    public static final String USER_ROLE_FROM_SESSION = "user_role";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String endpoint = request.getServletPath();
        if (UNAUTHENTICATED_ENDPOINTS.contains(endpoint)) {
            return true;
        }

        if (request.getSession(false) == null || request.getSession(false).getAttribute(USER_ID_FROM_SESSION) == null) {
            response.sendRedirect("/");
            return false;
        }

        if (request.getSession(false) != null && request.getSession(false).getAttribute(USER_ROLE_FROM_SESSION) != null && ROLE_PICK_ENDPOINT.contains(endpoint)) {
            response.sendRedirect("/home");

            return false;
        }

        return true;
    }
}
