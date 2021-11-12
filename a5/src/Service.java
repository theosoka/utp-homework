public final class Service extends Participant {

    private static int currentID = 0;
    private int serviceID;

    public Service(MessageQueue queue) {
        super(queue);
        serviceID = getCurrentID();
    }

    public void run() {
        while (true) {
            Request request = _queue.dequeueRequest();
            if (request != null) {
                _queue.queueMessage(result(request));
            }
            sleep();
        }
    }

    public long factorial(int n) {
        if (n == 0)
            return 1;
        if (n <= 2) {
            return n;
        }
        return n * factorial(n - 1);
    }

    private Response result(Request request) {
        long res = factorial(request.getVal());
        return new Response(request, res, this);
    }

    public static int getCurrentID() {
        return currentID++;
    }

    @Override
    public String toString() {
        return "service[" + serviceID + "]";
    }
}