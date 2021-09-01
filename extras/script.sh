#!/bin/sh
list="$(java 2>/dev/null -jar passwordmanager.jar ls)"
selected="$(echo "$list" | dmenu -i)"
java 2>/dev/null -jar passwordmanager.jar ls -gui "$selected" | xclip -selection clipboard
