package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

import api.circleAPI;
import bean.circle;
import bean.user;

public class CircleForm extends JFrame {

    JTextField txtName;
    JTextField txtDescription;

    JTable table;
    DefaultTableModel model;

    public CircleForm(user u) {

        setTitle("Saving Circle");
        setSize(700,500);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel lblName =
                new JLabel("Circle Name");

        lblName.setBounds(30,30,100,25);
        add(lblName);

        txtName =
                new JTextField();

        txtName.setBounds(150,30,200,25);
        add(txtName);

        JLabel lblDesc =
                new JLabel("Description");

        lblDesc.setBounds(30,70,100,25);
        add(lblDesc);

        txtDescription =
                new JTextField();

        txtDescription.setBounds(150,70,200,25);
        add(txtDescription);

        JButton btnSave =
                new JButton("Create Circle");

        btnSave.setBounds(150,120,150,35);
        add(btnSave);

        model =
                new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("Circle Name");
        model.addColumn("Description");

        table =
                new JTable(model);

        JScrollPane sp =
                new JScrollPane(table);

        sp.setBounds(30,190,600,220);

        add(sp);

        loadTable();

        btnSave.addActionListener(e -> {

            circle c =
                    new circle();

            c.setUserId(
                    u.getUserId());

            c.setCircleName(
                    txtName.getText());

            c.setDescription(
                    txtDescription.getText());

            circleAPI api =
                    new circleAPI();

            int result =
                    api.addCircle(c);

            if(result > 0) {

                JOptionPane.showMessageDialog(
                        null,
                        "Circle Created");

                loadTable();
            }
        });

        setVisible(true);
    }

    public void loadTable() {

        model.setRowCount(0);

        circleAPI api =
                new circleAPI();

        ArrayList<circle> list =
                api.getAllCircles();

        for(circle c : list) {

            model.addRow(new Object[] {

                    c.getCircleId(),
                    c.getCircleName(),
                    c.getDescription()

            });
        }
    }
}