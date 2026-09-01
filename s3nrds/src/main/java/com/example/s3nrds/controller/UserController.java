package com.example.s3nrds.controller;

import com.example.s3nrds.dto.UserResponse;
import com.example.s3nrds.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<UserResponse> createUser(

            @RequestParam("name")
            String name,

            @RequestParam("email")
            String email,

            @RequestParam("phone")
            String phone,

            @RequestParam("address")
            String address,

            @RequestParam("image")
            MultipartFile image

    ) throws IOException {

        UserResponse response =
                userService.createUser(
                        name,
                        email,
                        phone,
                        address,
                        image
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}