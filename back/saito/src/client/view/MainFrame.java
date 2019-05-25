package client.view;

import client.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainFrame extends JFrame {
//    JLabel searchLabel=new JLabel("Search:");
JPanel searchUpperPanel=new JPanel();
    JPanel searchPanel=new JPanel();
    JTextField searchQuery=new JTextField(20);
    JButton searchBtn=new JButton("Search");
    JTextArea searchList=new JTextArea(10,1);
    JScrollPane resultsField=new JScrollPane(searchList);



    JPanel recommPanel=new JPanel();
    JLabel recommLabel=new JLabel("Recommendations:");
    JTextArea recomList=new JTextArea(10,1);
    JScrollPane recomField=new JScrollPane(recomList);

    JButton refreshBtn=new JButton("Refresh");


    JPanel buttonPanel=new JPanel();
    JButton downloadBtn=new JButton("Download");
    JButton rateBtn=new JButton("Rate");

    Client connection;

    public MainFrame(Client connection) {
        super("Saito");
        this.connection=connection;
        init();
    }
    private void init() {
        setLayout(new BorderLayout());
        this.setSize(700,800);
        this.setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        resultsField.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        recomField.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        searchPanel.setLayout(new BorderLayout());
        recommPanel.setLayout(new BorderLayout());
        searchUpperPanel.setLayout(new BorderLayout());
        searchUpperPanel.add(searchQuery,BorderLayout.WEST);
        searchUpperPanel.add(searchBtn,BorderLayout.EAST);

        searchPanel.add(searchUpperPanel,BorderLayout.NORTH);
        searchPanel.add(resultsField,BorderLayout.CENTER);

        recommPanel.add(recommLabel,BorderLayout.NORTH);
        recommPanel.add(recomField,BorderLayout.CENTER);
        recommPanel.add(refreshBtn,BorderLayout.SOUTH);

        buttonPanel.add(downloadBtn);
        buttonPanel.add(rateBtn);

        add(searchPanel,BorderLayout.EAST);
        add(recommPanel,BorderLayout.WEST);
        add(buttonPanel,BorderLayout.SOUTH);

        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> bookList=connection.search(searchQuery.getText());

                bookList.forEach((book)->{
                    searchList.append(book);
                });
                {

                }
            }
        });
    }

}
