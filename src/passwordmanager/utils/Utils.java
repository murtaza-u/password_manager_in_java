package src.passwordmanager.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.Console;

public class Utils {
    private static String home;

    public Utils() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")){
            home = Paths.get(System.getProperty("user.home"), "AppData", "passwordmanager").toString();
        }
        else if (os.contains("osx") || os.contains("nix") || os.contains("aix") || os.contains("nux")){
            home = Paths.get(System.getProperty("user.home"), ".local", "share", "passwordmanager").toString();
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
        FileWriter fileWriter = new FileWriter(Paths.get(home, field + ".enc").toString());
        fileWriter.write(hash);
        fileWriter.close();
    }

    public void delete(String field) {
        File target = new File(Paths.get(home, field + ".enc").toString());
        target.delete();
    }

    public String readHash(String field) {
        File target = new File(Paths.get(home, field + ".enc").toString());
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

    public String readPassword(String prompt) {
        try {
            // creates a console object
            Console cnsl = System.console();

            if (cnsl == null) {
                System.out.println("No console available");
                return null;
            }

            // read password into the char array
            char[] pwd = cnsl.readPassword(prompt + ": ");

            return new String(pwd);
        } catch(Exception e) {
            System.out.print("An error occured " + e);
            return null;
        }
    }

    public boolean fieldExists(String field) {
        File homeDir = new File(home);
        for (String file : homeDir.list()) {
            if (file.equals(field + ".enc")) {
                return true;
            }
        }
        return false;
    }

    public String provideHelp() {
        return String.format("%s\n%s\n%s\n%s\n%s",
            "# Usage java -jar passwordmanager.jar <options> <arguments>",
            "help\tDisplays this help menu",
            "insert\tMust be followed by the field name. Inserts a new password entry",
            "delete\tMust be followed by the field name. Deletes a password entry",
            "ls\tIf followed my the field name decrypts the corresponding password; otherwise lists all the entries");
    }
}
