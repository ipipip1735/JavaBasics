import innerClass.OuterClass;

/**
 * Created by Administrator on 2018/10/13.
 */
public class LoopTrial {
    public static void main(String[] args) {
        LoopTrial loopTrial = new LoopTrial();
        loopTrial.switchs();
    }

    private void switchs() {

        switch ("a") {
            case "a":
                System.out.println("a");
            case "b":
                System.out.println("b");
            case "c":
                System.out.println("c");
            case "d":
                System.out.println("d");
            default:
                System.out.println("default");
        }

    }
}
