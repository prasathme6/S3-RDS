package com.example.s3nrds.service;

import com.example.s3nrds.dto.UserResponse;
import com.example.s3nrds.entity.User;
import com.example.s3nrds.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final S3Service s3Service;

    public UserService(
            UserRepository userRepository,
            S3Service s3Service
    ) {
        this.userRepository = userRepository;
        this.s3Service = s3Service;
    }

    public UserResponse createUser(
            String name,
            String email,
            String phone,
            String address,
            MultipartFile image
    ) throws IOException {

        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException(
                    "Email already exists"
            );
        }

        User user = new User();

        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setAddress(address);

        User savedUser = userRepository.save(user);

        String objectKey = generateObjectKey(
                savedUser.getId(),
                image
        );

        s3Service.uploadFile(objectKey, image);

        savedUser.setImageObjectKey(objectKey);

        User updatedUser = userRepository.save(savedUser);

        return new UserResponse(
                updatedUser.getId(),
                updatedUser.getName(),
                updatedUser.getEmail(),
                updatedUser.getPhone(),
                updatedUser.getAddress(),
                updatedUser.getImageObjectKey()
        );
    }

    private String generateObjectKey(
            Long userId,
            MultipartFile image
    ) {

        String originalFilename =
                image.getOriginalFilename();

        String extension = "";

        if (originalFilename != null &&
                originalFilename.contains(".")) {

            extension =
                    originalFilename.substring(
                            originalFilename.lastIndexOf(".")
                    );
        }

        return "users/"
                + userId
                + "/profile/"
                + UUID.randomUUID()
                + extension;
    }
}