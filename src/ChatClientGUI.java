import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;

public class ChatClientGUI {
    private JFrame frame;
    private JPanel panel;
    private JScrollPane chatScrollPane;
    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;

    private PrintWriter out;
    private String username;

    public ChatClientGUI(PrintWriter out, String username) {
        this.out = out;
        this.username = username;
        createUI();
    }
    private void createUI() {
        frame = new JFrame("Chat Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);  // Increased height for the chat area

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatScrollPane = new JScrollPane(chatArea);
        chatScrollPane.setPreferredSize(new Dimension(400, 300)); // Set preferred size for chat area

        inputField = new JTextField(30);
        inputField.setPreferredSize(new Dimension(400, 25)); // Set preferred size for input field

        sendButton = new JButton("Send");
        sendButton.setMaximumSize(new Dimension(400, 25));

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(sendButton);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));  // Use a Y_AXIS layout
        panel.add(chatScrollPane);  // Chat area at the top
        panel.add(inputField);     // Input field in the middle
        panel.add(buttonPanel);    // Centered "Send" button at the bottom

        frame.add(panel);
        frame.setVisible(true);
    }

    public void appendMessage(String message) {
        chatArea.append(message + "\n");
    }

    public String getInputText() {
        return inputField.getText();
    }

    public void clearInput() {
        inputField.setText("");
    }

    public void sendMessage() {
        String message = getInputText();
        if (!message.isEmpty()) {
            out.println(message);
            clearInput();
        }
    }
}
