package com.example.project_samsung;

 public class User {
     private String email;
     private String password;

     public User() {
         // Пустой конструктор необходим для Firebase
     }

     public User(String email, String password) {
         this.email = email;
         this.password = password;
     }

     public String getEmail() {
         return email;
     }

     public String getPassword() {
         return password;
     }
 }