package com.example.demo.user.service;

import com.example.demo.role.model.Role;
import com.example.demo.user.dao.UserDao;
import com.example.demo.user.model.User;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static com.example.demo.common.service.FileUploadManager.createFile;

public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private ValidationHelperService validationHelperService;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public User createUser(MultipartFile file, String firstName, String lastName, String password, String email, Integer roleId) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        String photoPath = null;
        String encodedPassword = validationHelperService.encodePassword(password);
        user.setPassword(String.valueOf(encodedPassword));
        String uniqueID = UUID.randomUUID().toString();
        user.setIncrementedKey(uniqueID);
        if (file != null) {
            try {
                photoPath = createFile(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (roleId == null) {
            Role role = new Role();
            role.setId(2);
            role.setName("public");
            user.setRole(role);
        }
        user.setPicturePath(photoPath);
        return userDao.create(user);
    }

    @Override
    public User updateUser(User user) {
        return userDao.update(user);
    }


    @Override
    public void deleteUser(User user) {
        userDao.delete(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public User getUserByTokenKey(String token) {
        return userDao.getUserByToken(token);
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public List<User> getAllUsers(@NotNull String key) {
        if(getUserByTokenKey(key).getRole().getId().equals(1)){
            return userDao.getAll(User.class);
        }else {
            return null;
        }
    }

    public void setValidationHelperService(ValidationHelperService validationHelperService) {
        this.validationHelperService = validationHelperService;
    }
}
