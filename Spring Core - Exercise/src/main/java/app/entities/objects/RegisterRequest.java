package app.entities.objects;

import app.entities.enums.Country;

public class RegisterRequest {
    private Country country;

    private String username;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;

    public RegisterRequest(Country country, String username, String password) {
        this.country = country;
        this.username = username;
        this.password = password;
    }
}
