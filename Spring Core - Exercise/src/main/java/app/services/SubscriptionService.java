package app.services;

import app.entities.Subscription;
import app.entities.User;

public interface SubscriptionService {

    void createDefaultSubscription(User user);

    Subscription getSubscriptionByUser(User user);
}
