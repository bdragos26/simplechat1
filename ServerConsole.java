import common.ChatIF;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ServerConsole implements ChatIF {
    final public static int DEFAULT_PORT = 5555;
    EchoServer server;

    public ServerConsole(int port) {
        server = new EchoServer(port);
        try {
            server.listen(); // Start listening for connections
        } catch (Exception e) {
            System.out.println("Error: Can't setup server connection!");
        }
    }

    public void accept() {
        try {
            BufferedReader fromConsole = new BufferedReader(new InputStreamReader(System.in));
            String message;

            while (true) {
                message = fromConsole.readLine();
                server.handleMessageFromServerConsole("SERVER MSG> " + message);
            }
        } catch (Exception ex) {
            System.out.println("Unexpected error while reading from server console!");
        }
    }

    @Override
    public void display(String message) {
        System.out.println(message);
    }

    public static void main(String[] args) {
//        int port = 5555;
        int port = 0;

        try {
            port = Integer.parseInt(args[1]);
        }
        catch(ArrayIndexOutOfBoundsException e)  {
            port = DEFAULT_PORT;
        }

        ServerConsole serverConsole = new ServerConsole(port);
        serverConsole.accept(); // Start accepting input from server console
    }
}
