import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {
    private final Socket socket;
    public ServerThread(Socket socket){
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
                    echo(meg);
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

    private void echo(String meg) throws IOException {
        OutputStream os = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        dos.writeUTF(meg);
        dos.flush();
    }
}
