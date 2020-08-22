package pl.sda.todoapp.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;

public class UserDto {

    private String email;

    @Max(8)
    private String password;

    private String username;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
