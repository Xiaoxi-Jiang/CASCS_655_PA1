import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ServerThread extends Thread {
    private final Socket socket;
    public ServerThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream is = null;
        try {
            is = socket.getInputStream();
            DataInputStream dis = new DataInputStream(is);
            while (true){
                try {

                    System.out.println(dis.readUTF());
                } catch (Exception e){
                    System.out.println(socket.getRemoteSocketAddress()+" is off-line.");
                    dis.close();
                    socket.close();
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
