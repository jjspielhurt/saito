package client.view;

import client.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    //    JLabel searchLabel=new JLabel("Search:");
    JPanel searchUpperPanel = new JPanel();
    JPanel searchPanel = new JPanel();
    JTextField searchQuery = new JTextField(20);
    JButton searchBtn = new JButton("Search");

    JPanel userPanel=new JPanel();
    JTextArea nameArea=new JTextArea();
    JTextArea followersArea=new JTextArea();
    JTextArea followingArea=new JTextArea();

    DefaultListModel<String> model = new DefaultListModel<>();
    JList<String> searchList = new JList(model);
    JScrollPane resultsField = new JScrollPane(searchList);
    JPanel centralPanel = new JPanel();


    JPanel recommPanel = new JPanel();
    JLabel recommLabel = new JLabel("Recommendations:");
    DefaultListModel<String> model2 = new DefaultListModel<>();
    JList<String> recomList = new JList(model2);
    JScrollPane recomField = new JScrollPane(recomList);

    JTextArea infoArea=new JTextArea();


    JButton refreshBtn = new JButton("Refresh");


    JPanel buttonPanel = new JPanel();
    JButton downloadBtn = new JButton("Download");
    JButton rateBtn = new JButton("Rate");
    JButton infoBtn = new JButton("Details");

    JFileChooser fileChooser = new JFileChooser();

    Client connection;

    public MainFrame(Client connection) {
        super("Saito");
        this.connection = connection;
        init();
    }

    private void init() {
        setLayout(new BorderLayout());
        this.setSize(1000, 800);
        this.setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        resultsField.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        recomField.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        searchPanel.setLayout(new BorderLayout());
        recommPanel.setLayout(new BorderLayout());
        searchUpperPanel.setLayout(new BorderLayout());
        searchUpperPanel.add(searchQuery, BorderLayout.WEST);
        searchUpperPanel.add(searchBtn, BorderLayout.EAST);

        searchPanel.add(searchUpperPanel, BorderLayout.NORTH);
        searchPanel.add(resultsField, BorderLayout.CENTER);

        recommPanel.add(recommLabel, BorderLayout.NORTH);
        recommPanel.add(recomField, BorderLayout.CENTER);
        recommPanel.add(refreshBtn, BorderLayout.SOUTH);

        centralPanel.setLayout(new BorderLayout());
        centralPanel.add(infoBtn, BorderLayout.NORTH);
        centralPanel.add(infoArea, BorderLayout.CENTER);
        buttonPanel.add(downloadBtn);
        buttonPanel.add(rateBtn);

        userPanel.add(followersArea,BorderLayout.EAST);
        userPanel.add(nameArea,BorderLayout.CENTER);
        userPanel.add(followingArea,BorderLayout.WEST);
        nameArea.setText(connection.getUsername());
        followersArea.setText("Followers:"+connection.getFollowers());
        followingArea.setText("Following:"+connection.getFollowing());
        add(userPanel,BorderLayout.NORTH);
        add(searchPanel, BorderLayout.EAST);
        add(recommPanel, BorderLayout.WEST);
        add(buttonPanel, BorderLayout.SOUTH);
        add(centralPanel, BorderLayout.CENTER);
        infoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String info = connection.getInfo(searchList.getSelectedValue());
                infoArea.setText("");
                infoArea.setText(info);
            }
        });
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> bookList = connection.search(searchQuery.getText());

                bookList.forEach((book) -> {
                    model.addElement(book);
                });

            }
        });
        refreshBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> bookList = connection.recommend();

                bookList.forEach((book) -> {
                    model2.addElement(book);
                });

            }
        });
        downloadBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String path = chooseDownload();
                String book_content = connection.download(searchList.getSelectedValue(), path);


            }
        });
        rateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createRateFrame();

            }
        });
    }

    String chooseDownload() {
        fileChooser.showSaveDialog(null);
        return fileChooser.getSelectedFile().toString();
    }

    public void createRateFrame() {
        setVisible(false);
        dispose();
        new RateFrame(connection).setVisible(true);
    }

}
