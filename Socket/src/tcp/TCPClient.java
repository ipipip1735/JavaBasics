package tcp;


import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Administrator on 2016/8/22.
 */
public class TCPClient {

    private InetAddress ip;
    private Socket socket;


    public TCPClient() {

        try {
            this.ip  = InetAddress.getByName("localhost");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        TCPClient tcpClient = new TCPClient();
        try {
            tcpClient.init();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void init() throws IOException {

        socket = new Socket();
        InetSocketAddress serverISA = new InetSocketAddress(ip, 6666);
        InetSocketAddress clientISA = new InetSocketAddress(ip, 7777);
        socket.bind(clientISA);
        socket.connect(serverISA);


        InputStream in = socket.getInputStream();

//        DataInputStream dataInputStream = new DataInputStream(in);

        System.out.println(in.available());
        BufferedInputStream bis = new BufferedInputStream(in);
//        String k = dataInputStream.readUTF();

        System.out.println(bis.available());






        byte[] bytes = new byte[1024];
        bis.read(bytes);

        System.out.println("start");
        for (byte b : bytes) {
            System.out.println("No." + b);
        }
        String k = new String(bytes, "utf-8");

        System.out.println(k);

        in.close();
//        dataInputStream.close();
        socket.close();

    }



}
