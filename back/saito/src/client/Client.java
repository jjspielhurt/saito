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

    public Client() throws IOException {
        socket = new Socket(serverAddress, PORT);
        out= new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
    public boolean login(String name,String password)
    {
        String request="login \""+name+"\" \""+password+"\"";
        out.println(request);
        try {
            String  response=in.readLine();
            return response.equals("true");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;

    }
    public boolean register(String name,String mail,String password)
    {
        String request="register \""+name+"\" \""+password+"\" \""+mail+"\"";
        out.println(request);
        try {
            String  response=in.readLine();
            return response.equals("true");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;


    }


    public String download(String book_id)
    {
        out.println("download "+book_id);
        try {
            String  response=in.readLine();
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "No result.";

    }
    public boolean rate(String book_id,double rating)
    {
        out.println("rate "+book_id+" "+Double.toString(rating));
        try {
            String  response=in.readLine();
            return response.equals("true");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    public String recommend()
    {
        out.println("recomm");
        try {
            String  numString=in.readLine();
            int num;
            String response="";
            String book;
            num=Integer.parseInt(numString);
            for(int i=0;i<num;i++) {
                book=in.readLine();
                response +=book +"\n";
            }
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "No recommendations.";


    }
    public ArrayList<String> search(String query)
    {
        ArrayList<String> bookList=new ArrayList<String>();
        out.println("search "+query);
        try {
            String  numString=in.readLine();
            int num;
            String response="";
            String book;
            num=Integer.parseInt(numString);
            for(int i=0;i<num;i++) {
                book=in.readLine();
                bookList.add(book);
                response +=book +"\n";
            }
            return bookList;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bookList;

    }


}
