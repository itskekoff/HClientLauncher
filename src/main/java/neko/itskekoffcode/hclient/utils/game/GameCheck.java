package neko.itskekoffcode.hclient.utils.game;

import neko.itskekoffcode.hclient.launcher.StartGame;

import java.io.File;

public class GameCheck {
    private File instance = new StartGame().instance;
    public boolean check() {
        if (!instance.exists()) {
            instance.mkdirs();
            return false;
        }
        return true;
    }
}
