package main.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "quests")
public class Quest {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "title", unique = true)
    @NotNull
    private String title;

    @Column(name = "description", unique = true)
    @NotNull
    private String description;

    @Column(name = "xp")
    private double xp;

    @Column(name = "banner_url", unique = true)
    @NotNull
    private String bannerUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "eligible_class")
    private PlayerClass eligibleClass;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    @NotNull
    private Item rewardItem;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private Player capturer;

    @Column(name = "created_on")
    @NotNull
    private LocalDateTime createdOn;

    @Column(name = "updated_on")
    @NotNull
    private LocalDateTime updatedOn;

    @Column(name = "created_by")
    @NotNull
    private String createdBy;

    @Column(name = "updated_by")
    @NotNull
    private String updatedBy;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getXp() {
        return xp;
    }

    public void setXp(double xp) {
        this.xp = xp;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public PlayerClass getEligibleClass() {
        return eligibleClass;
    }

    public void setEligibleClass(PlayerClass eligibleClass) {
        this.eligibleClass = eligibleClass;
    }

    public Item getRewardItem() {
        return rewardItem;
    }

    public void setRewardItem(Item rewardItem) {
        this.rewardItem = rewardItem;
    }

    public Player getCapturer() {
        return capturer;
    }

    public void setCapturer(Player capturer) {
        this.capturer = capturer;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Quest() {
    }

}

