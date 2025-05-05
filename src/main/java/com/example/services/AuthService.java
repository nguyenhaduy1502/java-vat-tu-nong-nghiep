package com.example.services;

import com.example.models.User;
import com.example.models.UserRole;
import java.util.HashMap;
import java.util.Map;

public class AuthService {
    private static final Map<String, User> users = new HashMap<>();
    
    static {
        // Dummy users for testing
        users.put("admin@testing.com", new User("admin@testing.com", "admin123", UserRole.ADMIN));
        users.put("sales@testing.com", new User("sales@testing.com", "sales123", UserRole.SALES_STAFF));
        users.put("warehouse@testing.com", new User("warehouse@testing.com", "warehouse123", UserRole.WAREHOUSE_STAFF));
    }

    public static User authenticate(String email, String password) throws Exception {
        User user = users.get(email);
        if (user == null) {
            throw new Exception("User not found");
        }
        if (!user.getPassword().equals(password)) {
            throw new Exception("Invalid password");
        }
        return user;
    }
} 