package innerClass;

/**
 * Created by Administrator on 2017/4/24.
 */
public class OuterClass {

    public InnerClass innerClass;
    private int a = 58;

    public OuterClass() {
        System.out.println("*********  " + getClass().getSimpleName() + ".Constructor  *********");
        this.innerClass = new InnerClass();
        System.out.println("innerClass.name is " + innerClass.name);
    }

    public void getInner() {
        innerClass.accessPrivate();
    }

    private void show() {
        System.out.println("show");
    }


    class InnerClass{
        private String name = "invalid";

        public InnerClass() {
            System.out.println("*********  " + getClass().getSimpleName() + ".Constructor  *********");
            this.name = "inner";
        }

        private void show() {
            System.out.println("inner show");

        }

        private void accessPrivate() {
            OuterClass.this.show();
            System.out.println("OuterClass.this.a is " + OuterClass.this.a);
        }
    }

    static class NestedClass{
        private String name = "invalid";

        public NestedClass() {
            System.out.println("*********  " + getClass().getSimpleName() + ".Constructor  *********");

            this.name = "nested";
            System.out.println("NestedClass.name is " + this.name);
//            System.out.println("OuterClass.a is " + OuterClass.this.a);//实例化化才能访问外部类的非静态成员
        }
    }
}



