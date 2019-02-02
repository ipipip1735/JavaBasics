package innerClass;

/**
 * Created by Administrator on 2017/4/24.
 */
public class OuterClass {

    InnerClass innerClass;

    public OuterClass() {
        System.out.println("*********  " + getClass().getSimpleName() + ".Constructor  *********");
        this.innerClass = new InnerClass();
    }

    public static void main(String[] args) {
        OuterClass outerClass = new OuterClass();

        //实例化内部类
//        OuterClass.InnerClass innerClass = outerClass.new InnerClass();
//        System.out.println(innerClass.name);


        //实例化静态内部类
//        OuterClass.NestedClass nestedClass = new OuterClass.NestedClass();
//        System.out.println(nestedClass.name);


    }


    public void getInner() {
        innerClass.accessPrivate();
    }

    private void show() {
        System.out.println("show");
    }


    class InnerClass{
        public String name = "invalid";

        public InnerClass() {
            System.out.println("*********  " + getClass().getSimpleName() + ".Constructor  *********");
            this.name = "inner";
        }

        private void show() {
            System.out.println("inner show");

        }

        private void accessPrivate() {
            OuterClass.this.show();
        }
    }

    static class NestedClass{
        private String name = "invalid";

        public NestedClass() {
            System.out.println("*********  " + getClass().getSimpleName() + ".Constructor  *********");

            this.name = "nested";
        }
    }
}



