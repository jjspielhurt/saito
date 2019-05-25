package client.view;

import client.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    JLabel usernameLabel=new JLabel("Username:");
    JTextField username=new JTextField(20);

    JLabel passwordLabel=new JLabel("Password:");
    JTextField password=new JTextField(20);

    JButton submitBtn=new JButton("Submit");
    Client connection;

    JLabel errorLabel=new JLabel("Invalid Username/Password");

    public LoginFrame(Client connection) {
        super("Login");
        this.connection=connection;
        init();
    }
    private void init()
    {
        JPanel panel=new JPanel();
        BoxLayout layout=new BoxLayout(panel,BoxLayout.Y_AXIS);
        panel.setLayout(layout);

        this.setSize(700,500);
        this.setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        panel.add();
        panel.add(usernameLabel);
        panel.add(username);

        panel.add(passwordLabel);
        panel.add(password);

        panel.add(submitBtn);

        add(panel);

        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(connection.login(username.getText(),password.getText())) createMainFrame();
                else panel.add(errorLabel);
            }
        });
        //pack();
    }
    public void createMainFrame()
    {
        setVisible(false);
        dispose();
        new MainFrame(connection).setVisible(true);
    }
}
