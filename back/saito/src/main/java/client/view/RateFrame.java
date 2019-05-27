package client.view;

import client.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class RateFrame extends JFrame{

    Client connection;
    String bookID;
    JRadioButton btn1=new JRadioButton("1");
    JRadioButton btn2=new JRadioButton("2");
    JRadioButton btn3=new JRadioButton("3");
    JRadioButton btn4=new JRadioButton("4");
    JRadioButton btn5=new JRadioButton("5");
    JButton submitBtn=new JButton("Rate");

    ButtonGroup group=new ButtonGroup();

    public RateFrame(Client connection) {
        super("Saito");
        this.connection=connection;
        init();
    }
    private void init() {
        setLayout(new FlowLayout());
        this.setSize(700,800);
        this.setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btn1.setMnemonic((KeyEvent.VK_1));
        btn1.setActionCommand("1");
        btn2.setMnemonic((KeyEvent.VK_2));
        btn2.setActionCommand("2");
        btn3.setMnemonic((KeyEvent.VK_3));
        btn3.setActionCommand("3");
        btn4.setMnemonic((KeyEvent.VK_4));
        btn4.setActionCommand("4");
        btn5.setMnemonic((KeyEvent.VK_5));
        btn5.setActionCommand("5");

        group.add(btn1);
        group.add(btn2);
        group.add(btn3);
        group.add(btn4);
        group.add(btn5);

        add(btn1);
        add(btn2);
        add(btn3);
        add(btn4);
        add(btn5);
        add(submitBtn);

        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connection.rate(bookID,Integer.parseInt(group.getSelection().getActionCommand()));
                createMainFrame();
            }
        });


    }
    public void createMainFrame()
    {
        setVisible(false);
        dispose();
        new MainFrame(connection).setVisible(true);

    }
}
