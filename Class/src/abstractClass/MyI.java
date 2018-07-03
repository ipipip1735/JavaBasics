package abstractClass;

/**
 * Created by Administrator on 2017/5/12.
 */
public interface MyI {

    void show();
    void one();

    public class MyC{
        public int i = 4;

        public void show() {
//            MyI.one();
            System.out.println(i);
        }
    }
}
