package app.entities;

import app.entities.enums.SubscriptionPeriod;
import app.entities.enums.SubscriptionStatus;
import app.entities.enums.SubscriptionType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "subscription")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return owner;
    }

    public void setUser(User owner) {
        this.owner = owner;
    }

    public SubscriptionStatus getStatus() {
        return status;
    }

    public void setStatus(SubscriptionStatus status) {
        this.status = status;
    }

    public SubscriptionPeriod getPeriod() {
        return period;
    }

    public void setPeriod(SubscriptionPeriod period) {
        this.period = period;
    }

    public SubscriptionType getType() {
        return type;
    }

    public void setType(SubscriptionType type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getRenewalAllowed() {
        return renewalAllowed;
    }

    public void setRenewalAllowed(Boolean renewalAllowed) {
        this.renewalAllowed = renewalAllowed;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getCompletedOn() {
        return completedOn;
    }

    public void setCompletedOn(LocalDateTime completedOn) {
        this.completedOn = completedOn;
    }

    @ManyToOne(optional = false, cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User owner;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private SubscriptionStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "period")
    private SubscriptionPeriod period;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private SubscriptionType type;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "renewal_allowed")
    private Boolean renewalAllowed;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "completed_on")
    private LocalDateTime completedOn;

    public Subscription() {
    }
}
