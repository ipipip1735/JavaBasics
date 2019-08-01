/**
 * Created by Administrator on 2017/5/23.
 */
public class SuppressedExceptions {
    public static void main(String[] args) throws Exception {
        try {
            callTryFinallyBlock();
        } catch (Exception e) {
            e.printStackTrace();
            for(Throwable t: e.getSuppressed())
            {
                t.printStackTrace();
            }
        }
    }

    private static void callTryFinallyBlock() throws Exception {
        Throwable t = null;
        try
        {
            throw new TryException();
        }
        catch (Exception e) {
            t = e;
            e.printStackTrace();
        }
        finally
        {
            FinallyException fEx = new FinallyException();
            if(t != null)fEx.addSuppressed(t);
            throw fEx;
        }
    }
}

class TryException extends Exception {
}

class FinallyException extends Exception {
}