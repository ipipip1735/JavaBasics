import java.io.*;
import java.lang.instrument.Instrumentation;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Administrator on 2018/11/20.
 */
public class ObjectIO {
    public static void main(String[] args) {
        ObjectIO objectIO = new ObjectIO();


        objectIO.output();
        objectIO.input();
//        objectIO.inputSkip();
    }

    private void inputSkip() {
        String s = "Hello World!"; //skip()仅对基本类型有效，不能用于Object

        try {
            // create a new file with an ObjectOutputStream
            FileOutputStream out = new FileOutputStream("test.txt");
            ObjectOutputStream oout = new ObjectOutputStream(out);

            // write something in the file
            oout.writeUTF(s);
            oout.writeUTF("This is an example");
            oout.flush();

            // create an ObjectInputStream for the file we created before
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("test.txt"));

            // skip 4 bytes and read the rest
            ois.skipBytes(4);
            for (int i = 0; i < ois.available() - 4; i++) {
                System.out.print("" + (char) ois.readByte());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private void input() {
        try {
            if (!Files.exists(Paths.get("IO/res/object"))) return;
            ObjectInputStream objectInputStream =
                    new ObjectInputStream(new FileInputStream("IO/res/object"));

            System.out.println("available is " + objectInputStream.available());
            Person person = (Person) objectInputStream.readObject();
            Pet pet = (Pet) objectInputStream.readObject();
            objectInputStream.close();

            person.show();
            pet.see();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void output() {
        try {

            ObjectOutputStream objectOutputStream =
                    new ObjectOutputStream(new FileOutputStream("IO/res/object"));
            Person person = new Person(12, "bob");
            objectOutputStream.writeObject(person);
            Pet pet = new Pet(13l, "tom", "apple");
            objectOutputStream.writeObject(pet);

            objectOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

class Person implements Serializable {
    public int age;

    public String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public void show() {
        System.out.println(age);
        System.out.println(name);
    }
}

class Pet implements Serializable {
    public long age;
    public String name;
    public String food;

    public Pet(long age, String name, String food) {
        this.age = age;
        this.name = name;
        this.food = food;
    }

    public void see() {
        System.out.println(age);
        System.out.println(name);
        System.out.println(food);
    }
}