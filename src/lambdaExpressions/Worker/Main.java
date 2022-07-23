package lambdaExpressions.Worker;

public class Main {
    public static void main(String[] args) {
        OnTaskDoneListener listener = System.out::println;
        OnTaskErrorListener errorListener = x -> !(x == 33);
        Worker worker = new Worker(listener, errorListener);
        worker.start();
    }
}
