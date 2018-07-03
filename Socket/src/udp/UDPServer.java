package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created by Administrator on 2016/8/24.
 */
public class UDPServer {

    public static void main(String[] args) {
        UDPServer server = new UDPServer();

        try {
            server.receive();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void receive() throws IOException {

        byte[] bytes = new byte[1024];
        DatagramPacket dp = new DatagramPacket(bytes, 1024);
        DatagramSocket ds = new DatagramSocket(5656);
        ds.receive(dp);
        System.out.println(dp.getLength());


    }
}
