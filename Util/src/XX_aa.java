import java.util.ListResourceBundle;

/**
 * Created by Administrator on 2020/2/14 21:05.
 */
public class XX_aa extends ListResourceBundle {
    public XX_aa() {
        System.out.println("~~" + getClass().getSimpleName() + ".Constructor~~");

    }

    @Override
    protected Object[][] getContents() {
        System.out.println("~~" + getClass().getSimpleName() + ".getContents~~");
        return new Object[][]{
                {"one", "1111"},
                {"month", "January"}
        };
    }
}
