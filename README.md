> Password Manager written in JAVA

# Features
- Uses AES encryption
- Can output to stout(opens up a whole new world of possibilities)

# Installation
- Download jar file from build/

### or

- compile from source
```bash
$ git clone https://github.com/Murtaza-Udaipurwala/passwordmanager_java
$ cd passwordmanager_java/
$ javac src/passwordmanager/*.java
$ jar cvfe passwordmanager.jar src.passwordmanager.Main src/passwordmanager/*
```

# Usage
```bash
java -jar passwordmanager.jar help # to get the help menu
```

# Example
1. List all the entries
```bash
$ java -jar passwordmanager.jar ls # lists all the entries
google
email
...
...
```

2. Insert a new entry
- The `secret key` used to encrypt and decrypt passwords. Keep it same for all the entries to make it act like a `master password`.
```bash
$ java -jar passwordmanager.jar insert google # here google is the field name
Enter secret key:
Password:
confirm Password:
```

3. Delete an entry
```bash
$ java -jar passwordmanager.jar delete google
```

# TODO
- [ ] Improve error handling
- [ ] implement a gui using `swing`
- [ ] Test it on windows OS(Currently only tested on GNU/Linux)

# Note
- This is a university project. It solely exists to provide a learning experience.
- Please donot use this for anything but a reference. Murtaza Udaipurwala shall not be held responsible for your loses.
