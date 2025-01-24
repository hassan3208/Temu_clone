package com.hassan.temu_clone_2;

import java.util.List;

public class GoogleUser {
    private String name;
    private String email;
    private List<String> cartItems;

    public GoogleUser(String name, String email, String profilePicture, List<String> cartItems) {
        this.name = name;
        this.email = email;
        this.cartItems = cartItems;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<String> cartItems) {
        this.cartItems = cartItems;
    }
}
