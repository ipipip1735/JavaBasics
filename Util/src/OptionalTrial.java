import java.time.Instant;
import java.util.Optional;

public class OptionalTrial {

    public static void main(String[] args) {
        OptionalTrial optionalTrial = new OptionalTrial();

        optionalTrial.get();
//        optionalTrial.ifPresent();
//        optionalTrial.filter();

    }

    private void filter() {
        Optional<String> optionalS = Optional.of("aaaa");
        boolean b = optionalS.filter(v -> v=="ok").isPresent();
        System.out.println(b);
    }

    private void ifPresent() {

        long timestamp = Instant.now().getEpochSecond();//获取时间戳
        Optional<String> optional = Optional.ofNullable(timestamp%2==0?"aaa":null);

        optional.ifPresent(name -> {
            System.out.println("~ifPresent~");
            System.out.println(name);
        });





    }

    void get(){

        long timestamp = Instant.now().getEpochSecond();//获取时间戳
        Optional<String> optional = Optional.ofNullable(timestamp%2==0?"aaa":null);

        System.out.println(optional.orElse("--"));//为null则返回默认值
        System.out.println(optional.get());//为null则抛异常

    }

}
