public class Request extends Message {

    private final int _val;

    public Request(Requester requester, Priority p, int val) {
        super(requester, Priority.getRandomPriority());
        _val = val;
    }

    public int getVal() {
        return _val;
    }

    @Override
    public String toString() {
        return super.toString() + ", (" + _val +") ";
    }
}

