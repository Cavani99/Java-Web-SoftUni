package main.service.impl;

import main.model.Player;
import main.repository.PlayerRepository;
import main.service.PlayerService;
import main.web.dto.LoginRequest;
import main.web.dto.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class PlayerServiceImpl implements PlayerService {

    private PlayerRepository playerRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository, PasswordEncoder passwordEncoder) {
        this.playerRepository = playerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Player register(RegisterRequest registerRequest) {
        String username = registerRequest.getUsername();
        String password = registerRequest.getPassword();
        String nickname = registerRequest.getNickname();

        Player player = new Player();
        player.setUsername(username);
        player.setPassword(passwordEncoder.encode(password));
        player.setNickname(nickname);
        player.setCreatedOn(LocalDateTime.now());
        player.setUpdatedOn(LocalDateTime.now());

        Optional<Player> userByName = playerRepository.findByUsername(username);
        if (userByName.isPresent()) {
            throw new RuntimeException("A User with this Username already exists");
        }

        playerRepository.save(player);

        return player;
    }

    @Override
    public Player login(LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        Optional<Player> playerByUser = playerRepository.findByUsername(username);
        if (playerByUser.isPresent()) {
            String userPass = playerByUser.get().getPassword();
            if (!passwordEncoder.matches(password, userPass)) {
                throw new RuntimeException("A User with this Username and Password does not exist");
            }

            return playerByUser.get();
        }

        throw new RuntimeException("A User with this Username and Password does not exist");
    }

    @Override
    public Player getUser(UUID id) {
        Optional<Player> user = playerRepository.findById(id);
        return user.orElse(null);
    }

    @Override
    public Player savePlayer(Player player) {
        return playerRepository.save(player);
    }
}
