package client;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
    private static String serverAddress = "127.0.0.1"; // The server's IP address
    private static int PORT = 8100; // The server's port
    private static Socket socket = null;
    private static PrintWriter out;
    private static BufferedReader in;
    private String username="Adi";

    public String getUsername() {
        return username;
    }

    public Client() throws IOException {
        socket = new Socket(serverAddress, PORT);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public boolean login(String name, String password) {
        String request = "login \"" + name + "\" \"" + password + "\"";
        out.println(request);
        try {
            String response = in.readLine();
            username=name;
            return response.equals("true");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;

    }

    public boolean register(String name, String mail, String password) {
        String request = "register \"" + name + "\" \"" + password + "\" \"" + mail + "\"";
        out.println(request);
        try {
            String response = in.readLine();
            return response.equals("true");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;


    }


    public String download(String book_id,String path) {
        out.println("download " + book_id);
        try {
            String response = in.readLine();
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "No result.";

    }

    public boolean rate(String book_id, int rating) {
        out.println("rate " + book_id + " " + Integer.toString(rating));
        try {
            String response = in.readLine();
            return response.equals("true");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<String> recommend() {
        ArrayList<String> bookList = new ArrayList<String>();
        out.println("recomm");
        try {
            String numString = in.readLine();
            int num;
            String response = "";
            String book;
            num = Integer.parseInt(numString);
            for (int i = 0; i < num; i++) {
                book = in.readLine();
                response += book + "\n";
                bookList.add(book);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return bookList;


    }

    public ArrayList<String> search(String query) {
        ArrayList<String> bookList = new ArrayList<String>();
        out.println("search " + query);
        try {
            String numString = in.readLine();
            int num;
            String response = "";
            String book;
            num = Integer.parseInt(numString);
            for (int i = 0; i < num; i++) {
                book = in.readLine();
                bookList.add(book);
                response += book + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bookList;

    }
    public String getInfo(String book_name)
    {
        out.println("getinfo "+book_name);
        try{
            String info=in.readLine();
            return info;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
    public String getFollowers()
    {
        out.println("getfollowers");
        try{
            String info=in.readLine();
            return info;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
    public String getFollowing()
    {
        out.println("getfollowing");
        try{
            String info=in.readLine();
            return info;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }




}
