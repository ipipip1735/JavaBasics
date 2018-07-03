public interface IDefault {

    default void show(){
        System.out.println("original");
    };
}

interface IDefaultOne {

    default void show(){
        System.out.println("original");
    };
}

class IDefaultImp implements IDefault{
    @Override
    public void show(){
        IDefault.super.show();
        System.out.println("class");
    };
}

