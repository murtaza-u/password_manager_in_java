package src.passwordmanager.cli;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import src.passwordmanager.utils.Utils;
import src.passwordmanager.utils.DataBreaches;
import src.passwordmanager.crypt.*;

interface Insert {
    public void insert(String field);
}

interface Delete {
    public void delete(String field);
}

interface Show {
    public void listFields();
    public void showPassword(String field);
}

interface Help {
    public void help();
}

public class CLI implements Insert, Delete, Show, Help {
    Utils utils = new Utils();
    Encrypt encrypt = new Encrypt();
    Decrypt decrypt = new Decrypt();
    DataBreaches dataBreaches = new DataBreaches();

    @Override
    public void insert(String field) {
        if (utils.fieldExists(field)) {
            System.out.print("Field exists. Overwrite? (y/N): ");
            Scanner sc = new Scanner(System.in);
            String overwrite = sc.next();
            if (!overwrite.equalsIgnoreCase("y")) {
                return;
            }
        }

        String secretKey = utils.readPassword("Enter secret key");
        String password = utils.readPassword("Password");
        String confirmPassword = utils.readPassword("confirm Password");
        if (!password.equals(confirmPassword)) {
            System.out.println("Passwords donot match");
            return;
        }

        String hash = encrypt.encrypt(password, secretKey);
        if (hash == null)
            return;

        try {
            utils.insert(field, hash);
        } catch(Exception e){
            System.out.println("Failed to insert password: " + e);
        }
    }

    @Override
    public void delete(String field) {
        utils.delete(field);
    }

    @Override
    public void listFields() {
        String[] fields = utils.listFields();
        for (String field : fields) {
            System.out.println(field.replace(".enc", ""));
        }
    }

    @Override
    public void showPassword(String field) {
        String hash = utils.readHash(field);
        String secretKey = utils.readPassword("Enter secret key");
        String decryptedPassword = decrypt.decrypt(hash, secretKey);
        if (decryptedPassword != null)
        System.out.println(decryptedPassword);
    }

    @Override
    public void help() {
        System.out.println(utils.provideHelp());
    }

    public void parse(String[] args) {
        switch(args[0]) {
            case "ls":
                if(args.length == 2) {
                    showPassword(args[1]);
                } else {
                    listFields();
                }
                break;

            case "insert":
                if(args.length == 2)
                    insert(args[1]);
                else
                    System.out.println("Not enough arguments");
                break;

            case "delete":
                if(args.length == 2)
                    delete(args[1]);
                else
                    System.out.println("Not enough arguments");
                break;

            case "help":
                help();
                break;

            case "breach":
                if(args.length == 2) {
                    HashMap<String, String> data = dataBreaches.getData(args[1]);
                    if(data != null) {
                        for (Map.Entry<String, String> row : data.entrySet()) {
                            System.out.println(row.getKey() + ":     "  + row.getValue());
                        }
                    } else {
                        System.out.println("Try using a different search term or check your internet connection and try again");
                    }
                }
                else
                    System.out.println("Not enough arguments");
                break;

            default:
                System.out.println("Wrong arguments");
                break;
        }
    }
}
