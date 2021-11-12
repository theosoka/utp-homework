public class Requester extends Participant {
    private static int currentID = 0;
    private int requesterID;
    private int val;

    public Requester(MessageQueue queue){
        super(queue);
        requesterID = getCurrentID();
    }

    public void run(){
        System.out.println("running " + this);
        while (true){
            _queue.queueMessage(createRequest());
            while (true) {
                sleep();
                if (consumeResponse())
                    break;
            }
        }
    }

    public static int getCurrentID() {
        return currentID++;
    }

    public Request createRequest() {
        val = (int) (Math.random() * 9);
        return new Request(this, Priority.getRandomPriority(), val);
    }

    public boolean consumeResponse(){
        Response response = _queue.dequeueResponse(this);
        if (response == null)
            return false;
        System.out.println(this + " " + response + " " + val + "! = " + response.get_result() + " consumed");
        return true;
    }

    @Override
    public String toString() {
        return "Requester[" + requesterID + "]";
    }
}

