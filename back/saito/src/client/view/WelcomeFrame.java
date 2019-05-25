package client.view;

import client.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class WelcomeFrame extends JFrame {
    JButton loginBtn=new JButton("Login");
    JButton registerBtn=new JButton("Register");
    Client connection;
    public WelcomeFrame() {
        super("Welcome to Saito.");

        init();
    }
    private void init()
    {
        JPanel panel=new JPanel();
        BoxLayout layout=new BoxLayout(panel,BoxLayout.Y_AXIS);
        panel.setLayout(layout);
        try {
            connection=new Client();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setSize(700,500);
        this.setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.add(loginBtn);
        panel.add(registerBtn);

        add(panel);

        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createLoginFrame();
            }
        });
        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createRegisterFrame();
            }
        });
        //pack();
    }
    public void createLoginFrame()
    {
        setVisible(false);
        dispose();
        new LoginFrame(connection).setVisible(true);
    }
    public void createRegisterFrame()
    {
        setVisible(false);
        dispose();
        new RegisterFrame(connection).setVisible(true);
    }
}
