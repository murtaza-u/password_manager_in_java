---
title: Password Manager in Java
author: Murtaza Udaipurwala
date: 2021-10-09
styles:
  style: gruvbox-dark
  margin:
    top: 3
    bottom: 1
  padding:
    top: 3
    bottom: 3

---

---
# Project status: βοΈ completed
# Github: https://github.com/Murtaza-Udaipurwala/password_manager_in_java
# Lines of code: around 1000
# Encryption algorithm used: πAES encryption
# Project structure:
```bash
βββ assets
βΒ Β  βββ copy.png
βΒ Β  βββ edit.png
βΒ Β  βββ logo.png
βΒ Β  βββ refresh.png
βββ cli
βΒ Β  βββ CLI.java
βββ crypt
βΒ Β  βββ Decrypt.java
βΒ Β  βββ Encrypt.java
βΒ Β  βββ SecretKey.java
βββ gui
βΒ Β  βββ AddNewEntry.java
βΒ Β  βββ ChangePassword.java
βΒ Β  βββ CheckDataBreaches.java
βΒ Β  βββ GUI.java
βΒ Β  βββ ListAll.java
βΒ Β  βββ SecretKey.java
βββ Main.java
βββ utils
    βββ DataBreaches.java
    βββ Utils.java
```

---
# Extensibility (Yes it is extensible!)π₯
- My password manager can output to stdout
- Integrated well with programs like `dmenu`

```bash
#!/bin/sh
list="$(java 2>/dev/null -jar passwordmanager.jar ls | dmenu -i)"
java -jar passwordmanager.jar ls -gui "$field" | xclip -selection clipboard
```

---
# Demonstration
1. GUI
2. CLI
3. An example of extensibility

---
# Thankyou
