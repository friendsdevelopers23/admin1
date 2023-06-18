package com.calsoft.model;

import java.util.Collection;

public class UserProfile {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Collection<AuthorityVO> authorities;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<AuthorityVO> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<AuthorityVO> authorities) {
        this.authorities = authorities;
    }
}
