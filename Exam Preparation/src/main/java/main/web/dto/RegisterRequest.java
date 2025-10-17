package main.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank
    @Size(max = 12, message = "invalid username")
    private String username;

    @NotBlank
    @Size(max = 12, message = "invalid password")
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    @NotBlank
    @Size(max = 12, message = "invalid nickname")
    private String nickname;

    public RegisterRequest(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }
}
