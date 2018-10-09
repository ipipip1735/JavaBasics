/**
 * Created by Administrator on 2018/10/9 11:57.
 */

public class EnumTrial {
    public static void main(String[] args) {
        EnumTrial enumTrial = new EnumTrial();
        enumTrial.base();

    }

    private void base() {
//        Words name = Words.CC;
//        System.out.println(name);


//        Person person = Person.Tom;
//        System.out.println(person);
//        System.out.println(person.age);
//        System.out.println(person.gender);


        for (Person p : Person.values()){
            System.out.println(p + "|" + p.age + ", " +  p.gender);
        }


    }
}


enum Words {AA,BB,CC,DD}
enum Person {
    Jone("Female", 15), Tom("Male", 20), Jack("Male", 23);

    String gender;
    int age;

    Person(String gender, int age) {
        this.gender = gender;
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }
}

