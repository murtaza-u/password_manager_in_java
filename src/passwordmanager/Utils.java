package src.passwordmanager;

import java.io.File;

public class Utils {
    private static String home;

    Utils() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")){
            home = System.getProperty("user.home") + "/AppData/passwordmanager/";
        }
        else if (os.contains("osx") || os.contains("nix") || os.contains("aix") || os.contains("nux")){
            home = System.getProperty("user.home") + "/.local/share/passwordmanager/";
        }

        File homeDir = new File(home);
        if (!homeDir.exists()){
            homeDir.mkdirs();
        }
    }

    public String[] listFields() {
        File homeDir = new File(home);
        return homeDir.list();
    }
}
