#!/bin/sh
list="$(java 2>/dev/null -jar passwordmanager.jar ls | dmenu -i)"
java 2>/dev/null -jar passwordmanager.jar ls -gui "$field" | xclip -selection clipboard
