import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/3/20 11:34.
 */

public class CloneTrial implements Cloneable {
    List<String> list = new ArrayList<>();


    public static void main(String[] args) {

        try {
            CloneTrial cloneTrial = new CloneTrial();
            CloneTrial clone = cloneTrial.clone();


            cloneTrial.list.add("asdf");

            System.out.println(clone.list);


        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }


    }

    @Override
    public CloneTrial clone() throws CloneNotSupportedException {

        CloneTrial cloneTrial = (CloneTrial) super.clone();
        List<String> temp = new ArrayList<>();
        temp.addAll(cloneTrial.list);
        cloneTrial.list = temp;

        return cloneTrial;
    }
}
