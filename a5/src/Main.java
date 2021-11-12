import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        MessageQueue queue = new MessageQueue();
        ArrayList<Requester> requesters = new ArrayList<>();
        ArrayList<Service> services = new ArrayList<>();
        ArrayList<Thread> threadRequesters = new ArrayList<>();
        ArrayList<Thread> threadServices = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            Requester requester = new Requester(queue);
            requesters.add(requester);
            threadRequesters.add(new Thread(requester));
        }
        for (int i = 0; i < 3; i++) {
            Service service = new Service(queue);
            services.add(service);
            threadServices.add(new Thread(service));
        }

        for (int i = 0; i < 3; i++) {
            threadRequesters.get(i).start();
            threadServices.get(i).start();
        }
    }
}
