package innerClass;

/**
 * Created by Administrator on 2017/4/24.
 */
public class InnerClass{

    public static void main(String[] args) {
//        InnerClass innerClass = new InnerClass();
//        innerClass.see();

        InnerClass.MyInner myInner = new InnerClass().new MyInner();
        myInner.show();
    }

    private class MyInner {


        private void show() {
            System.out.println("inner   show");
            see();
            InnerClass.this.see();
        }
    }

    private void see() {
        MyInner myInner = this.new MyInner();

        System.out.println("outer see");
    }

}



