package test;

import api.UserAPI;
import bean.user;

public class TestRegister {

    public static void main(String[] args) {

        user User = new user();

        User.setUsername("ayno");
        User.setEmail("ayno@gmail.com");
        User.setPassword("aynoimut");
        User.setPoints(0);

        UserAPI api = new UserAPI();

        int result = api.register(User);

        if (result > 0)
            System.out.println("Register Success");
        else
            System.out.println("Register Failed");
    }
}