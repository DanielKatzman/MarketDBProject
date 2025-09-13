package shon_daniel;

import java.util.Objects;

public class Action2 extends Action implements Observer{

    public Action2() {
        setId("default_id_action2");
    }

    public Action2(String id) {
        setId(id);
    }

    @Override
    public void update(MarketManager subject) {
        System.out.println("Action2 got: " + subject.getMsg());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Action2 action2 = (Action2) o;
        return Objects.equals(getId(), action2.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
