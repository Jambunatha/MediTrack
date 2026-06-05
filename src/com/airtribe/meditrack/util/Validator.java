package com.airtribe.meditrack.util;

import java.util.regex.Pattern;

public class Validator {
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\+?[0-9]{10,15}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    private Validator() {
        // Private constructor to prevent instantiation
    }

    public static void requireNonBank(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be null or empty");
        }
    }

    public static void requireValidPhone(String phone) {
        requireNonBank(phone, "Phone number");
        if (!PHONE_PATTERN.matcher(phone).matches()) {
            throw new IllegalArgumentException("Invalid phone number");
        }
    }

    public static void requireValidEmail(String email) {
        requireNonBank(email, "Email");
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid email address");
        }
    }
}
