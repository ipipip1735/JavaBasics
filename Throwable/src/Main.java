import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.instrument.IllegalClassFormatException;

/**
 * Created by Administrator on 2017/5/22.
 */
public class Main {


    public static void main(String[] args) throws Exception {
        System.out.println("  -- main --");


        try {
            throw new IOException("IOE");
        } catch (IOException e) {
            Exception exception = new Exception();
            exception.addSuppressed(e);
            throw exception;
        }



    }


    public void read() throws IOException {

        try (
                FileReader fileReaderA = new FileReader("a.txt");
                FileReader fileReaderB = new FileReader("b.txt");
                FileReader fileReaderC = new FileReader("c.txt")

        ) {
            fileReaderA.read();
            fileReaderB.read();
            fileReaderC.read();

        }
    }


//
//    public void one() throws Exception {
//
//        try {
//            throw new RuntimeException("kkk");
//        } catch (RuntimeException e) {
//            throw new Exception("lllll");
//        }
//    }
//
//    public void two() throws MyThrowable {
//        try {
//            one();
//        } catch (MyThrowable e) {
//            throw new MyThrowable("two", e);
//        }
//    }
//
//    public void three() throws MyThrowable {
//        try {
//            two();
//        } catch (MyThrowable e) {
//            throw new MyThrowable("three", e);
//        }
//    }


    public void sk() {
        st();
        System.out.println("sk");

    }


    public void st() {
        System.out.println("st");
        printError();
    }


    public void printError() {


//        new MyThrowable();
        try {
            throw new MyThrowable("my");
        } catch (MyThrowable myThrowable) {
            StackTraceElement[] elements1 = myThrowable.getStackTrace();
            System.out.println(elements1.length);

            for (StackTraceElement e :
                    elements1) {
                System.out.println("===========");
                System.out.println(e.getLineNumber());
                System.out.println(e.getClassName() + " --> " + e.getMethodName());
                System.out.println("===========");
            }

            try {
                throw new MyThrowableOne("one", myThrowable);
            } catch (MyThrowableOne myThrowableOne) {
                StackTraceElement[] elements2 = myThrowableOne.getStackTrace();
                for (StackTraceElement e :
                        elements2) {
                    System.out.println("===========");
                    System.out.println(e.getLineNumber());
                    System.out.println(e.getClassName() + " --> " + e.getMethodName());
                    System.out.println("===========");
                }

            }
        }
    }
}

//
//class OneException extends MyThrowable {
//}
//
//class TwoException extends MyThrowable {
//}
//
//class ThreeException extends MyThrowable {
//}


class MyFileReader extends FileReader {

    public MyFileReader(String fileName) throws IOException {
        super(fileName);
    }

    @Override
    public void close() throws IOException {
        super.close();
        System.out.println("close MyFileReader");
    }
}

