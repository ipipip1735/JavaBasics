import java.util.List;

/**
 * Created by Administrator on 2018/10/4.
 */

public class Family {

    Integer addr;
    List<String> person;

    public Family(Integer add, List<String> person) {
        this.addr = add;
        this.person = person;
    }

    public Integer getAddr() {
        return addr;
    }

    public void setAddr(Integer addr) {
        this.addr = addr;
    }

    public List<String> getPerson() {
        return person;
    }

    public void setPerson(List<String> person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Family{" +
                "addr=" + addr +
                ", person=" + person +
                '}';
    }
}
