package server;

import java.io.*;
import java.net.*;

/**
 * Runs on its own thread per connected client.
 * Text protocol (fields separated by '~'):
 *   ADD~name~age~gender~disease~doctor~phone~address
 *   UPDATE~id~name~age~gender~disease~doctor~phone~address
 *   DELETE~id
 *   VIEW_ALL
 * Responses: "OK~..." or "ERROR~message"
 */
public class ClientHandler implements Runnable {
    private final Socket socket;
    private final DatabaseHelper db;

    public ClientHandler(Socket socket, DatabaseHelper db) {
        this.socket = socket;
        this.db = db;
    }

    @Override
    public void run() {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            String line;
            while ((line = in.readLine()) != null) {
                out.println(processCommand(line));
            }
        } catch (IOException e) {
            System.out.println("Client disconnected: " + socket.getInetAddress());
        } finally {
            try { socket.close(); } catch (IOException ignored) {}
        }
    }

    private String processCommand(String line) {
        try {
            String[] p = line.split("~", -1);
            switch (p[0]) {
                case "ADD":
                    return db.addPatient(p[1], Integer.parseInt(p[2]), p[3], p[4], p[5], p[6], p[7]);
                case "UPDATE":
                    return db.updatePatient(Integer.parseInt(p[1]), p[2], Integer.parseInt(p[3]), p[4], p[5], p[6], p[7], p[8]);
                case "DELETE":
                    return db.deletePatient(Integer.parseInt(p[1]));
                case "VIEW_ALL":
                    return db.getAllPatients();
                default:
                    return "ERROR~Unknown command: " + p[0];
            }
        } catch (Exception e) {
            return "ERROR~Bad request (" + e.getMessage() + ")";
        }
    }
}
