package com.civka.calculatordemo.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class WebUser {

    @Id
    @Column(name = "username")
    private String username;

    @OneToMany(mappedBy = "id.username", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WebUserAuthority> authorities;

    @Column(name = "email")
    private String email;

    @Column(name = "creditnumber")
    private Integer creditNumber;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private int enabled;

    @Column(name = "nickname")
    private String nickname;

    public WebUser() {
    }

    public WebUser(String username, String email, Integer creditNumber, String password) {
        this.username = username;
        this.password = password;
        this.creditNumber = creditNumber;
        this.email = email;
        this.enabled = 1;
        this.nickname = username;
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

    public Integer getCreditNumber() {
        return creditNumber;
    }

    public void setCreditNumber(Integer creditNumber) {
        this.creditNumber = creditNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public List<WebUserAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<WebUserAuthority> authorities) {
        this.authorities = authorities;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "WebUser{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", creditNumber='" + creditNumber + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
