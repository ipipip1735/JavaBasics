import java.util.HashMap;

/**
 * Created by Administrator on 2018/9/15.
 */
public class MapTrial {
    public static void main(String[] args) {
        MapTrial mapTrial = new MapTrial();
        mapTrial.base();
    }

    private void base() {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("one", "11111");
        hashMap.put("one", "22222");
        System.out.println(hashMap);


    }
}
