package api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.circle;
import database.DBConnection;

public class circleAPI {

    public int addCircle(circle c) {

        int status = 0;

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "INSERT INTO circles(user_id,circle_name,description) "
                    + "VALUES(?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, c.getUserId());
            ps.setString(2, c.getCircleName());
            ps.setString(3, c.getDescription());

            status = ps.executeUpdate();

            con.close();

        } catch(Exception e) {

            e.printStackTrace();

        }

        return status;
    }

    public ArrayList<circle> getAllCircles() {

        ArrayList<circle> list =
                new ArrayList<>();

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "SELECT * FROM circles "
                    + "WHERE is_deleted=0";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

                circle c =
                        new circle();

                c.setCircleId(
                        rs.getInt("circle_id"));

                c.setUserId(
                        rs.getInt("user_id"));

                c.setCircleName(
                        rs.getString("circle_name"));

                c.setDescription(
                        rs.getString("description"));

                list.add(c);
            }

            con.close();

        } catch(Exception e) {

            e.printStackTrace();

        }

        return list;
    }
}