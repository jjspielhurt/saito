package client.view;

import client.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterFrame extends JFrame {
    JLabel usernameLabel=new JLabel("Username:");
    JTextField username=new JTextField(20);

    JLabel mailLabel=new JLabel("Mail:");
    JTextField mail=new JTextField(20);

    JLabel passwordLabel=new JLabel("Password:");
    JTextField password=new JTextField(20);

    JButton submitBtn=new JButton("Submit");

    JLabel errorLabel=new JLabel("Register Invalid.");

    Client connection;

    public RegisterFrame(Client connection) {
        super("Register");
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

        panel.add(usernameLabel);
        panel.add(username);

        panel.add(mailLabel);
        panel.add(mail);

        panel.add(passwordLabel);
        panel.add(password);


        panel.add(submitBtn);

        add(panel,BorderLayout.CENTER);

        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(connection.register(username.getText(),mail.getText(),password.getText()))
                {
                    createLoginFrame();
                }
                else panel.add(errorLabel);
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

}
