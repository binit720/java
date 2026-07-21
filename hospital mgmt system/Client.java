package client;

import java.io.*;
import java.net.Socket;

/**
 * Thin wrapper around a socket connection to the Hospital Management Server.
 */
public class Client {
    private final Socket socket;
    private final BufferedReader in;
    private final PrintWriter out;

    public Client(String host, int port) throws IOException {
        socket = new Socket(host, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    /** Sends one command line and blocks for the single-line response. */
    public synchronized String sendCommand(String command) throws IOException {
        out.println(command);
        String response = in.readLine();
        if (response == null) throw new IOException("Server closed the connection");
        return response;
    }

    public void close() {
        try { socket.close(); } catch (IOException ignored) {}
    }
}
