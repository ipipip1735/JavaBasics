import java.io.*;

/**
 * Created by Administrator on 2016/10/9.
 */
public class DataInput {

    public static void main(String[] args) throws IOException {

        DataInput dataInput = new DataInput();
        dataInput.quizRead();

    }



    public void quizRead() throws IOException {

        File file = new File("IO/resource/a");



        FileOutputStream fileOutputStream = new FileOutputStream(file);
        DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
        dataOutputStream.writeUTF("卡1卡y");


        FileInputStream fileInputStream = new FileInputStream(file);
        DataInputStream dataInputStream = new DataInputStream(fileInputStream);
        System.out.println(dataInputStream.available());
        System.out.println(dataInputStream.readUTF());


    }
}
