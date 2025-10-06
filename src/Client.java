import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception{
        String host = "csa1.bu.edu";
        int port = 58500;
        Socket socket = new Socket(host, port);
        new ClientReaderThread(socket).start();
        OutputStream os = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        Scanner sc = new Scanner(System.in);
        System.out.println("send messages or type 'exit' to quit");

        while(true) {
            String meg = sc.nextLine();
            if("exit".equals(meg)){
            dos.close();
            socket.close();
            break;
            }
            dos.writeUTF(meg);
            dos.flush();
        }
    }
}
