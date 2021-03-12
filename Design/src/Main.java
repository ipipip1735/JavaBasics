public class Main {

    public static void main(String[] args) {

        Person person = new Person.Builder()
                .setAge(11)
                .setName("one")
                .build();

        System.out.println("person = " + person);
    }

}
