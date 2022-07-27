package lambdaExpressions.Worker;

public class Main {
    public static void main(String[] args) {
        OnTaskDoneListener listener = System.out::println;
        OnTaskErrorListener errorListener = x -> {
            if (x == 33) {
                System.out.println("Task " + x + " failed");
                return true;
            }
            return false;
        };

        Worker worker = new Worker(listener, errorListener);
        worker.start();
    }
}
