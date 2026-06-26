package api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.budget;
import database.DBConnection;

public class budgetAPI {

    public int addBudget(budget b) {

        int status = 0;

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "INSERT INTO budgets(user_id,budget_amount,start_date,end_date)"
                    + " VALUES(?,?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, b.getUserId());
            ps.setDouble(2, b.getBudgetAmount());
            ps.setString(3, b.getStartDate());
            ps.setString(4, b.getEndDate());

            status = ps.executeUpdate();

            con.close();

        } catch(Exception e) {

            e.printStackTrace();

        }

        return status;
    }
    
    public double getBudgetAmount(int userId) {

        double amount = 0;

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "SELECT budget_amount "
                    + "FROM budgets "
                    + "WHERE user_id=? "
                    + "ORDER BY budget_id DESC "
                    + "LIMIT 1";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, userId);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {

                amount =
                        rs.getDouble("budget_amount");

            }

            con.close();

        }
        catch(Exception e) {

            e.printStackTrace();

        }

        return amount;
    }
}