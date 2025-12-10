import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.List;

public class ChatServer {

    private static final int PORT = 12345; // good port number for custom applications
    public static List<ClientHandler> clientHandlers = new CopyOnWriteArrayList<>(); // array list designed for multi-threaded use and writes

    public static void main(String[] args) {
        System.out.println("Starting server on port " + PORT + "...");
        
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getRemoteSocketAddress());
                ClientHandler handler = new ClientHandler(clientSocket);
                clientHandlers.add(handler);
                new Thread(handler).start();
            }

        } catch (IOException e) {
            System.err.println("Error starting server: " + e.getMessage());
        }
    }

    /**
     * Broadcasts a message to all connected clients except the sender.
     * @param message The message to send.
     * @param sender The handler who sent the message.
     */
    public static void broadcastMessage(String message, ClientHandler sender) {
        for (ClientHandler handler : clientHandlers) {
            // Send to everyone EXCEPT the sender
            if (handler != sender) {
                handler.sendMessage(message);
            }
        }
    }

    /**
     * Removes a disconnected client from the list.
     * @param handler The handler to remove.
     */
    public static void removeHandler(ClientHandler handler) {
        clientHandlers.remove(handler);
        System.out.println("Client disconnected. Active clients: " + clientHandlers.size());
    }
}