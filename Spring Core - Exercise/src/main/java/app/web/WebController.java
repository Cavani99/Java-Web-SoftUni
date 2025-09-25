package app.web;

import app.entities.Subscription;
import app.entities.User;
import app.entities.Wallet;
import app.entities.objects.LoginRequest;
import app.entities.objects.RegisterRequest;
import app.services.SubscriptionService;
import app.services.TransactionService;
import app.services.UserService;
import app.services.WalletService;
import jakarta.validation.Valid;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class WebController {

    private UserService userService;
    private TransactionService transactionService;
    private WalletService walletService;
    private SubscriptionService subscriptionService;

    public WebController(UserService userService, TransactionService transactionService, WalletService walletService, SubscriptionService subscriptionService) {
        this.userService = userService;
        this.transactionService = transactionService;
        this.walletService = walletService;
        this.subscriptionService = subscriptionService;
    }

    @GetMapping
    public ModelAndView getHomepage() {
        ModelAndView modelAndView = new ModelAndView("index.html");

        return modelAndView;
    }

    @GetMapping("/home")
    public ModelAndView getHome() {
        ModelAndView modelAndView = new ModelAndView("home.html");

        User user = userService.getActiveUser();
        Wallet wallet = walletService.getWalletByUser(user);
        Subscription subscription = subscriptionService.getSubscriptionByUser(user);
        modelAndView.addObject("user", user);
        modelAndView.addObject("wallet", wallet);
        modelAndView.addObject("subscription", subscription);

        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView getLogin() {
        ModelAndView modelAndView = new ModelAndView("login.html");

        modelAndView.addObject("user", new User());

        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView login(@Valid @ModelAttribute("user") LoginRequest user, BindingResult result) {

        if (result.hasErrors()) {
            ModelAndView mav = new ModelAndView("login");
            mav.addObject("user", user);
            return mav;
        }

        userService.login(new LoginRequest(
                user.getUsername(),
                user.getPassword()
        ));

        userService.setInactive();
        userService.setActiveUser(user.getUsername());

        return new ModelAndView("redirect:/home");
    }

    @GetMapping("/logout")
    public ModelAndView logout() {
        userService.setInactive();

        return new ModelAndView("redirect:/");
    }

    @GetMapping("/register")
    public ModelAndView getRegister() {
        ModelAndView modelAndView = new ModelAndView("register.html");

        modelAndView.addObject("user", new User());

        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView register(@Valid @ModelAttribute("user") RegisterRequest user,
                                 BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView mav = new ModelAndView("register");
            mav.addObject("user", user);
            return mav;
        }

        userService.register(new RegisterRequest(
                user.getCountry(),
                user.getUsername(),
                user.getPassword()
        ));

        return new ModelAndView("redirect:/login");
    }

    @GetMapping("/users/{id}/profile")
    public ModelAndView getUserProfile(@PathVariable long UUID) {
        ModelAndView modelAndView = new ModelAndView("profile-menu.html");

        return modelAndView;
    }

    @GetMapping("/wallets")
    public ModelAndView getWallets() {
        ModelAndView modelAndView = new ModelAndView("wallets.html");

        return modelAndView;
    }

    @GetMapping("/subscriptions")
    public ModelAndView getSubscriptions() {
        ModelAndView modelAndView = new ModelAndView("upgrade.html");

        return modelAndView;
    }

    @GetMapping("/subscriptions/history")
    public ModelAndView getSubscriptionsHistory() {
        ModelAndView modelAndView = new ModelAndView("subscription-history.html");

        return modelAndView;
    }

    @GetMapping("/users")
    public ModelAndView getUsers() {
        ModelAndView modelAndView = new ModelAndView("users.html");

        return modelAndView;
    }

    @GetMapping("/reports")
    public ModelAndView getReports() {
        ModelAndView modelAndView = new ModelAndView("reports.html");

        return modelAndView;
    }

    @GetMapping("/transactions")
    public ModelAndView getTransactions() {
        ModelAndView modelAndView = new ModelAndView("transactions.html");

        return modelAndView;
    }

    @GetMapping("/transfers")
    public ModelAndView getTransfers() {
        ModelAndView modelAndView = new ModelAndView("transfer.html");

        return modelAndView;
    }

    @GetMapping("/notifications")
    public ModelAndView getNotifications() {
        ModelAndView modelAndView = new ModelAndView("notifications.html");

        return modelAndView;
    }

}
