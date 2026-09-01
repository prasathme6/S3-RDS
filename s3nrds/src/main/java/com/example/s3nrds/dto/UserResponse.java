package com.example.s3nrds.dto;

public class UserResponse {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String imageObjectKey;

    public UserResponse() {
    }

    public UserResponse(
            Long id,
            String name,
            String email,
            String phone,
            String address,
            String imageObjectKey
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.imageObjectKey = imageObjectKey;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getImageObjectKey() {
        return imageObjectKey;
    }
}