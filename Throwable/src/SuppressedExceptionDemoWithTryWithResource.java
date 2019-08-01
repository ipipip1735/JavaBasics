/**
 * Created by Administrator on 2017/5/23.
 */
public class SuppressedExceptionDemoWithTryWithResource {
    /**
     * Demonstrating suppressed exceptions using try-with-resources
     */
    public static void main(String[] arguments) throws Exception {
        try (DirtyResource resource = new DirtyResource()) {
            resource.accessResource();
        } catch (RuntimeException e) {
            System.err.println("Exception encountered: " + e.toString());
            final Throwable[] suppressedExceptions = e.getSuppressed();


            final int numSuppressed = suppressedExceptions.length;
            if (numSuppressed > 0)
            {
                System.err.println("tThere are " + numSuppressed + " suppressed exceptions:");
                for (final Throwable exception : suppressedExceptions)
                {
                    System.err.println("tt" + exception.toString());
                }
            }
        }
    }
}



class DirtyResource implements AutoCloseable {
    /**
     * Need to call this method if you want to access this resource
     *
     * @throws RuntimeException no matter how you call this method
     */
    public void accessResource() {
        throw new RuntimeException("I wanted to access this resource. Bad luck. Its dirty resource !!!");
    }

    public void show() {
        throw new RuntimeException("show sub");
    }
    /**
     * The overridden closure method from AutoCloseable interface
     *
     * @throws Exception which is thrown during closure of this dirty resource
     */
    @Override
    public void close() throws Exception {
        show();
        throw new NullPointerException("Remember me. I am your worst nightmare !! I am Null pointer exception !!");
    }
}