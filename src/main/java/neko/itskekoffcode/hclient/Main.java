package neko.itskekoffcode.hclient;

import neko.itskekoffcode.hclient.launcher.StartGame;
import org.to2mbn.jmccc.launch.LaunchException;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) throws LaunchException, IOException {
        checkConsole();
        System.out.println("[INFO] HClient launcher starts");
        new StartGame().run();

    }
    public static void checkConsole() {
        if (System.console() == null) {
            try {
                String path = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath();
                String decodedPath = URLDecoder.decode(path, String.valueOf(StandardCharsets.UTF_8)).substring(1).replace("/", "\\");
                (new ProcessBuilder(new String[]{"cmd", "/c", "start", "java", "-Xmx2G", "-server", "-jar", "\"" + decodedPath + "\""})).start();
            } catch (Exception ignored) {}

            System.exit(0);
        }
    }
}