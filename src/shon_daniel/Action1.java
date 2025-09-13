package shon_daniel;

import java.util.Objects;

public class Action1 extends Action implements Observer{

    public Action1() {
        setId("default_id_action1");
    }

    public Action1(String id) {
        setId(id);
    }

    @Override
    public void update(MarketManager subject) {
        System.out.println("Action1 got: " + subject.getMsg());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Action1 action1 = (Action1) o;
        return Objects.equals(getId(), action1.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
