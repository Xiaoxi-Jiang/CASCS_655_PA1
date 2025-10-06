import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ServerThread extends Thread {
    private final Socket socket;
    public ServerThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            BufferedWriter out = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));

            String line;
            while ((line = in.readLine()) != null) {  // the client need to type '\n' to send
                System.out.println("message from " + socket.getRemoteSocketAddress() + ": " + line);
                out.write(line);
                out.write('\n');
                out.flush();
            }
            in.close();
            out.close();
            socket.close();
        }  catch (IOException e) {
            System.out.println(socket.getRemoteSocketAddress()+" I/O error: " + e.getMessage());
            try {
                socket.close();
            } catch (IOException ignore) {}
        }
    }
}
