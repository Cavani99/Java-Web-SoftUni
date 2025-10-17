package main.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", unique = true)
    @NotNull
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    @NotNull
    private ItemType type;

    @Column(name = "xp_bonus_multiplier")
    private double xpBonusMultiplier;

    @Column(name = "icon_url", unique = true)
    @NotNull
    private String iconUrl;

    @Column(name = "created_on")
    @NotNull
    private LocalDateTime createdOn;

    @Column(name = "updated_on")
    @NotNull
    private LocalDateTime updatedOn;

    @Column(name = "created_by")
    @NotNull
    private String createdBy;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public double getXpBonusMultiplier() {
        return xpBonusMultiplier;
    }

    public void setXpBonusMultiplier(double xpBonusMultiplier) {
        this.xpBonusMultiplier = xpBonusMultiplier;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
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

    @Column(name = "updated_by")
    @NotNull
    private String updatedBy;

    public Item() {
    }
}

