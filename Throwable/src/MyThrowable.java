import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2017/5/22.
 */
public class MyThrowable extends Throwable {

    public MyThrowable() {
//        super();
        System.out.println("  ------------  MyThrowable   MyThrowable0  ------------  ");
    }

    public MyThrowable(String message) {
        super(message);
        System.out.println("  ------------  MyThrowable   MyThrowable1  ------------  ");

    }

    public MyThrowable(String message, Throwable cause) {
        super(message, cause);
        System.out.println("  ------------  MyThrowable   MyThrowable2  ------------  ");
    }

    public MyThrowable(Throwable cause) {
        super(cause);
        System.out.println("  ------------  MyThrowable   MyThrowable3  ------------  ");
    }

    protected MyThrowable(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        System.out.println("  ------------  MyThrowable   MyThrowable4  ------------  ");
    }

    @Override
    public String getMessage() {
        System.out.println("  ------------  MyThrowable   getMessage  ------------  ");
        return super.getMessage();
    }

    @Override
    public String getLocalizedMessage() {
        System.out.println("  ------------  MyThrowable   getLocalizedMessage  ------------  ");
        return super.getLocalizedMessage();
    }

    @Override
    public synchronized Throwable getCause() {
        System.out.println("  ------------  MyThrowable   getCause  ------------  ");
        return super.getCause();
    }

    @Override
    public synchronized Throwable initCause(Throwable cause) {
        System.out.println("  ------------  MyThrowable   initCause  ------------  ");
        return super.initCause(cause);
    }

    @Override
    public String toString() {
        System.out.println("  ------------  MyThrowable   toString  ------------  ");
        return super.toString();
    }

    @Override
    public void printStackTrace() {
        System.out.println("  ------------  MyThrowable   printStackTrace  ------------  ");
        super.printStackTrace();
    }

    @Override
    public void printStackTrace(PrintStream s) {
        System.out.println("  ------------  MyThrowable   printStackTrace1  ------------  ");
        super.printStackTrace(s);
    }

    @Override
    public void printStackTrace(PrintWriter s) {
        System.out.println("  ------------  MyThrowable   printStackTrace2  ------------  ");
        super.printStackTrace(s);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        System.out.println("  ------------  MyThrowable   fillInStackTrace3  ------------  ");
        return super.fillInStackTrace();
    }

    @Override
    public StackTraceElement[] getStackTrace() {
        System.out.println("  ------------  MyThrowable   getStackTrace  ------------  ");
        return super.getStackTrace();
    }

    @Override
    public void setStackTrace(StackTraceElement[] stackTrace) {
        super.setStackTrace(stackTrace);
        System.out.println("  ------------  MyThrowable   setStackTrace  ------------  ");
    }
}
