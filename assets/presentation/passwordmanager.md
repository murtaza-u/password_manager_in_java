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
# Project status: ✔️ completed
# Github: https://github.com/Murtaza-Udaipurwala/password_manager_in_java
# Lines of code: around 1000
# Encryption algorithm used: 🔐AES encryption
# Project structure:
```bash
├── assets
│   ├── copy.png
│   ├── edit.png
│   ├── logo.png
│   └── refresh.png
├── cli
│   └── CLI.java
├── crypt
│   ├── Decrypt.java
│   ├── Encrypt.java
│   └── SecretKey.java
├── gui
│   ├── AddNewEntry.java
│   ├── ChangePassword.java
│   ├── CheckDataBreaches.java
│   ├── GUI.java
│   ├── ListAll.java
│   └── SecretKey.java
├── Main.java
└── utils
    ├── DataBreaches.java
    └── Utils.java
```

---
# Extensibility (Yes it is extensible!)🔥
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
