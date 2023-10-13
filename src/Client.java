import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Runnable {
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private boolean done;

    private ChatClientGUI gui;
    private String username;

    public Client(String username, String serverAddress, int serverPort) {
        this.username = username;

        try {
            client = new Socket(serverAddress, serverPort);
            out = new PrintWriter(client.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            gui = new ChatClientGUI(out, username); // Pass the username to the GUI
        } catch (IOException e) {
            shutdown();
        }
    }

    @Override
    public void run() {
        try {
            InputHandler inHandler = new InputHandler();
            Thread t = new Thread(inHandler);
            t.start();

            String inMessage;
            while ((inMessage = in.readLine()) != null) {
                gui.appendMessage(inMessage);
            }
        } catch (IOException e) {
            shutdown();
        }
    }

    public void shutdown() {
        done = true;
        try {
            in.close();
            out.close();
            if (!client.isClosed())
                client.close();
        } catch (IOException e) {
            // IGNORE
        }
    }

    class InputHandler implements Runnable {
        @Override
        public void run() {
            try {
                BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
                while (!done) {
                    String message = inReader.readLine();
                    if (message.equals("/quit")) {
                        out.println(message);
                        inReader.close();
                        shutdown();
                    } else {
                        out.println(message);
                    }
                }
            } catch (IOException e) {
                shutdown();
            }
        }
    }

    public static void main(String[] args) {
        // Create and run two different clients with different usernames
        Client client1 = new Client("User1", "127.0.0.1", 9999);
        Client client2 = new Client("User2", "127.0.0.1", 9999);
        Client client3 = new Client("User3", "127.0.0.1", 9999);
        Client client4 = new Client("User4", "127.0.0.1", 9999);

        Thread thread1 = new Thread(client1);
        Thread thread2 = new Thread(client2);
        Thread thread3 = new Thread(client3);
        Thread thread4 = new Thread(client4);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}
