package app.entities.objects;

import app.entities.enums.Country;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotNull
    private Country country;

    @NotBlank
    @Size(min = 6, max = 24, message = "Username length must be between 6 and 26 symbols.")
    private String username;

    public Country getCountry() {
        return country;
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    @NotBlank
    @Size(min = 6, max = 6, message = "Password must be exactly 6 symbols.")
    private String password;

    public RegisterRequest(Country country, String username, String password) {
        this.country = country;
        this.username = username;
        this.password = password;
    }
}
