package src.passwordmanager;

import java.io.File;

public class Utils {
    private static String home = "/home/murtaza/.local/share/passwordmanager";

    public String[] listFields() {
        File homeDir = new File(home);
        return homeDir.list();
    }
}
