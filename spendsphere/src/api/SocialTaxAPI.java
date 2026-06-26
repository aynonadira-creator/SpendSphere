package api;

import java.sql.Connection;
import java.sql.PreparedStatement;

import bean.SocialTax;
import database.DBConnection;

public class SocialTaxAPI {

    public int addTax(SocialTax tax) {

        int status = 0;

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "INSERT INTO social_tax "
                    + "(user_id, penalty_points, message) "
                    + "VALUES (?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, tax.getUserId());
            ps.setInt(2, tax.getPenaltyPoints());
            ps.setString(3, tax.getMessage());

            status = ps.executeUpdate();

            con.close();

        } catch(Exception e) {

            e.printStackTrace();

        }

        return status;
    }
}