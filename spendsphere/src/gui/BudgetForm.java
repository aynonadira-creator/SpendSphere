package gui;

import javax.swing.*;

import api.budgetAPI;
import bean.budget;
import bean.user;

public class BudgetForm extends JFrame {

    private JTextField txtAmount;
    private JTextField txtStartDate;
    private JTextField txtEndDate;

    private JButton btnSave;
    private JButton btnBack;

    public BudgetForm(user u) {

        setTitle("Budget Form");
        setSize(400, 300);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel lblAmount =
                new JLabel("Budget Amount");

        lblAmount.setBounds(30, 40, 120, 25);
        add(lblAmount);

        txtAmount = new JTextField();

        txtAmount.setBounds(170, 40, 150, 25);
        add(txtAmount);

        JLabel lblStartDate =
                new JLabel("Start Date");

        lblStartDate.setBounds(30, 90, 120, 25);
        add(lblStartDate);

        txtStartDate = new JTextField();

        txtStartDate.setBounds(170, 90, 150, 25);
        add(txtStartDate);

        JLabel lblEndDate =
                new JLabel("End Date");

        lblEndDate.setBounds(30, 140, 120, 25);
        add(lblEndDate);

        txtEndDate = new JTextField();

        txtEndDate.setBounds(170, 140, 150, 25);
        add(txtEndDate);

        btnSave = new JButton("Save Budget");

        btnSave.setBounds(120, 200, 150, 35);
        add(btnSave);
        
        btnBack = new JButton("Back");
        btnBack.setBounds(280, 200, 100, 35);
        add(btnBack);
        
        btnBack.addActionListener(e -> {

            new DashboardForm(u);

            dispose();

        });

        btnSave.addActionListener(e -> {

            try {

                budget b = new budget();

                b.setUserId(u.getUserId());

                b.setBudgetAmount(
                        Double.parseDouble(
                                txtAmount.getText()));

                b.setStartDate(
                        txtStartDate.getText());

                b.setEndDate(
                        txtEndDate.getText());

                budgetAPI api =
                        new budgetAPI();

                int result =
                        api.addBudget(b);

                if (result > 0) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Budget Saved Successfully");

                } else {

                    JOptionPane.showMessageDialog(
                            null,
                            "Failed To Save Budget");

                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        null,
                        "Invalid Input");

            }

        });

        setVisible(true);
    }
}