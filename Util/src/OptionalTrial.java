import java.util.Optional;

public class OptionalTrial {

    public static void main(String[] args) {
        OptionalTrial optionalTrial = new OptionalTrial();

        optionalTrial.get();

    }


    void get(){

        Optional<Object> optionalS = Optional.ofNullable(null);
        Object o1 = optionalS.get();//为null则抛异常
        Object o2 = optionalS.orElse(new Object());//为null则返回默认值

    }

}
