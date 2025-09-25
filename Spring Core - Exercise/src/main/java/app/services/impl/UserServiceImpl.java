package app.services.impl;

import app.entities.User;
import app.entities.enums.UserRole;
import app.entities.objects.LoginRequest;
import app.entities.objects.RegisterRequest;
import app.repositories.UserRepo;
import app.services.SubscriptionService;
import app.services.UserService;
import app.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;
    private WalletService walletService;
    private SubscriptionService subscriptionService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, WalletService walletService, SubscriptionService subscriptionService, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.walletService = walletService;
        this.subscriptionService = subscriptionService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(RegisterRequest registerRequest) {
        String username = registerRequest.getUsername();

        if (username.length() < 5) {
            throw new RuntimeException("Username must be at least 5 characters");
        }

        String password = registerRequest.getPassword();

        if (password.length() != 6) {
            throw new RuntimeException("Password must be exactly 6 digits");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setCountry(registerRequest.getCountry());
        user.setRole(UserRole.USER);
        user.setActive(true);
        user.setCreatedOn(LocalDateTime.now());
        user.setUpdatedOn(LocalDateTime.now());

        Optional<User> userByName = userRepo.findByUsername(username);
        if (userByName.isPresent()) {
            throw new RuntimeException("A User with this Username already exists");
        }

        userRepo.save(user);
        User savedUser = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        walletService.createNewWallet(savedUser);
        subscriptionService.createDefaultSubscription(savedUser);

        return user;
    }

    @Override
    public User login(LoginRequest loginRequest) {
        String username = loginRequest.getUsername();

        if (username.length() < 5) {
            throw new RuntimeException("Username must be at least 5 characters");
        }

        String password = loginRequest.getPassword();

        if (password.length() != 6) {
            throw new RuntimeException("Password must be exactly 6 digits");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));

        Optional<User> userByName = userRepo.findByUsername(username);
        if (userByName.isPresent()) {
            String userPass = userByName.get().getPassword();
            if (!passwordEncoder.matches(password, userPass)) {
                throw new RuntimeException("A User with this Username and Password does not exist");
            }

            return user;
        }

        throw new RuntimeException("A User with this Username and Password does not exist");
    }

    @Override
    public void setInactive() {
        List<User> userList = userRepo.findAll();

        for (User user : userList) {
            user.setActive(false);
            userRepo.save(user);
        }
    }

    @Override
    public void setActiveUser(String username) {
        Optional<User> user = userRepo.findByUsername(username);

        if (user.isPresent()) {
            user.get().setActive(true);
            userRepo.save(user.get());
        }
    }

    @Override
    public User getActiveUser() {
        Optional<User> user = userRepo.findByIsActive(true);

        return user.orElse(null);
    }
}
