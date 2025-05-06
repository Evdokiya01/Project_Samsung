package com.example.project_samsung;

 public class User {
     private String email;
     private String password; // В реальном приложении не следует хранить пароли в открытом виде!
                              // Используйте Firebase Authentication для управления пользователями.

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