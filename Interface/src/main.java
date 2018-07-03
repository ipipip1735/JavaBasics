/**
 * Created by Administrator on 2017/4/24.
 */
public class main {
    public static void main(String[] args) {


//        IMyItf iMyItf = new IMyItf(new MyItf() {
//            @Override
//            public void show() {
//                System.out.println(" impliment  interface Myitf.show() ");
//
//            }
//        });
//        System.out.println(iMyItf.getA());

        IDefaultImp iDefaultImp = new IDefaultImp();
        iDefaultImp.show();


    }
}
