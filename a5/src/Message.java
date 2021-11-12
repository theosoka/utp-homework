import java.time.LocalDateTime;

public abstract class Message implements Comparable<Message>{
    private static int currentId = 0;
    private final int messageID;

    private final LocalDateTime time;
    private final Requester _requester;
    private final Priority _priority;

    public synchronized int getCurrentId() {
        return currentId++;
    }

    public int getMessageID() {
        return messageID;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public Requester get_requester() {
        return _requester;
    }

    public Priority get_priority() {
        return _priority;
    }

    Message(Requester requester, Priority priority) {
        _requester = requester;
        _priority = priority;
        messageID = getCurrentId();
        time = LocalDateTime.now();
    }

    @Override
    public int compareTo(Message m) {
        if (m == null)
            return -1;
        int res = get_priority().compareTo(m.get_priority());
        if (res == 0) {
            res = getTime().compareTo(m.getTime());
        }
        return res;
    }

    @Override
    public String toString() {
        return "message[" + messageID + "] " + _priority + " ";
    }
}
