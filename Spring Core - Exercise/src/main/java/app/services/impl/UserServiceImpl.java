package app.services.impl;

import app.entities.User;
import app.entities.objects.LoginRequest;
import app.entities.objects.RegisterRequest;
import app.repositories.UserRepo;
import app.services.SubscriptionService;
import app.services.UserService;
import app.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;
    private WalletService walletService;
    private SubscriptionService subscriptionService;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, WalletService walletService, SubscriptionService subscriptionService) {
        this.userRepo = userRepo;
        this.walletService = walletService;
        this.subscriptionService = subscriptionService;
    }

    @Override
    public User register(RegisterRequest registerRequest) {
        String username = registerRequest.getUsername();

        if (username.length() < 5) {
            System.out.println("Username must be at least 5 characters");
            return null;
        }

        String password = registerRequest.getPassword();

        if (password.length() != 6) {
            System.out.println("Password must be exactly 6 digits");
            return null;
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setCountry(registerRequest.getCountry());

        Optional<User> userByName = userRepo.findByUsername(username);
        if (userByName.isPresent()) {
            System.out.println("A User with this Username already exists");

            return null;
        }

        userRepo.save(user);


        return null;
    }

    @Override
    public User login(LoginRequest loginRequest) {
        String username = loginRequest.getUsername();

        if (username.length() < 5) {
            System.out.println("Username must be at least 5 characters");
            return null;
        }

        String password = loginRequest.getPassword();

        if (password.length() != 6) {
            System.out.println("Password must be exactly 6 digits");
            return null;
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        Optional<User> userByName = userRepo.findByUsernameAndPassword(username, password);
        if (userByName.isPresent()) {
            return user;
        }

        System.out.println("A User with this Username and Password does not exist");
        return null;
    }
}
