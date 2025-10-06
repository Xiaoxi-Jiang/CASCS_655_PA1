import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        String host;
        int port;
        if (args.length >= 2) {
            host = args[0];
            port = Integer.parseInt(args[1]);
        } else {
            System.out.println("Please enter the hostname:");
            host = sc.nextLine();
            System.out.println("Please enter the port number:");
            port = Integer.parseInt(sc.nextLine());
        }

        Socket socket = new Socket(host, port);
        new ClientReaderThread(socket).start();

        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
        System.out.println("Server connected, send messages or type 'exit' to quit");

        while(true) {
            String msg = sc.nextLine();
            if("exit".equalsIgnoreCase(msg)){
                out.close();
                socket.close();
                break;
            }
            out.write(msg);
            out.write('\n');
            out.flush();
        }
    }
}
