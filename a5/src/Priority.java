import java.util.Random;

public enum Priority {
    LOW, NORMAL, HIGH;

    public static Priority getRandomPriority() {
        Priority[] priorities = Priority.values();
        return priorities[new Random().nextInt(priorities.length)];
    }
}
