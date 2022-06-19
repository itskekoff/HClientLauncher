package neko.itskekoffcode.hclient.launcher;

import neko.itskekoffcode.hclient.utils.download.DownloadUtils;
import neko.itskekoffcode.hclient.utils.game.GameCheck;
import neko.itskekoffcode.hclient.utils.archive.ZipUtils;
import neko.itskekoffcode.hclient.yaml.Config;
import org.to2mbn.jmccc.auth.OfflineAuthenticator;
import org.to2mbn.jmccc.launch.LaunchException;
import org.to2mbn.jmccc.launch.Launcher;
import org.to2mbn.jmccc.launch.LauncherBuilder;
import org.to2mbn.jmccc.option.LaunchOption;
import org.to2mbn.jmccc.option.MinecraftDirectory;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Map;

public class StartGame {
    public File instance = new File(System.getenv("APPDATA") + "/HClient");

    public void run() throws IOException, LaunchException {
        if (new GameCheck().check()) {
            this.startGame();
        } else {
            new DownloadUtils().downloadFromUrl("https://www.dropbox.com/s/talk3qt4wigm6za/HClient.zip?dl=1", instance.toString() + "/HClient.zip");
            new ZipUtils().unzip(new File(instance + "/HClient.zip"), instance);
            this.startGame();
        }
    }

    public void startGame() throws IOException, LaunchException {
        File cfg = new File(new StartGame().instance + "/config.yml");
        if (!cfg.exists()) {
            try {
                cfg.createNewFile();
                FileWriter cfgWriter = new FileWriter(cfg);
                cfgWriter.write("username: \"itskekoff\"");
                cfgWriter.close();
            } catch (IOException e) {e.printStackTrace();}
        }
        try {
            Map<String, Object> values = new Yaml().load(new FileInputStream(System.getenv("APPDATA") + "/HClient/" + "config.yml"));
            {
                Config.username = (String) values.get("username");
            }
        } catch (FileNotFoundException e) {e.printStackTrace();}
        System.out.printf("[INFO] Username can be edited in: %s\n", instance.getAbsolutePath() + "\\config.yml");
        MinecraftDirectory dir = new MinecraftDirectory(instance);
        Launcher launcher = LauncherBuilder.create()
                .printDebugCommandline(false)
                .nativeFastCheck(true)
                .useDaemonThreads(false)
                .build();
        launcher.launch(new LaunchOption("HClient", new OfflineAuthenticator(Config.username), dir));
        System.out.println("[INFO] Game started");
        System.exit(0);
    }
}
