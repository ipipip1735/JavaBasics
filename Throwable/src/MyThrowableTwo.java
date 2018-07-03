import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2017/5/22.
 */
public class MyThrowableTwo extends Throwable {

    public MyThrowableTwo() {
        super();
        System.out.println("  ------------  MyThrowableTwo   MyThrowableTwo0  ------------  ");
    }

    public MyThrowableTwo(String message) {
        super(message);
        System.out.println("  ------------  MyThrowableTwo   MyThrowableTwo1  ------------  ");

    }

    public MyThrowableTwo(String message, Throwable cause) {
        super(message, cause);
        System.out.println("  ------------  MyThrowableTwo   MyThrowableTwo2  ------------  ");
    }

    public MyThrowableTwo(Throwable cause) {
        super(cause);
        System.out.println("  ------------  MyThrowableTwo   MyThrowableTwo3  ------------  ");
    }

    protected MyThrowableTwo(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        System.out.println("  ------------  MyThrowableTwo   MyThrowableTwo4  ------------  ");
    }

    @Override
    public String getMessage() {
        System.out.println("  ------------  MyThrowableTwo   getMessage  ------------  ");
        return super.getMessage();
    }

    @Override
    public String getLocalizedMessage() {
        System.out.println("  ------------  MyThrowableTwo   getLocalizedMessage  ------------  ");
        return super.getLocalizedMessage();
    }

    @Override
    public synchronized Throwable getCause() {
        System.out.println("  ------------  MyThrowableTwo   getCause  ------------  ");
        return super.getCause();
    }

    @Override
    public synchronized Throwable initCause(Throwable cause) {
        System.out.println("  ------------  MyThrowableTwo   initCause  ------------  ");
        return super.initCause(cause);
    }

    @Override
    public String toString() {
        System.out.println("  ------------  MyThrowableTwo   toString  ------------  ");
        return super.toString();
    }

    @Override
    public void printStackTrace() {
        System.out.println("  ------------  MyThrowableTwo   printStackTrace  ------------  ");
        super.printStackTrace();
    }

    @Override
    public void printStackTrace(PrintStream s) {
        System.out.println("  ------------  MyThrowableTwo   printStackTrace1  ------------  ");
        super.printStackTrace(s);
    }

    @Override
    public void printStackTrace(PrintWriter s) {
        System.out.println("  ------------  MyThrowableTwo   printStackTrace2  ------------  ");
        super.printStackTrace(s);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        System.out.println("  ------------  MyThrowableTwo   fillInStackTrace3  ------------  ");
        return super.fillInStackTrace();
    }

    @Override
    public StackTraceElement[] getStackTrace() {
        System.out.println("  ------------  MyThrowableTwo   getStackTrace  ------------  ");
        return super.getStackTrace();
    }

    @Override
    public void setStackTrace(StackTraceElement[] stackTrace) {
        super.setStackTrace(stackTrace);
        System.out.println("  ------------  MyThrowableTwo   setStackTrace  ------------  ");
    }
}
