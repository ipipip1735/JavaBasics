package udp;

import java.io.IOException;
import java.net.*;

/**
 * Created by Administrator on 2016/8/24.
 */
public class UDPClient {

    public static void main(String[] args) {
        UDPClient client = new UDPClient();

        try {
            client.sendInfo();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendInfo() throws IOException {

        byte[] data = new byte[1024];
        data[0]=97;

        InetAddress ip = InetAddress.getByName("localhost");
        DatagramPacket dp = new DatagramPacket(data,data.length,ip,5656);
        DatagramSocket ds = new DatagramSocket(54321);
//        ds.send(dp);



    }
}
