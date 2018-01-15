package com.example.demo.user.controller;

import com.example.demo.user.model.User;
import com.example.demo.user.service.UserService;
import com.example.demo.user.service.ValidationHelperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ValidationHelperService validationHelperService;

    /**
     * checking email and password
     * saving user adding key in coockies and rediectiong to home page
     */
    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public void saveUser(HttpServletResponse response, @RequestParam("file") MultipartFile file,
                         @RequestParam("firstName") String firstName,
                         @RequestParam("lastName") String lastName,
                         @RequestParam("password") String password,
                         @RequestParam("email") String email) {
        if (validationHelperService.validateEmail(email) &&
                validationHelperService.validatePassword(password)) {
            User user = userService.createUser(file, firstName, lastName, password, email, null);
            try {
                Cookie userCookie =
                        new Cookie("user", user.getGeneratedKey());
                response.addCookie(userCookie);
                response.sendRedirect("index.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                response.sendRedirect("signup.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /*
     *
     * checking email and password
     * checking user adding key in coockies and rediectiong to home page
     *
     * */
    @RequestMapping(value = "/loginUser", method = RequestMethod.GET)
    public void login(HttpServletResponse response,
                      @RequestParam(value = "password") String password,
                      @RequestParam(value = "email") String email) {
        User user = validationHelperService.checkPassword(password, email);
        if (user != null && user.getGeneratedKey() != null) {
            try {
                Cookie userCookie =
                        new Cookie("user", user.getGeneratedKey());
                response.addCookie(userCookie);
                if (user.getRole().getId().equals(1)) {
                    response.sendRedirect("admin.html");
                } else {
                    response.sendRedirect("index.html");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                response.sendRedirect("login.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @return if user is not authorized returning null
     * else return related data
     */
    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public User getUserDate(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user") && cookie.getValue() != null) {
                    User user = userService.getUserByTokenKey(cookie.getValue());
                    user.setPassword(null);
                    if (user.getRole().getId().equals(1)) {
                        try {
                            response.sendRedirect("admin.html");
                            return null;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    return user;
                }
            }
        }
        return null;
    }

    /**
     * @return if user doesn't have admin role return null
     * else return all users
     */
    @RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
    public List<User> getAllUsers(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user") && cookie.getValue() != null) {
                    return userService.getAllUsers(cookie.getValue());
                }
            }
        }
        return null;
    }

    @RequestMapping(value = "/userDelete", method = RequestMethod.POST)
    public void deleteUsers(@RequestBody HttpServletRequest request, HttpServletResponse response, User user) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user") && cookie.getValue() != null) {
                    User currentUser = userService.getUserByTokenKey(cookie.getValue());
                    if (currentUser.getRole().getId().equals(1)) {
                        try {
                            userService.deleteUser(user);
                            response.sendRedirect("admin.html");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
