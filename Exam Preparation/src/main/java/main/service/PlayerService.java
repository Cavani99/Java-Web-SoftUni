package main.service;

import main.model.Player;
import main.web.dto.LoginRequest;
import main.web.dto.RegisterRequest;

import java.util.UUID;

public interface PlayerService {
    Player register(RegisterRequest registerRequest);

    Player login(LoginRequest loginRequest);

    Player getUser(UUID id);

    Player savePlayer(Player player);

}
