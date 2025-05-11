package com.vtnn.app.models;

public enum UserRole {
    ADMIN("Quản lý", 1),
    SALES_STAFF("Nhân viên bán hàng", 2),
    ACCOUNTANT("Kế toán", 3),
    WAREHOUSE_STAFF("Nhân viên kho", 4);

    private final String displayName;
    private final int maVT;

    UserRole(String displayName, int maVT) {
        this.displayName = displayName;
        this.maVT = maVT;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getMaVT() {
        return maVT;
    }

    public static UserRole fromMaVT(int maVT) {
        for (UserRole role : values()) {
            if (role.maVT == maVT) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid MaVT: " + maVT);
    }
} 