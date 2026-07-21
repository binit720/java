package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 5000;

    public static void main(String[] args) {
        DatabaseHelper db = new DatabaseHelper("hospital.db");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("=== Hospital Management Server ===");
            System.out.println("Listening on port " + PORT + " ... waiting for clients.");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());
                new Thread(new ClientHandler(clientSocket, db)).start();
            }
        } catch (IOException e) {
            System.out.println("Could not start server on port " + PORT + ". Is it already in use?");
            e.printStackTrace();
        }
    }
}
