import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int PORT = 12345;
    private Socket socket; // information associated with the address and a port
    private BufferedReader serverReader;
    private PrintWriter serverWriter;
    private Scanner consoleScanner;

    public ChatClient() {
        consoleScanner = new Scanner(System.in);
        try {
            // 1. Connect to the server
            socket = new Socket(SERVER_ADDRESS, PORT);
            System.out.println("Connected to the server at " + SERVER_ADDRESS + ":" + PORT);

            // 2. Set up I/O streams
            serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            serverWriter = new PrintWriter(socket.getOutputStream(), true); 

            // 3. Start a new thread to constantly listen for messages from the server
            new Thread(new ServerListener()).start();

            // 4. Main thread handles sending messages
            sendMessages();
            
        } catch (IOException e) {
            System.err.println("Could not connect to the server: " + e.getMessage());
            closeEverything();
        }
    }

    /**
     * Inner class to handle messages received from the server in the thread
     */
    private class ServerListener implements Runnable {
        @Override
        public void run() {
            try {
                String message;
                // Read messages from the server until the connection is closed
                while ((message = serverReader.readLine()) != null) {
                    System.out.println(message);
                }
            } catch (IOException e) {
                System.out.println("Connection closed by the server.");
            } finally {
                closeEverything();
            }
        }
    }

    /**
     * Main thread loop to read user input and send messages to the server (Sender Thread).
     */
    private void sendMessages() {
        String userInput;
        while (socket != null && !socket.isClosed()) {
            userInput = consoleScanner.nextLine();
            if (userInput.equalsIgnoreCase("/quit")) {
                break;
            }
            serverWriter.println(userInput); // Send the user input to the server
        }
        System.out.println("Exiting chat...");
        closeEverything();
    }

    private void closeEverything() {
        try {
            if (consoleScanner != null) consoleScanner.close();
            if (serverReader != null) serverReader.close();
            if (serverWriter != null) serverWriter.close();
            if (socket != null && !socket.isClosed()) socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Its purpose is to keep the process alive
     * @param args
     */
    public static void main(String[] args) {
        new ChatClient();
    }
}