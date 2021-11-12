
import java.util.PriorityQueue;

public class MessageQueue {

    private final PriorityQueue<Message> priorityQueue;

    public MessageQueue() {
        priorityQueue = new PriorityQueue<>();
    }

    public synchronized void queueMessage(Message message){
        priorityQueue.add(message);
    }

    public synchronized Request dequeueRequest() {
       if (!(priorityQueue.peek() instanceof Request))
           return null;
       return (Request) priorityQueue.poll();
    }

    public synchronized Response dequeueResponse(Requester requester) {
        Response response = null;
        Message message = priorityQueue.peek();
        if (message instanceof Response && message.get_requester() == requester)
        {
            response = (Response) priorityQueue.poll();
        }
        return response;
    }

}