package edu.erezd.erezproject;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class PasswordGenerator {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "password";
        String encodedPassword = encoder.encode(rawPassword);
        System.out.printf(encodedPassword);
    }

}