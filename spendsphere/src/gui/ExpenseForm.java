package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

import api.expenseAPI;
import bean.expense;
import bean.user;

public class ExpenseForm extends JFrame {

    JTextField txtAmount, txtCategory, txtDescription, txtDate, txtSearch;
    JButton btnSave, btnUpdate, btnDelete, btnSearch, btnBack;

    JTable table;
    DefaultTableModel model;

    int selectedExpenseId = 0;

    public ExpenseForm(user u) {

        setTitle("Expense Form");
        setSize(850, 600);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // ================= INPUT =================
        JLabel lbl1 = new JLabel("Amount");
        lbl1.setBounds(30, 30, 100, 25);
        add(lbl1);

        txtAmount = new JTextField();
        txtAmount.setBounds(150, 30, 200, 25);
        add(txtAmount);

        JLabel lbl2 = new JLabel("Category");
        lbl2.setBounds(30, 70, 100, 25);
        add(lbl2);

        txtCategory = new JTextField();
        txtCategory.setBounds(150, 70, 200, 25);
        add(txtCategory);

        JLabel lbl3 = new JLabel("Description");
        lbl3.setBounds(30, 110, 100, 25);
        add(lbl3);

        txtDescription = new JTextField();
        txtDescription.setBounds(150, 110, 200, 25);
        add(txtDescription);

        JLabel lbl4 = new JLabel("Date");
        lbl4.setBounds(30, 150, 100, 25);
        add(lbl4);

        txtDate = new JTextField();
        txtDate.setBounds(150, 150, 200, 25);
        add(txtDate);

        // ================= BUTTON =================
        btnSave = new JButton("Save");
        btnSave.setBounds(30, 200, 100, 30);
        add(btnSave);

        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(140, 200, 100, 30);
        add(btnUpdate);

        btnDelete = new JButton("Delete");
        btnDelete.setBounds(250, 200, 100, 30);
        add(btnDelete);

        btnBack = new JButton("Back");
        btnBack.setBounds(360, 200, 100, 30);
        add(btnBack);

        // ================= SEARCH =================
        txtSearch = new JTextField();
        txtSearch.setBounds(30, 250, 200, 25);
        add(txtSearch);

        btnSearch = new JButton("Search");
        btnSearch.setBounds(250, 250, 100, 25);
        add(btnSearch);

        // ================= TABLE =================
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Amount");
        model.addColumn("Category");
        model.addColumn("Description");
        model.addColumn("Date");

        table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(30, 300, 750, 220);
        add(sp);

        loadTable(u);

        // ================= TABLE CLICK =================
        table.getSelectionModel().addListSelectionListener(e -> {

            int row = table.getSelectedRow();

            if (row >= 0) {

                selectedExpenseId = Integer.parseInt(model.getValueAt(row, 0).toString());
                txtAmount.setText(model.getValueAt(row, 1).toString());
                txtCategory.setText(model.getValueAt(row, 2).toString());
                txtDescription.setText(model.getValueAt(row, 3).toString());
                txtDate.setText(model.getValueAt(row, 4).toString());
            }
        });

        // ================= SAVE =================
        btnSave.addActionListener(e -> {

            try {

                expense ex = new expense();
                ex.setUserId(u.getUserId());
                ex.setAmount(Double.parseDouble(txtAmount.getText()));
                ex.setCategory(txtCategory.getText());
                ex.setDescription(txtDescription.getText());
                ex.setExpenseDate(txtDate.getText());

                expenseAPI api = new expenseAPI();
                api.addExpense(ex);

                JOptionPane.showMessageDialog(null, "Saved Successfully!");
                loadTable(u);

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Invalid Input!");
            }
        });

        // ================= UPDATE =================
        btnUpdate.addActionListener(e -> {

            if (selectedExpenseId == 0) {
                JOptionPane.showMessageDialog(null, "Select a record first!");
                return;
            }

            try {

                expense ex = new expense();
                ex.setExpenseId(selectedExpenseId);
                ex.setAmount(Double.parseDouble(txtAmount.getText()));
                ex.setCategory(txtCategory.getText());
                ex.setDescription(txtDescription.getText());
                ex.setExpenseDate(txtDate.getText());

                expenseAPI api = new expenseAPI();
                int result = api.updateExpense(ex);

                if (result > 0) {
                    JOptionPane.showMessageDialog(null, "Updated Successfully!");
                    loadTable(u);
                } else {
                    JOptionPane.showMessageDialog(null, "Update Failed!");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // ================= DELETE =================
        btnDelete.addActionListener(e -> {

            if (selectedExpenseId == 0) {
                JOptionPane.showMessageDialog(null, "Select a record first!");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Confirm delete?",
                    "Delete",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {

                expenseAPI api = new expenseAPI();
                int result = api.deleteExpense(selectedExpenseId);

                if (result > 0) {
                    JOptionPane.showMessageDialog(null, "Deleted Successfully!");
                    selectedExpenseId = 0;
                    loadTable(u);
                } else {
                    JOptionPane.showMessageDialog(null, "Delete Failed!");
                }
            }
        });

        // ================= SEARCH =================
        btnSearch.addActionListener(e -> {

            model.setRowCount(0);

            expenseAPI api = new expenseAPI();

            ArrayList<expense> list =
                    api.searchExpense(txtSearch.getText());

            for (expense ex : list) {

                model.addRow(new Object[]{
                        ex.getExpenseId(),
                        ex.getAmount(),
                        ex.getCategory(),
                        ex.getDescription(),
                        ex.getExpenseDate()
                });
            }
        });

        // ================= BACK =================
        btnBack.addActionListener(e -> {
            new DashboardForm(u);
            dispose();
        });

        setVisible(true);
    }

    // ================= LOAD TABLE =================
    public void loadTable(user u) {

        model.setRowCount(0);

        expenseAPI api = new expenseAPI();
        ArrayList<expense> list = api.getAllExpenses(u.getUserId());

        for (expense ex : list) {

            model.addRow(new Object[]{
                    ex.getExpenseId(),
                    ex.getAmount(),
                    ex.getCategory(),
                    ex.getDescription(),
                    ex.getExpenseDate()
            });
        }
    }
}