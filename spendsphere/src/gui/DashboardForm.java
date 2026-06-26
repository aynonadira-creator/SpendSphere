package gui;

import javax.swing.*;

import api.budgetAPI;
import api.expenseAPI;
import bean.user;

public class DashboardForm extends JFrame {

    public DashboardForm(user u) {

        setTitle("SpendSphere Dashboard");
        setSize(550, 450);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        budgetAPI budgetApi = new budgetAPI();
        expenseAPI expenseApi = new expenseAPI();

        double budget =
                budgetApi.getBudgetAmount(
                        u.getUserId());

        double spent =
                expenseApi.getTotalExpense(
                        u.getUserId());

        double remaining =
                budget - spent;

        // Welcome Label
        JLabel lblWelcome =
                new JLabel("Welcome, " + u.getUsername());

        lblWelcome.setBounds(30, 20, 300, 30);
        add(lblWelcome);

        // Points Label
        JLabel lblPoints =
                new JLabel("Points : " + u.getPoints());

        lblPoints.setBounds(30, 50, 300, 30);
        add(lblPoints);

        // Budget Label
        JLabel lblBudget =
                new JLabel("Budget : RM " + budget);

        lblBudget.setBounds(30, 90, 300, 30);
        add(lblBudget);

        // Spent Label
        JLabel lblSpent =
                new JLabel("Spent : RM " + spent);

        lblSpent.setBounds(30, 120, 300, 30);
        add(lblSpent);

        // Remaining Label
        JLabel lblRemaining =
                new JLabel("Remaining : RM " + remaining);

        lblRemaining.setBounds(30, 150, 300, 30);
        add(lblRemaining);

        // Over Budget Warning
        if (remaining < 0) {

            JLabel lblWarning =
                    new JLabel("⚠ OVER BUDGET!");

            lblWarning.setBounds(30, 180, 300, 30);
            add(lblWarning);
        }

        // Budget Button
        JButton btnBudget =
                new JButton("Budget");

        btnBudget.setBounds(30, 240, 120, 40);
        add(btnBudget);

        btnBudget.addActionListener(e -> {

            new BudgetForm(u);

        });

        // Expense Button
        JButton btnExpense =
                new JButton("Expense");

        btnExpense.setBounds(200, 240, 120, 40);
        add(btnExpense);

        btnExpense.addActionListener(e -> {

            new ExpenseForm(u);

        });

        // Circle Button
     // Circle Button
        JButton btnCircle =
                new JButton("Circle");

        btnCircle.setBounds(370, 240, 120, 40);
        add(btnCircle);

        btnCircle.addActionListener(e -> {

            new CircleForm(u);

        });

        // Leaderboard Button
        JButton btnLeaderboard =
                new JButton("Leaderboard");
        
        btnLeaderboard.addActionListener(e -> {

            new LeaderBoardForm();

        });

        btnLeaderboard.setBounds(170, 320, 180, 40);
        add(btnLeaderboard);
        
     // Analytics Button
        JButton btnAnalytics =
                new JButton("Analytics");

        btnAnalytics.setBounds(170, 370, 180, 40);

        add(btnAnalytics);

        btnAnalytics.addActionListener(e -> {

            new ChartForm();

        });

        setVisible(true);
    }
}