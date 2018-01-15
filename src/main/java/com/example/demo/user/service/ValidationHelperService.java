package com.example.demo.user.service;

import com.example.demo.user.dao.UserDao;
import com.example.demo.user.model.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Pattern;

public class ValidationHelperService {
    private UserDao userDao;
    private EncodingService encodingService;


    public boolean validatePassword(String password) {
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";
        Pattern regexCompiler = Pattern.compile(passwordRegex);
        return regexCompiler.matcher(password).find();
    }

    public User checkPassword(String password, String email) {
        User user = userDao.getUserByEmail(email);
        if (user == null) {
            return null;
        }
        if (user.getPassword().equals(encodingService.encodePassword(password))) {
            return user;
        } else return null;
    }

    public boolean validateEmail(String email) {
        Pattern regexCompiler = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        if (!regexCompiler.matcher(email).find()) {
            return false;
        }
        return userDao.getUserByEmail(email) == null;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setEncodingService(EncodingService encodingService) {
        this.encodingService = encodingService;
    }
}
