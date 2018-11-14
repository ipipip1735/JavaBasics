package tcp;

import java.io.*;
import java.net.*;

/**
 * Created by Administrator on 2016/8/22.
 */
public class TCPServer {

    public static void main(String[] args) {

        TCPServer tcpServer = new TCPServer();
//        tcpServer.create();
//        tcpServer.isBind();
        tcpServer.listen();
//        tcpServer.http();

    }

    private void http() {

//        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//
//        String s;
//        while ((s = in.readLine()) != null) {
//            System.out.println(s);
//            if (s.isEmpty()) {
//                break;
//            }
//        }


//        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//        out.write("HTTP/1.0 200 OK\r\n");
//        out.write("Date: Fri, 31 Dec 1999 23:59:59 GMT\r\n");
//        out.write("Server: Apache/0.8.4\r\n");
//        out.write("Content-Type: text/html\r\n");
//        out.write("Content-Length: 59\r\n");
//        out.write("Expires: Sat, 01 Jan 2000 00:59:59 GMT\r\n");
//        out.write("Last-modified: Fri, 09 Aug 1996 14:21:40 GMT\r\n");
//        out.write("\r\n");
//        out.write("<html><body>111</body></html>");
//
//        out.close();
//        socket.close();
//        serverSocket.close();
//
//
//
//        out.write("<P>Ceci est une page d'exemple.</P>");
//
//        on ferme les flux.
//        System.err.println("Connexion avec le client terminée");
//        in.close();


//        System.out.println(socket.getInetAddress().toString());
//        OutputStream os = socket.getOutputStream();
//
//        String errorMessage = "HTTP/1.1 200 OK\r\n" +
//                "Content-Type:application/binary\r\n" +
//                "Content-Length: 26\r\n" +
//                "\r\n" +
//                "<h1>File Not Found111</h1>\r\n";
//
//        DataOutputStream dos = new DataOutputStream(os);
//        dos.writeUTF(errorMessage);


//        PrintStream writer = new PrintStream(socket.getOutputStream());
//        writer.println("HTTP/1.0 200 OK"); 返回应答消息,并结束应答
//        writer.println("Date: Fri, 31 Dec 1999 23:59:59 GMT"); 返回应答消息,并结束应答
//        writer.println("Server: Apache/0.8.4"); 返回应答消息,并结束应答
//        writer.println("Content-Type: text/html"); 返回应答消息,并结束应答
//        writer.println("Content-Length: 29"); 返回应答消息,并结束应答
//        writer.println("Expires: Sat, 01 Jan 2000 00:59:59 GMT"); 返回应答消息,并结束应答
//        writer.println("Last-modified: Fri, 09 Aug 1996 14:21:40 GMT");
//        writer.println(""); 根据 HTTP 协议, 空行将结束头信息
//
//
//        FileInputStream fis = new FileInputStream(fileToSend);
//        byte[] buf = new byte[fis.available()];
//        fis.read(buf);
//
//        writer.write("<html><body>111</body></html>".getBytes());
//        writer.flush();
//        writer.close();
//
//        fis.close();
//        socket.close();
//        serverSocket.close();
//
//
//        dos.close();
//        os.close();

    }


    private void isBind() {

        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println(serverSocket.isBound());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void create() {

        try {
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress("localhost", 5656));
            serverSocket.accept();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listen() {

        try {
//            init();
            initWhile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void init() throws IOException {

        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(InetAddress.getByName("192.168.0.102"), 6666));
        Socket socket = serverSocket.accept();

        OutputStream os = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        dos.writeUTF("kkk卡kk");

        dos.close();
        os.close();
        socket.close();
        serverSocket.close();
    }


    public void initWhile() throws IOException {
        InetAddress ip = InetAddress.getByName("192.168.0.102");
        InetSocketAddress isa = new InetSocketAddress(ip, 6666);

        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(isa);


        while (true) {
            Socket socket = serverSocket.accept();
            OutputStream os = socket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            dos.writeUTF("kkk卡kk");

            dos.close();
            os.close();
            socket.close();
//            serverSocket.close();
        }
//        serverSocket.close();
    }


}
