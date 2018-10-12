import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/10/12 8:40.
 */

public class GenericTrial {
    public static void main(String[] args) {
        GenericTrial genericTrial = new GenericTrial();
        genericTrial.method();
//        genericTrial.Class();
    }

    private void Class() {

        List<?> list = new ArrayList<>();
//        list.add(new GGC());

    }


    private void method() {


        GGC ggc = new GGC();
//        ggc.<Double>see("ssss");




    }
}


class GGC {
    public <T> Double see(T g) {
        return Double.valueOf(11.1d);
    }
}