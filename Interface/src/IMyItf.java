/**
 * Created by Administrator on 2017/5/4.
 */
public class IMyItf<A extends MyItf> {

    A a =null;
    public IMyItf(A a) {
        this.a = a;
    }

    public A getA() {
        return this.a;
    }
}