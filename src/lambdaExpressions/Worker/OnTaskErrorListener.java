package lambdaExpressions.Worker;

@FunctionalInterface
public interface OnTaskErrorListener {
    boolean OnError(int position);
}
