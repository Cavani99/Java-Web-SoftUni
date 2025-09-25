package app.services.impl;

import app.entities.Subscription;
import app.entities.User;
import app.entities.enums.SubscriptionPeriod;
import app.entities.enums.SubscriptionType;
import app.repositories.SubscriptionRepository;
import app.services.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private SubscriptionRepository subscriptionRepo;

    @Autowired
    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepo) {
        this.subscriptionRepo = subscriptionRepo;
    }

    @Override
    public void createDefaultSubscription(User user) {
        Subscription subscription = new Subscription();
        subscription.setType(SubscriptionType.DEFAULT);
        subscription.setPeriod(SubscriptionPeriod.MONTHLY);
        subscription.setPrice(BigDecimal.valueOf(0));
        subscription.setRenewalAllowed(true);
        subscription.setUser(user);
        subscription.setCreatedOn(LocalDateTime.now());

        subscriptionRepo.save(subscription);
    }

    @Override
    public Subscription getSubscriptionByUser(User user) {
        Optional<Subscription> subscription = subscriptionRepo.findByOwner(user);

        return subscription.orElse(null);
    }
}
