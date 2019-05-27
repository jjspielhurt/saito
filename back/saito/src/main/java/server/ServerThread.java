package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread {
    private Socket socket = null;
    boolean logged = false;
    int waitSec = 600;
    User user = new User();
    PrintWriter out;
    BufferedReader in;


    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public boolean register(String name, String password, String mail) {
        user.setUsername(name);
        user.setPassword(password);
        user.setMail(mail);
        String response = "User " + user.username + " added successsfully";
        System.out.println(response);
        return user.register();
    }

    public boolean login(String name, String password) {


        user.setUsername(name);
        user.setPassword(password);
        if (user.login()) {
            logged = true;
            return true;
        }
        return false;

    }

    public void search(String query) {
        Search search=new Search(query);
        ArrayList<String> bookList=search.getBookList();
        int numBooks;
        numBooks=search.numBooks;
        System.out.println("NUm:"+numBooks);
        out.println(numBooks);
        out.flush();
        bookList.forEach((book)->{
            out.println(book);
            out.flush();
        });

    }

    public void recomm() {
        user.recommend();
        ArrayList<String> bookList=user.getBookList();
        int numBooks=user.getNumRecommendations();
        System.out.println("num_recom:"+numBooks);
        out.println(numBooks);
        out.flush();
        bookList.forEach((book)->{
            out.println(book);
            out.flush();
        });
    }

    public String download(String book) {
        return "a";
    }

    public boolean rate(int book,int rating) {
        return user.userRate.rate(user.user_id,book,rating);
    }


    public void run() {
        try {
            boolean exit = false;
            in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());


            // Get the request from the input stream: client → server
            do {


                String request = in.readLine();
                // Send the response to the output stream: server → client
                System.out.println(request);
                System.out.flush();
                String command = request.split(" ")[0];
                String argument;
                String[] temp;
                temp = request.split(" ");
                ArrayList<String> arguments = new ArrayList<String>();
                for (int i = 1; i < temp.length; i++) {
                    arguments.add(temp[i]);
                }
//                System.out.println("Command:"+command);
//                System.out.println("Username:"+arguments.get(0).replaceAll("\"",""));
//                System.out.println("Password:"+arguments.get(1).replaceAll("\"",""));

                switch (command) {
                    /**
                     input:register "username" "password" "mail"
                     output: true/false
                     **/
                    case "register":
                        out.println(register(arguments.get(0).replaceAll("\"", ""), arguments.get(1).replaceAll("\"", ""), arguments.get(2).replaceAll("\"", "")));
                        out.flush();
                        break;
                    /**
                     input:login "username" "password"
                     output: true/false
                     **/
                    case "login":
                        if (!logged) {

                            out.println(login(arguments.get(0).replaceAll("\"", ""), arguments.get(1).replaceAll("\"", "")));
                            out.flush();
                        } else {
                            out.println("Already logged in.");
                            out.flush();
                        }
                        break;
                    /**
                     input: download book_id
                     output: num_messages
                     book as string
                     **/
                    case "download":
                        if (logged) {
                            argument = request.split(" ", 2)[1];
                            out.println(download(arguments.get(0)));
                            out.flush();
                        } else {
                            out.println("Log in first.");
                            out.flush();
                        }
                        break;
                    /**
                     input:search query
                     output: num_books
                     books
                     **/
                    case "search":
                        if (logged) {
                            argument = request.split(" ", 2)[1];
                            search(argument);
                        } else {
                            out.println("Log in first.");
                            out.flush();
                        }
                        break;
                    /**
                     input:rate book_id rating
                     output: true/false
                     **/
                    case "rate":
                        if (logged) {
                            out.println(rate(Integer.parseInt(arguments.get(0)),Integer.parseInt(arguments.get(1))));
                            out.flush();
                        } else {
                            out.println("Log in first.");
                            out.flush();
                        }
                        break;
                    /**
                     input:recomm
                     output: num_books
                     books
                     **/
                    case "recomm":
                        if (logged) {
                            recomm();
                        } else {
                            out.println("Log in first.");
                            out.flush();
                        }

                        break;
                    case "exit":
                        exit = true;
                        break;
                    default:
                        out.println("Invalid command.");
                        out.flush();

                }
            } while (!exit);

        } catch (IOException e) {
            System.err.println("Communication error... " + e);
        } finally {
            try {
                socket.close(); // or use try-with-resources
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }
}