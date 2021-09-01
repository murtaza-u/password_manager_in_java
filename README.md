> Password Manager written in JAVA

![password manager](assets/2.png)

![password manager](assets/1.png)

# Features
- Uses AES encryption
- Can output to stout(opens up a whole new world of possibilities)

# Installation
```bash
$ git clone https://github.com/Murtaza-Udaipurwala/passwordmanager_java
$ cd password_manager_in_java/
```

- Use jar file from build/
```bash
$ cp build/passwordmanager.jar .
$ java -jar passwordmanager.jar
```

### or

- compile from source
```bash
$ javac -cp :./lib:./lib/json-simple-1.1.1.jar src/passwordmanager/Main.java src/passwordmanager/gui/*.java
$ jar cmf MANIFEST.MF passwordmanager.jar src/passwordmanager/* lib/*.jar
```

- Export _JAVA_OPTIONS environment variable (make `java swing` uses your desktop environment theme)
```bash
export _JAVA_OPTIONS="-Dawt.useSystemAAFontSettings=on -Dswing.aatext=true -Dswing.defaultlaf=com.sun.java.swing.plaf.gtk.GTKLookAndFeel -Dswing.crossplatformlaf=com.sun.java.swing.plaf.gtk.GTKLookAndFeel ${_JAVA_OPTIONS}"
```

# Usage
```bash
$ java -jar passwordmanager.jar help # to get the help menu
```

# Example
1. To launch GUI
```bash
$ java -jar passwordmanager.jar
```

2. List all the entries
```bash
$ java -jar passwordmanager.jar ls
google
email
```

3. Insert a new entry
- The `secret key` used to encrypt and decrypt passwords. Keep it same for all the entries to make it act like a `master password`.
```bash
$ java -jar passwordmanager.jar insert google # here google is the field name
Enter secret key:
Password:
confirm Password:
```

4. Delete an entry
```bash
$ java -jar passwordmanager.jar delete google
```

# Use with external script
- The following shell script takes output of passwordmanager and pipes it into dmenu
- The user can then select the field and passwordmanager will prompt them for the secretKey
- If entered correctly, the decrypted password will be copied to user's clipboard
```bash
list="$(java 2>/dev/null -jar passwordmanager.jar ls)"
selected="$(echo "$list" | dmenu -i)"
java 2>/dev/null -jar passwordmanager.jar ls -gui "$selected" | xclip -selection clipboard
```

# TODO
- [x] Improve error handling
- [x] implement a gui using `swing`
- [x] get response from haveIbeenpwned api in a seperate thread.
- [ ] Test it on windows OS(Currently only tested on GNU/Linux)

# Disclaimer
- This is a university project. It solely exists to provide a learning experience.
- There might be potential lurking bugs.
- Please donot use this for anything but a reference. Murtaza Udaipurwala shall not be held responsible for your loses.
