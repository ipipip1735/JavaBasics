import org.junit.Test;

/**
 * Created by Administrator on 2017/5/21.
 */
public class JUnitUse {

    public static void main (String[] args) {

        JUnitUse jUnitUse = new JUnitUse();
//        jUnitUse.myNum(1, 2, 3);
        jUnitUse.a();




//        ScopeTest st = new ScopeTest();

//        System.out.println(st.i);
    }

    public void showArray(String[] arr) {
//        for (int i = 0; i < 4; i++) {
//            System.out.println(arr[i]);
//        }
    }

    @Test
    public void assignArray() {
        System.out.println("dd");
//        showArray({"a", "b"});

    }

    @Test
    public void myNum(int... ints) {
        System.out.println("dd");
        System.out.println(ints.length);
        System.out.println(ints[0]);
        System.out.println(ints[1]);
        System.out.println(ints[2]);

    }


    public boolean a() {


        return b() || c();
    }

    public boolean b() {
        System.out.println("b");
        return false;

    }

    public boolean c() {
        System.out.println("c");
        return false;
    }


}
