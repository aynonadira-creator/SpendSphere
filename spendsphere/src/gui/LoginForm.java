package gui;

import javax.swing.*;
import java.awt.event.*;

import api.UserAPI;
import bean.user;

public class LoginForm extends JFrame {

    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnRegister;

    public LoginForm() {

        setTitle("SpendSphere Login");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(50, 50, 100, 25);
        add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(150, 50, 180, 25);
        add(txtEmail);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(50, 100, 100, 25);
        add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(150, 100, 180, 25);
        add(txtPassword);

        // Login Button
        btnLogin = new JButton("Login");
        btnLogin.setBounds(80, 150, 100, 30);
        add(btnLogin);

        // Register Button
        btnRegister = new JButton("Register");
        btnRegister.setBounds(200, 150, 120, 30);
        add(btnRegister);

        // LOGIN EVENT
        btnLogin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String email = txtEmail.getText();
                String password = new String(txtPassword.getPassword());

                UserAPI api = new UserAPI();

                user u = api.login(email, password);

                if (u != null) {

                    new DashboardForm(u);

                    dispose();

                } else {

                    JOptionPane.showMessageDialog(
                            null,
                            "Invalid Email or Password");

                }
            }
        });

        // REGISTER EVENT
        btnRegister.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                new RegisterForm();

                dispose();

            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {

        new LoginForm();

    }
}