package model;

import annotations.Pattern;
import annotations.Validate;
import annotations.username;

public class User {
    @username(name = "Username", min = 5, max = 15, msg = "Username must be between 5 and 15 characters")
    private String userName;
    
    @Validate(min = 8, max = 20, msg = "Password must be between 8 and 20 characters")
    private String password;

    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", msg = "Email must be a valid email address")
    private String email;

    @Pattern(regexp = "(\\+998|998)[0-9]{9}", msg = "Phone number must be a 10-digit number")
    private String phone;

    public String getUsername() {
        return userName;
    }

    public void setUsername(String userName) {
        this.userName = userName;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    
}