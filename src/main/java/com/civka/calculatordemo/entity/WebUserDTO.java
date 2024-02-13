package com.civka.calculatordemo.entity;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

@Component
public class WebUserDTO {

    @NotNull
    @Size(min = 5, message = "Must be at least 5 symbols")
    private String username;

    @NotNull
    @Size(min = 5, message = "Must be at least 5 symbols")
    private String email;

    @Digits(integer = 4, fraction = 0, message = "Must be 4 symbols")
    private Integer creditNumber;

    @NotNull
    @Size(min = 5, message = "Must be at least 5 symbols")
    private String password;

    private String passwordRepeat;

    @AssertTrue(message = "Passwords must match")
    public boolean isPasswordMatching() {
        if (passwordRepeat != null) {
            return password.equals(passwordRepeat);
        } else return false;
    }

    public WebUserDTO() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getCreditNumber() {
        return creditNumber;
    }

    public void setCreditNumber(Integer creditNumber) {
        this.creditNumber = creditNumber;
    }

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }
}
