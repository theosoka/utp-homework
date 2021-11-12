abstract class Participant implements Runnable {

    protected final MessageQueue _queue;

    private static  final  int SLEEP_TIME = 1500;


    Participant(MessageQueue queue) {
        _queue = queue;
    }


    public void sleep() {
        try {
            Thread.sleep(SLEEP_TIME);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
