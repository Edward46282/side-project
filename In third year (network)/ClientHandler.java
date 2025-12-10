import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private Socket clientSocket;
    private BufferedReader reader;
    private PrintWriter writer;
    private String clientName;

    /**
     * Constructor to initialize the client handler
     */
    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
        try {
            // Set up input and output streams for the client socket
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); 
            // getInputStream - raw, InputStreamReader - bytes to chars, BufferedReader - efficient reading of lines
            writer = new PrintWriter(clientSocket.getOutputStream(), true); // 'true' for auto-flush
            // getOutputStream - raw, PrintWriter - text formatting to right structure
        } catch (IOException e) {
            closeEverything();
        }
    }

    @Override
    public void run() {
        try {
            // 1. Get name and notify
            writer.println("Enter your name:");
            clientName = reader.readLine();
            System.out.println(clientName + " has joined the chat.");
            ChatServer.broadcastMessage(clientName + " has entered the chat!", this);

            // 2. Main message loop
            String message;
            while ((message = reader.readLine()) != null) {
                String fullMessage = "[" + clientName + "]: " + message;
                System.out.println("Received: " + fullMessage);
                ChatServer.broadcastMessage(fullMessage, this);
            }
        } catch (IOException e) {
            // Disconnected or an error
        } finally {
            // Clean up resources when the client disconnects
            closeEverything();
        }
    }

    /**
     * Sends a message to the specific client.
     * @param message The message to send.
     */
    public void sendMessage(String message) {
        writer.println(message);
    }

    /**
     * Closes all the resources associated with this client.   
     */
    private void closeEverything() {
        ChatServer.removeHandler(this);
        if (clientName != null) {
            ChatServer.broadcastMessage(clientName + " has left the chat.", this);
        }
        try {
            if (reader != null) reader.close();
            if (writer != null) writer.close();
            if (clientSocket != null) clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}