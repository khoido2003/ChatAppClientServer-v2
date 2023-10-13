# Chat Application README

This is a simple chat application implemented in Java. It consists of a server and client components that allow multiple clients to chat with each other. Each client has its own graphical user interface (GUI) window.

## Getting Started

1. Clone or download this repository to your local machine.

2. Open the project in your preferred Java development environment.

3. Compile and run the `Server` class. This will start the chat server on port 9999.

4. Compile and run the `ChatClientApp` class. This class manages and launches multiple client instances. By default, it creates and runs three different clients, each with its own username.

5. Each client will have its GUI window where you can enter messages and chat with other clients.

## Usage

- To send a message, type your message in the input field and click the "Send" button.

- You can also use commands such as "/nick" to change your username and "/quit" to exit the chat.

## Customizing

- You can modify the server's port by changing the port number in the `Server` class.

- To add more users, modify the `ChatClientApp` class to create additional instances of the `Client` class with unique usernames.

## Authors

- Khoi Do

## License

This project is open source. Feel free to use.
