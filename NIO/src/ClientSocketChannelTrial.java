import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * Created by Administrator on 2019/10/31 13:25.
 */
public class ClientSocketChannelTrial {

    public static void main(String[] args) {
        ClientSocketChannelTrial client = new ClientSocketChannelTrial();
        client.noBlock();
    }

    private void noBlock() {


        try {
            InetAddress inetAddress = InetAddress.getByName("192.168.0.126");

            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(true);
            socketChannel.setOption(StandardSocketOptions.SO_KEEPALIVE, true);


            socketChannel.connect(new InetSocketAddress(inetAddress, 5454));


            socketChannel.write(ByteBuffer.wrap("request".getBytes()));

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (socketChannel.read(buffer) != -1) {

                System.out.println("reading");
                String response = Charset.defaultCharset()
                        .newDecoder()
                        .decode(buffer.flip())
                        .toString();
                System.out.println(response);

                buffer.clear();
            }

            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
