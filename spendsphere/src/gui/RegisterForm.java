package gui;

import javax.swing.*;

import api.UserAPI;
import bean.user;

import java.awt.event.*;

public class RegisterForm extends JFrame {

    private JTextField txtUsername;
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JButton btnRegister;
    private JButton btnBack;

    public RegisterForm() {

        setTitle("SpendSphere Register");
        setSize(400, 320);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Username
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(50, 50, 100, 25);
        add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(150, 50, 180, 25);
        add(txtUsername);

        // Email
        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(50, 90, 100, 25);
        add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(150, 90, 180, 25);
        add(txtEmail);

        // Password
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(50, 130, 100, 25);
        add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(150, 130, 180, 25);
        add(txtPassword);

        // Register Button
        btnRegister = new JButton("Register");
        btnRegister.setBounds(150, 180, 120, 30);
        add(btnRegister);

        // Back Button
        btnBack = new JButton("Back");
        btnBack.setBounds(150, 220, 120, 30);
        add(btnBack);

        // BACK → LoginForm
        btnBack.addActionListener(e -> {
            new LoginForm();
            dispose();
        });

        // REGISTER ACTION
        btnRegister.addActionListener(e -> {

            try {

                user u = new user();
                u.setUsername(txtUsername.getText());
                u.setEmail(txtEmail.getText());
                u.setPassword(new String(txtPassword.getPassword()));
                u.setPoints(0);

                UserAPI api = new UserAPI();
                int result = api.register(u);

                if (result > 0) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Register Success!"
                    );

                    // auto go login after success
                    new LoginForm();
                    dispose();

                } else {

                    JOptionPane.showMessageDialog(
                            null,
                            "Register Failed!"
                    );
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error occurred");
            }
        });

        setVisible(true);
    }
}