import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2017/5/22.
 */
public class MyThrowableOne extends Throwable {

    public MyThrowableOne() {
        super();
        System.out.println("  ------------  MyThrowableOne   MyThrowableOne0  ------------  ");
    }

    public MyThrowableOne(String message) {
        super(message);
        System.out.println("  ------------  MyThrowableOne   MyThrowableOne1  ------------  ");

    }

    public MyThrowableOne(String message, Throwable cause) {
        super(message, cause);
        System.out.println("  ------------  MyThrowableOne   MyThrowableOne2  ------------  ");
    }

    public MyThrowableOne(Throwable cause) {
        super(cause);
        System.out.println("  ------------  MyThrowableOne   MyThrowableOne3  ------------  ");
    }

    protected MyThrowableOne(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        System.out.println("  ------------  MyThrowableOne   MyThrowableOne4  ------------  ");
    }

    @Override
    public String getMessage() {
        System.out.println("  ------------  MyThrowableOne   getMessage  ------------  ");
        return super.getMessage();
    }

    @Override
    public String getLocalizedMessage() {
        System.out.println("  ------------  MyThrowableOne   getLocalizedMessage  ------------  ");
        return super.getLocalizedMessage();
    }

    @Override
    public synchronized Throwable getCause() {
        System.out.println("  ------------  MyThrowableOne   getCause  ------------  ");
        return super.getCause();
    }

    @Override
    public synchronized Throwable initCause(Throwable cause) {
        System.out.println("  ------------  MyThrowableOne   initCause  ------------  ");
        return super.initCause(cause);
    }

    @Override
    public String toString() {
        System.out.println("  ------------  MyThrowableOne   toString  ------------  ");
        return super.toString();
    }

    @Override
    public void printStackTrace() {
        System.out.println("  ------------  MyThrowableOne   printStackTrace  ------------  ");
        super.printStackTrace();
    }

    @Override
    public void printStackTrace(PrintStream s) {
        System.out.println("  ------------  MyThrowableOne   printStackTrace1  ------------  ");
        super.printStackTrace(s);
    }

    @Override
    public void printStackTrace(PrintWriter s) {
        System.out.println("  ------------  MyThrowableOne   printStackTrace2  ------------  ");
        super.printStackTrace(s);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        System.out.println("  ------------  MyThrowableOne   fillInStackTrace3  ------------  ");
        return super.fillInStackTrace();
    }

    @Override
    public StackTraceElement[] getStackTrace() {
        System.out.println("  ------------  MyThrowableOne   getStackTrace  ------------  ");
        return super.getStackTrace();
    }

    @Override
    public void setStackTrace(StackTraceElement[] stackTrace) {
        super.setStackTrace(stackTrace);
        System.out.println("  ------------  MyThrowableOne   setStackTrace  ------------  ");
    }
}
