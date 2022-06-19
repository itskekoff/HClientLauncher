package neko.itskekoffcode.hclient.utils.thread;

public class ThreadUtils {
    public void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception ignored) {}
    }
}
