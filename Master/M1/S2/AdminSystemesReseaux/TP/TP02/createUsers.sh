#!/bin/bash

echo "Need admin rights to execute this script and it can be executed as follows : bash createUsers.sh usersList.txt"
echo "This script also needs a text file containing the list of the users as input parameter"
echo "The file should have the following syntax (one user per line) : firstName:name"
echo ""
echo ""
echo ""
echo ""

gid="100"
group="users"
pathPasswd="/etc/passwd"
pathShadow="/etc/shadow"
nbUsersCreated="1"

while IFS=":" read -r name firstname
do
	login="$name$firstname"
	echo "Login $nbUsersCreated : $login"
	echo ""
	uid=$(( $(cut -d: -f3 $pathPasswd | sort -n | tail -n 1 ) + 1 ))
	echo "uid : $uid"
	echo ""
	pass=$(openssl rand --base64 4)
	encryptedPass=$(openssl passwd -5 password)
	homedir="/home/$login"
	mkdir "$homedir"
	echo "Homedir location : $homedir"
	echo ""
	chown "$login" "$homedir"
	chmod u+rwx "$homedir"
	echo "$login:x:$uid:$gid:$homedir" >> "$pathPasswd"
	echo "$login:$encryptedPass:0:0:30:7:30:::" >> "$pathShadow"
	cp ".bashrc" "$homedir"
	cp ".vimrc" "$homedir"
	cp ".vscode/" "$homedir"
	chown $uid:$gid "$homedir/.bashrc"
	chown $uid:$gid "$homedir/.vimrc"
	chown $uid:$gid "$homedir/.vscode/"
	nbUsersCreated=$(( $nbUsersCreated + 1 ))
	echo ""
	echo ""
done < $1
