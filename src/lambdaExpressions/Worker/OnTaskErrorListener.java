package lambdaExpressions.Worker;

@FunctionalInterface
public interface OnTaskErrorListener {
    void onError(String message);
}
