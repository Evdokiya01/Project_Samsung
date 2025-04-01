package com.example.project_samsung;

public class User {
    public String email;
    public String password_confirmation;

    // Обязательно нужно для Firestore
    public User() {

    }

    public User(String email, String password_confirmation) {
        this.email = email;
        this.password_confirmation = password_confirmation;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordConfirmation() {
        return password_confirmation;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPasswordConfirmation(String password_confirmation) {
        this.password_confirmation = password_confirmation;
    }
}
