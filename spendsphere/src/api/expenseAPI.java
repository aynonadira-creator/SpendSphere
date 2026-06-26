package api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.expense;
import database.DBConnection;

public class expenseAPI {

    // ADD EXPENSE
    public int addExpense(expense e) {

        int status = 0;

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "INSERT INTO expenses(user_id,amount,category,description,expense_date)"
                    + " VALUES(?,?,?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, e.getUserId());
            ps.setDouble(2, e.getAmount());
            ps.setString(3, e.getCategory());
            ps.setString(4, e.getDescription());
            ps.setString(5, e.getExpenseDate());

            status = ps.executeUpdate();

            con.close();

        } catch(Exception ex) {

            ex.printStackTrace();

        }

        return status;
    }

    // VIEW ALL EXPENSES
    public ArrayList<expense> getAllExpenses(int userId) {

        ArrayList<expense> list =
                new ArrayList<>();

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "SELECT * FROM expenses "
                    + "WHERE user_id=? "
                    + "AND is_deleted=0";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, userId);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

                expense e =
                        new expense();

                e.setExpenseId(
                        rs.getInt("expense_id"));

                e.setUserId(
                        rs.getInt("user_id"));

                e.setAmount(
                        rs.getDouble("amount"));

                e.setCategory(
                        rs.getString("category"));

                e.setDescription(
                        rs.getString("description"));

                e.setExpenseDate(
                        rs.getString("expense_date"));

                list.add(e);

            }

            con.close();

        } catch(Exception ex) {

            ex.printStackTrace();

        }

        return list;
    }

    // SEARCH EXPENSE
    public ArrayList<expense> searchExpense(String keyword) {

        ArrayList<expense> list =
                new ArrayList<>();

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "SELECT * FROM expenses "
                    + "WHERE category LIKE ? "
                    + "AND is_deleted=0";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(
                    1,
                    "%" + keyword + "%");

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()) {

                expense e =
                        new expense();

                e.setExpenseId(
                        rs.getInt("expense_id"));

                e.setUserId(
                        rs.getInt("user_id"));

                e.setAmount(
                        rs.getDouble("amount"));

                e.setCategory(
                        rs.getString("category"));

                e.setDescription(
                        rs.getString("description"));

                e.setExpenseDate(
                        rs.getString("expense_date"));

                list.add(e);

            }

            con.close();

        } catch(Exception ex) {

            ex.printStackTrace();

        }

        return list;
    }

    // UPDATE EXPENSE
    public int updateExpense(expense e) {

        int status = 0;

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "UPDATE expenses "
                    + "SET amount=?, "
                    + "category=?, "
                    + "description=?, "
                    + "expense_date=? "
                    + "WHERE expense_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setDouble(
                    1,
                    e.getAmount());

            ps.setString(
                    2,
                    e.getCategory());

            ps.setString(
                    3,
                    e.getDescription());

            ps.setString(
                    4,
                    e.getExpenseDate());

            ps.setInt(
                    5,
                    e.getExpenseId());

            status =
                    ps.executeUpdate();

            con.close();

        } catch(Exception ex) {

            ex.printStackTrace();

        }

        return status;
    }

    // SOFT DELETE
    public int deleteExpense(int expenseId) {

        int status = 0;

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "UPDATE expenses "
                    + "SET is_deleted=1 "
                    + "WHERE expense_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, expenseId);

            status =
                    ps.executeUpdate();

            con.close();

        } catch(Exception ex) {

            ex.printStackTrace();

        }

        return status;
    }
    
    public double getTotalExpense(int userId) {

        double total = 0;

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "SELECT SUM(amount) AS total "
                    + "FROM expenses "
                    + "WHERE user_id=? "
                    + "AND is_deleted=0";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, userId);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {

                total =
                        rs.getDouble("total");

            }

            con.close();

        }
        catch(Exception e) {

            e.printStackTrace();

        }

        return total;
    }
}