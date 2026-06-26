package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;

import api.UserAPI;
import bean.user;

public class LeaderBoardForm extends JFrame {

    JTable table;
    DefaultTableModel model;

    public LeaderBoardForm() {

        setTitle("Leaderboard");
        setSize(500,400);
        setLayout(null);
        setLocationRelativeTo(null);

        model = new DefaultTableModel();

        model.addColumn("Rank");
        model.addColumn("Username");
        model.addColumn("Points");

        table = new JTable(model);

        JScrollPane sp =
                new JScrollPane(table);

        sp.setBounds(20,20,440,280);

        add(sp);

        loadLeaderboard();

        setVisible(true);
    }

    public void loadLeaderboard() {

        model.setRowCount(0);

        UserAPI api =
                new UserAPI();

        ArrayList<user> list =
                api.getLeaderboard();

        int rank = 1;

        for(user u : list) {

            model.addRow(new Object[] {

                    rank++,
                    u.getUsername(),
                    u.getPoints()

            });
        }
    }
}