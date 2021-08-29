package src.passwordmanager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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

    public void insert(String field, String hash) throws IOException {
        FileWriter fileWriter = new FileWriter(home + field + ".enc");
        fileWriter.write(hash);
        fileWriter.close();
    }

    public void delete(String field) {
        File target = new File(home + field + ".enc");
        target.delete();
    }

    public String readHash(String field) {
        File target = new File(home + field + ".enc");
        try {
            Scanner sc = new Scanner(target);
            String hash = sc.nextLine();
            sc.close();
            return hash;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
