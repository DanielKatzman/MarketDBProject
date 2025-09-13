package shon_daniel;

import java.util.ListIterator;

public class ConcreteListIteratorAdapter implements TargetAdapter {

    private ListIterator<String> concreteListIterator;

    public ConcreteListIteratorAdapter(ListIterator<String> concreteListIterator){
        this.concreteListIterator = concreteListIterator;
    }

    @Override
    public boolean myHasNext() {
        return concreteListIterator.hasNext();
    }

    @Override
    public String myNext() {
        return concreteListIterator.next();
    }

    @Override
    public boolean myHasPrevious() {
        return concreteListIterator.hasPrevious();
    }

    @Override
    public String myPrevious() {
        return concreteListIterator.previous();
    }

}
