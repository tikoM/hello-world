package com.example.demo.user.service;

import com.example.demo.user.model.User;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface UserService {

    /**
     * incrementing password generating tocken, adding role and saving user
     *
     * @param file-     photo of user
     * @param firstName
     * @param lastName
     * @param password
     * @param email
     * @param roleId    - must be null, for future
     */
    User createUser(MultipartFile file, String firstName, String lastName, String password, String email, Integer roleId);

    /**
     * update user
     *
     * @param user
     */
    User updateUser(User user);

    /**
     * deleting user
     *
     * @param user to be deleted
     */
    void deleteUser(User user);

    /**
     * getting user by email
     *
     * @param
     */
    User getUserByEmail(String email);

    /**
     * getting user by tocken
     *
     * @param tocken is generated key and is in coockie
     */
    User getUserByTokenKey(String tocken);

    /**
     * getting user by Id
     *
     * @param userId
     */
    User getUserById(Integer userId);

    /**
     * getting all users for admin only
     *
     * @param key generated key for current user
     */
    List<User> getAllUsers(@NotNull String key);

    void setEncodingService(EncodingService encodingService);

}
