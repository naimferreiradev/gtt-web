package utils;

public class StepLogger {
    private static final ThreadLocal<String> stepAtual = new ThreadLocal<>();

    public static void set(String step) {
        stepAtual.set(step);
    }

    public static String get() {
        return stepAtual.get();
    }
}
