package neko.itskekoffcode.hclient.utils.archive;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import java.io.File;

public class ZipUtils {
    public void unzip(File zipFile, File destination) {
        System.out.println("[INFO] Unpacking client & starting game");
        try {
            ZipFile zipFile1 = new ZipFile(zipFile);
            zipFile1.extractAll(destination.toString());
        } catch (ZipException e) { e.printStackTrace();}
        zipFile.delete();
    }
}
