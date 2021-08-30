package src.passwordmanager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Utils utils = new Utils();
        Crypt crypt = new Crypt();

        if(args.length > 0) {
            switch(args[0]) {
                case "ls":
                    if(args.length == 2) {
                        String field = args[1];
                        String hash = utils.readHash(field);
                        String secretKey = utils.readPassword("Enter secret key(master password)");
                        String decryptedPassword = crypt.decrypt(hash, secretKey);
                        if (decryptedPassword != null)
                            System.out.println(decryptedPassword);
                    } else {
                        String[] fields = utils.listFields();
                        for (String field : fields) {
                            System.out.println(field.replace(".enc", ""));
                        }
                    }
                    break;

                case "insert":
                    String field = args[1];
                    if (utils.fieldExists(field)) {
                        System.out.print("Field exists. Overwrite? (y/N): ");
                        Scanner sc = new Scanner(System.in);
                        String overwrite = sc.next();
                        if (!overwrite.equalsIgnoreCase("y")) {
                            break;
                        }
                    }

                    String secretKey = utils.readPassword("Enter secret key(master password)");
                    String password = utils.readPassword("Password");
                    String confirmPassword = utils.readPassword("confirm Password");
                    if (!password.equals(confirmPassword)) {
                        System.out.println("Passwords donot match");
                        break;
                    }

                    String hash = crypt.encrypt(password, secretKey);
                    if (hash == null)
                        break;

                    try {
                        utils.insert(field, hash);
                    } catch(Exception e){
                        System.out.println("Failed to insert password: " + e);
                    }
                    break;

                case "delete":
                    utils.delete(args[1]);
                    break;

                case "help":
                    System.out.println(utils.provideHelp());
                    break;

                default:
                    System.out.println("Wrong arguments");
                    break;
            }
        }
    }
}
