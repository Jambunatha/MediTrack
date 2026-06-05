package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.util.Validator;

import java.io.Serializable;

public abstract class Person implements Serializable {
    private final String id;
    private String name;
    private String phone;
    private String email;

    public Person(String id, String name, String phone, String email) {
        Validator.requireNonBank(id, "ID");
        Validator.requireNonBank(name, "Name");
        Validator.requireValidPhone(phone);
        Validator.requireValidEmail(email);
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        Validator.requireNonBank(name, "Name");
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        Validator.requireValidPhone(phone);
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        Validator.requireValidEmail(email);
        this.email = email;
    }
}
