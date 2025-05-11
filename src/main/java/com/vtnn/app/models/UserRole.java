package com.vtnn.app.models;

public enum UserRole {
    ADMIN("Quản trị viên"),
    SALES_STAFF("Nhân viên bán hàng"),
    WAREHOUSE_STAFF("Nhân viên kho");

    private final String displayName;

    UserRole(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
} 