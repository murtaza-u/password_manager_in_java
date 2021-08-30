package src.passwordmanager;

import src.passwordmanager.cli.CLI;
import src.passwordmanager.gui.GUI;

public class Main {
    public static void main(String[] args) {
        if (args.length > 0) {
            CLI cli = new CLI();
            cli.parse(args);
        } else {
            new GUI();
        }
    }
}
