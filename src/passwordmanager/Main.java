package src.passwordmanager;

import src.passwordmanager.cli.CLI;

public class Main {
    public static void main(String[] args) {
        if (args.length > 0) {
            CLI cli = new CLI();
            cli.parse(args);
        }
    }
}
