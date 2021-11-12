import java.util.ArrayList;
import java.util.List;

public class MyContainer<TElement extends IAggregable<TElement, TAggregateResult> & IDeeplyCloneable<TElement>, TAggregateResult> implements IContainer<TElement, TAggregateResult> {

    private List<TElement> list = new ArrayList<>();

    MyContainer(){

    }

    public void add(TElement elem)
    {
        list.add(elem);
    }

    public TElement get(int index)
    {
        if (index >= list.size() || index < 0) {
            System.out.println("wrong index");
            return null;
        }
        return list.get(index);
    }

    @Override
    public List<TElement> elements() {
        if (list.isEmpty())
            return null;
        return list;
    }

    @Override
    public TAggregateResult aggregateAllElements() {
        TAggregateResult res = null;
        for (TElement elem : list) {
            res = elem.aggregate(res);
        }
        return res;
    }

    @Override
    public TElement cloneElementAtIndex(int index) {
        if (index >= list.size() || index < 0) {
            System.out.println("wrong index");
            return null;
        }
        return list.get(index).deepClone();
    }
}
