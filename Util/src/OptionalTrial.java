import java.util.Optional;

public class OptionalTrial {

    public static void main(String[] args) {
        OptionalTrial optionalTrial = new OptionalTrial();

    }


    void get(){

        Optional<String> optionalS = Optional.of("abcd");
        String r = optionalS.get();
        System.out.println(r);


        Optional<String> optionalE = Optional.empty();


//        return empty ? Optional.empty() : Optional.of(state);




    }

//    Optional<String> reduce() {
//
//        return ;
//    }
}
