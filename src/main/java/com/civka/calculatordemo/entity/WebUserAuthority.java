package com.civka.calculatordemo.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "authorities")
public class WebUserAuthority implements Serializable {

    @Embeddable
    public static class AuthorityId implements Serializable {
        @Column(name = "username")
        private String username;

        @Column(name = "authority")
        private String authority;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getAuthority() {
            return authority;
        }

        public void setAuthority(String authority) {
            this.authority = authority;
        }

        @Override
        public String toString() {
            return "AuthorityId{" +
                    "username='" + username + '\'' +
                    ", authority='" + authority + '\'' +
                    '}';
        }
    }

    @EmbeddedId
    private AuthorityId id;

    public WebUserAuthority(String username, String authority) {
        this.id = new AuthorityId();
        this.id.username = username;
        this.id.authority = authority;
    }

    public WebUserAuthority() {
        this.id = new AuthorityId();
    }

    public AuthorityId getId() {
        return id;
    }

    public void setId(AuthorityId id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "WebUserAuthority{" +
                "id=" + id +
                '}';
    }
}
