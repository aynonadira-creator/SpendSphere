package test;

import api.UserAPI;
import bean.user;

public class TestLogin {

    public static void main(String[] args) {

        UserAPI api = new UserAPI();

        user User =
                api.login(
                        "yin@gmail.com",
                        "123456");

        if (User != null) {

            System.out.println("Login Success");
            System.out.println("Welcome " + User.getUsername());

        } else {

            System.out.println("Login Failed");

        }
    }
}