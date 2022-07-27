package lambdaExpressions.Worker;

@FunctionalInterface
public interface OnTaskErrorListener {
    boolean onError(int position);
}
