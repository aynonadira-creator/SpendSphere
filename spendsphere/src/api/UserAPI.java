package api;

import bean.user;

import database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserAPI {

    public int register(user User) {

        int status = 0;

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "INSERT INTO users(username,email,password,points)"
                            + " VALUES(?,?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, User.getUsername());
            ps.setString(2, User.getEmail());
            ps.setString(3, User.getPassword());
            ps.setInt(4, User.getPoints());

            status = ps.executeUpdate();

            con.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return status;
    }
    
    public user login(String email, String password) {

        user User = null;

        try {

            Connection con = DBConnection.getConnection();

            String sql =
                    "SELECT * FROM users WHERE email=? AND password=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                User = new user();

                User.setUserId(rs.getInt("user_id"));
                User.setUsername(rs.getString("username"));
                User.setEmail(rs.getString("email"));
                User.setPassword(rs.getString("password"));
                User.setPoints(rs.getInt("points"));

            }

            con.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return User;
    }
    
    public void deductPoints(int userId, int points) {

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "UPDATE users "
                    + "SET points = points - ? "
                    + "WHERE user_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, points);
            ps.setInt(2, userId);

            ps.executeUpdate();

            con.close();

        } catch(Exception e) {

            e.printStackTrace();

        }
    }
    
    public ArrayList<user> getLeaderboard() {

        ArrayList<user> list =
                new ArrayList<>();

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "SELECT * FROM users "
                    + "ORDER BY points DESC";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

                user u =
                        new user();

                u.setUserId(
                        rs.getInt("user_id"));

                u.setUsername(
                        rs.getString("username"));

                u.setPoints(
                        rs.getInt("points"));

                list.add(u);
            }

            con.close();

        } catch(Exception e) {

            e.printStackTrace();

        }

        return list;
    } 
}