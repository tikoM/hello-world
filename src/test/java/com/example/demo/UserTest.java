package com.example.demo;

import com.example.demo.common.dao.SessionFactoryHelper;
import com.example.demo.user.dao.UserDao;
import com.example.demo.user.model.User;
import com.example.demo.user.service.EncodingService;
import com.example.demo.user.service.UserService;
import com.example.demo.user.service.UserServiceImpl;
import com.example.demo.user.service.ValidationHelperService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserTest {
    private static UserService userService;

    @BeforeClass
    public static void adddBeforeAll() {
        SessionFactoryHelperTest sessionFactoryHelperTest = new SessionFactoryHelperTest();
        SessionFactoryHelper helper = mock(SessionFactoryHelper.class);
        when(helper.getSessionFactory()).thenReturn(sessionFactoryHelperTest.getSessionFactory());
        userService = new UserServiceImpl(new UserDao(helper));
        userService.setEncodingService(new EncodingService());
    }

    @Test
    public void create() {
        User user = userService.createUser(null, "aa", "aa", "aa", "aa", 1);
        assertTrue("if saved user id must be 1", user.getId().equals(1));
        userService.deleteUser(user);
    }

    @Test
    public void update() {
        User user = userService.createUser(null, "aa", "aa", "aa", "aa", 1);
        assertTrue("if saved user id must be 1", user.getId()!= null);
        user.setLastName("bb");
        userService.updateUser(user);
        assertTrue(user.getLastName().equals("bb"));
        userService.deleteUser(user);
    }

    @Test
    public void delete() {
        User user = userService.createUser(null, "bb", "aa", "asaaa", "aa@aa.aa", 1);
        userService.deleteUser(user);
        assertTrue("user was deleted, size must be 0", userService.getUserByEmail("aa@aa.aa") == null);
    }

    @Test
    public void getAll() {
    }

    @org.springframework.context.annotation.Configuration
    public static class ContextConfiguration {
    }
}