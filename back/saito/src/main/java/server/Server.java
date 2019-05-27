package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 8100;
    private ServerSocket serverSocket = null;
    private static volatile boolean running = false;

    public  static final Object networkLock = new Object();
    public static  User user =new User();

    public static void setRunning(boolean b)
    {
        running=b;
    }

    public void init() throws IOException {
        serverSocket = new ServerSocket(PORT);
        running=true;
    }
    public void waitForClients() throws IOException {
        while (running) {
            System.out.println("Waiting for a client ...");
            Socket socket = serverSocket.accept();
            // Execute the client's request in a new thread
            if(running) new ServerThread(socket).start();
        }
    }
    public void stop() throws IOException{
        this.running=false;
        serverSocket.close();
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.init();
        server.waitForClients();
        server.stop();
    }
}
