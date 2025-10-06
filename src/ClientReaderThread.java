import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ClientReaderThread extends Thread{
    private final Socket socket;
    public ClientReaderThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream is;
        try {
            is = socket.getInputStream();
            DataInputStream dis = new DataInputStream(is);
            while (true){
                try {
                    String meg = dis.readUTF();
                    System.out.println(meg);
                }catch (Exception e){
                    dis.close();
                    socket.close();
                    break;
                }
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
