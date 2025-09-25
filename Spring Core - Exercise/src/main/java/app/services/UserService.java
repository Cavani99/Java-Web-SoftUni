package app.services;

import app.entities.User;
import app.entities.objects.LoginRequest;
import app.entities.objects.RegisterRequest;

public interface UserService {
    User register(RegisterRequest registerRequest);

    User login(LoginRequest loginRequest);

    void setInactive();

    void setActiveUser(String username);

    User getActiveUser();
}
