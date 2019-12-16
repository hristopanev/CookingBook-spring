package net.cookingbook.web.view.models.binding;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserRegisterBindingModel {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String ConfirmPassword;
    private String email;

    public UserRegisterBindingModel() {
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @NotEmpty(message = "Username name cannot be empty")
    @Size(min = 2, max = 30, message = "Username should be between 2 and 30")
    @Pattern(regexp = "")
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotEmpty(message = "Password cannot be empty")
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @NotEmpty(message = "Password cannot be empty")

    public String getConfirmPassword() {
        return this.ConfirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        ConfirmPassword = confirmPassword;
    }

    @NotEmpty(message = "Password cannot be empty")
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
